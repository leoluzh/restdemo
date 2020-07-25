package com.lambdasys.restdemo.repositories;

import com.lambdasys.restdemo.domains.UnidadeFederativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * leoluzh
 * @since 25/07/2020
 * @version 0.1
 */

public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa,Integer> {

    List<UnidadeFederativa> findByNome( String nome );
    List<UnidadeFederativa> findBySigla(String sigla );

}
