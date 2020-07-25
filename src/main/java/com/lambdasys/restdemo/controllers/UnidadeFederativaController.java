package com.lambdasys.restdemo.controllers;

import com.lambdasys.restdemo.assemblers.UnidadeFederativaModelAssembler;
import com.lambdasys.restdemo.domains.UnidadeFederativa;
import com.lambdasys.restdemo.exceptions.UnidadeFederativaNotFoundException;
import com.lambdasys.restdemo.repositories.UnidadeFederativaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Unidade Federativa Controller
 *
 * Expor funcionalidade via interface rest para unidades federativas.
 * Inclusão de conceito hateoas para navegação entre recursos.
 *
 * leoluzh
 * @since 25/07/2020
 * @version 0.1
 */

@RestController
public class UnidadeFederativaController implements Serializable {

    protected UnidadeFederativaRepository repository;
    // IMPLEMENTANDO HATEOAS
    protected UnidadeFederativaModelAssembler assembler;

    public UnidadeFederativaController(
            UnidadeFederativaRepository repository ,
            UnidadeFederativaModelAssembler assembler ){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/unidadesfederativas")
    public CollectionModel<EntityModel<UnidadeFederativa>> all(){
        // IMPLEMENTANDO HATEOAS
        return this.assembler.toCollectionModel( this.repository.findAll() );
    }

    @PostMapping("/unidadesfederativas")
    public EntityModel<UnidadeFederativa> newUnidadeFederativa(
            @RequestBody UnidadeFederativa uf ){
        // IMPLEMENTANDO HATEOAS
        return this.assembler.toModel( this.repository.save( uf ) );
    }

    @GetMapping("/unidadesfederativas/{id}")
    public EntityModel<UnidadeFederativa> one(@PathVariable("id") Integer id ) {
        UnidadeFederativa resultado = this.repository
                .findById( id )
                .orElseThrow( () -> new UnidadeFederativaNotFoundException( id ) );
        // IMPLEMENTANDO HATEOAS
        return this.assembler.toModel( resultado );
    }

    @PutMapping("/unidadesfederativas/{id}")
    public EntityModel<UnidadeFederativa> replaceUnidadeFederativa(
            @RequestBody UnidadeFederativa newUnidadeFederativa ,
            @PathVariable("id") Integer id ){

         UnidadeFederativa resultado = repository.findById( id )
                 .map( ( unidadeFederativa ) -> {
                    unidadeFederativa.setNome( newUnidadeFederativa.getNome() );
                    unidadeFederativa.setSigla( newUnidadeFederativa.getSigla() );
                    return repository.save( unidadeFederativa );
                 })
                 .orElseGet( () -> { return repository.save( newUnidadeFederativa ); }) ;
        // IMPLEMENTANDO HATEOAS
         return this.assembler.toModel( resultado );

    }

    @PatchMapping("/unidadesfederativas/{id}")
    public EntityModel<UnidadeFederativa> updateUnidadeFederativa(
            @RequestBody UnidadeFederativa newUnidadeFederativa ,
            @PathVariable("id") Integer id
    ) {

        UnidadeFederativa resultado = repository.findById( id )
                .map( unidadeFederativa -> {

                    boolean patched = false;

                    if( StringUtils.isNotBlank( newUnidadeFederativa.getNome() ) ){
                        unidadeFederativa.setNome( newUnidadeFederativa.getNome() );
                        patched = true;
                    }

                    if( StringUtils.isNotBlank( newUnidadeFederativa.getSigla() ) ) {
                        unidadeFederativa.setSigla(newUnidadeFederativa.getSigla());
                        patched = true;
                    }

                    if( patched ) {
                        return this.repository.save(unidadeFederativa);
                    }else{
                        return unidadeFederativa;
                    }

                }).orElseThrow( () -> new UnidadeFederativaNotFoundException( id ) );

        // IMPLEMENTANDO HATEOAS
        return this.assembler.toModel( resultado );

    }

    @DeleteMapping("/unidadesfederativas/{id}")
    public void deleteUnidadeFederativa( @PathVariable("id") Integer id ){
        repository.deleteById( id );
    }

}
