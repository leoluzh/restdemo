package com.lambdasys.restdemo.assemblers;

import com.lambdasys.restdemo.controllers.MunicipioController;
import com.lambdasys.restdemo.controllers.UnidadeFederativaController;
import com.lambdasys.restdemo.domains.UnidadeFederativa;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * leoluzh
 * @since 25/07/2020
 * @version 0.1
 */

@Component
public class UnidadeFederativaModelAssembler
        implements RepresentationModelAssembler<UnidadeFederativa, EntityModel<UnidadeFederativa>> {

    @Override
    public EntityModel<UnidadeFederativa> toModel(UnidadeFederativa uf) {
        final EntityModel<UnidadeFederativa> resultado = EntityModel.of( uf ,
                linkTo(methodOn( UnidadeFederativaController.class ).one( uf.getId() )).withSelfRel() ,
                linkTo(methodOn( UnidadeFederativaController.class ).all()).withRel("unidadesfederativas") ,
                linkTo(methodOn( MunicipioController.class ).municipiosByUfId( uf.getId() )).withRel("municipios"));
        return resultado;
    }

}
