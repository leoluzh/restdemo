package com.lambdasys.restdemo.controllers;

import com.lambdasys.restdemo.assemblers.MunicipioModelAssembler;
import com.lambdasys.restdemo.domains.Municipio;
import com.lambdasys.restdemo.exceptions.MunicipioNotFoundException;
import com.lambdasys.restdemo.repositories.MunicipioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Minicipio Controller
 *
 * Expor funcionalidade via interface rest para municipio.
 * Inclusão de conceito hateoas para navegação entre recursos.
 *
 * leoluzh
 * @since 25/07/2020
 * @version 0.1
 */

@RestController
public class MunicipioController implements Serializable {

    protected MunicipioRepository repository;
    // IMPLEMENTANDO HATEOAS
    protected MunicipioModelAssembler assembler;

    public MunicipioController(
            MunicipioRepository repository,
            MunicipioModelAssembler assembler ){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/municipios")
    public CollectionModel<EntityModel<Municipio>> all(){
        return assembler.toCollectionModel( this.repository.findAll() );
    }

    @PostMapping("/municipios")
    public EntityModel<Municipio> newMunicipio(@RequestBody Municipio municipio ){

        final Municipio resultado = this.repository.save( municipio );
        // IMPLEMENTANDO HATEOAS
        return assembler.toModel( resultado );

    }

    @GetMapping("/municipios/{id}")
    public EntityModel<Municipio> one(@PathVariable Integer id ){

        final Municipio resultado = this.repository
                .findById( id )
                .orElseThrow( () -> new MunicipioNotFoundException( id ) );

        // IMPLEMENTANDO HATEOAS
        return assembler.toModel( resultado );

    }

    @PutMapping("/municipios/{id}")
    public EntityModel<Municipio> replaceMunicipio(
            @RequestBody Municipio newMunicipio ,
            @PathVariable("id") Integer id ){

        final Municipio resultado = repository.findById( id ).map( municipio -> {
           municipio.setNome( newMunicipio.getNome() );
           municipio.setUf( newMunicipio.getUf() );
           return this.repository.save( municipio );
        }).orElseGet( () -> {
            return this.repository.save( newMunicipio );
        });

        // IMPLEMENTANDO HATEOAS
        return assembler.toModel( resultado );

    }

    @PatchMapping("/municipios/{id}")
    public EntityModel<Municipio> patchMunicipio(
            @RequestBody Municipio newMunicipio ,
            @PathVariable("id") Integer id ){

        final Municipio resultado = repository.findById( id ).map( municipio -> {
            boolean patch = false;
            if(StringUtils.isNotBlank( newMunicipio.getNome() ) ) {
                municipio.setNome(newMunicipio.getNome());
                patch = true;
            }
            if(Objects.nonNull(newMunicipio.getUf()) ) {
                municipio.setUf(newMunicipio.getUf());
                patch = true;
            }
            if( patch ) {
                return this.repository.save(municipio);
            }else{
                return municipio;
            }
        }).orElseThrow( () -> new MunicipioNotFoundException( id ) );

        // IMPLEMENTANDO HATEOAS
        return assembler.toModel( resultado );

    }

    @DeleteMapping("/municipios/{id}")
    public void deleteMunicipio( @PathVariable("id") Integer id ){
        repository.deleteById( id );
    }


    @GetMapping("/unidadesfederativas/{id}/municipios")
    public CollectionModel<EntityModel<Municipio>> municipiosByUfId(
            @PathVariable("id") Integer id ){
        List<Municipio> resultado = this.repository.findByUnidadeFederativaId( id );
        // IMPLEMENTANDO HATEOAS
        return assembler.toCollectionModel( resultado );
    }


}
