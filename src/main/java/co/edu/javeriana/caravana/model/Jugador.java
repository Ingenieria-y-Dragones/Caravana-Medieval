package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
//import java.util.*;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne
    private Caravana caravana;

    public Jugador(Caravana caravana, Long id, String nombre, Rol rol) {
        this.caravana = caravana;
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Caravana getCaravana() {
        return caravana;
    }

    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }

    public enum Rol {
        COMERCIANTE, CARAVANERO, ADMINISTRADOR
    }

    public boolean puedeViajar() {
        return rol == Rol.CARAVANERO;
    }

    public boolean puedeComerciar() {
        return rol == Rol.COMERCIANTE || rol == Rol.CARAVANERO;
    }

    public boolean puedeAdministrar() {
        return rol == Rol.ADMINISTRADOR;
    }

}

