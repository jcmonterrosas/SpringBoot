package com.guia.serviciosweb.model;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
//Ignora las propiedades JSON antes de que se cargue totalmente el objeto, para que no hayan fallos en la serialización.
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String genero;
    private Integer edad;

    public UsuarioModelo(){

    }

    public UsuarioModelo(String nombres, String apellidos, String direccion, String genero, Integer edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.genero = genero;
        this.edad = edad;
    }

    public UsuarioModelo(Integer id, String nombres, String apellidos, String direccion, String genero, Integer edad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.genero = genero;
        this.edad = edad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
