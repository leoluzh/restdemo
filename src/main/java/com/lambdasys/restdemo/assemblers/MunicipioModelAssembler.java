package com.lambdasys.restdemo.assemblers;

import com.lambdasys.restdemo.controllers.MunicipioController;
import com.lambdasys.restdemo.controllers.UnidadeFederativaController;
import com.lambdasys.restdemo.domains.Municipio;
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
public class MunicipioModelAssembler implements RepresentationModelAssembler<Municipio, EntityModel<Municipio>> {

    @Override
    public EntityModel<Municipio> toModel(Municipio municipio) {
        final EntityModel<Municipio> resultado = EntityModel.of(municipio,
                linkTo(methodOn(MunicipioController.class).one(municipio.getId())).withSelfRel(),
                linkTo(methodOn(MunicipioController.class).all()).withRel("municipios"),
                linkTo(methodOn(UnidadeFederativaController.class).one(municipio.getUf().getId())).withRel("unidadesfederativas"));
        return resultado;
    }
}
