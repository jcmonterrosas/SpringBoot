package com.guia.serviciosweb.model;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String address;
    private String genre;
    private Integer age;

    public UserEntity(){

    }

    public UserEntity(String firstname, String lastname, String address, String genre, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.genre = genre;
        this.age = age;
    }

    public UserEntity(Integer id, String firstname, String lastname, String address, String genre, Integer age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.genre = genre;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
