package com.guia.serviciosweb.repository;

import com.guia.serviciosweb.model.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Integer> {

}
