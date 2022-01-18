package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "pelicula")
@Getter
@Setter

public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)

    private long id;

    private String imagen;

    private String titulo;

    @Column(name= "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDeCreacion;

    private int calificacion;

    //Muchas peliculas van a estar en un genero.
    //Cada pelicula va a tener un solo genero
    //Cada genero va a tener muchas peliculas
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;
    //cuando haga una lista de paises se va a traer la info del continente enero

    @Column(name = "genero_id",nullable = false)
    private long generoId;
    //Sirve para guardar y actualizar donde tengo el ID

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn (name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn (name = "personaje_id"))
    private Set<PersonajeEntity> personaje = new HashSet<>();

}
