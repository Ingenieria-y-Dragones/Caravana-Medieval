package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import java.util.*;

@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Double costo;

    @Enumerated(EnumType.STRING)
    private TipoServicio tipo;

    public Servicio(Double costo, Long id, String nombre, TipoServicio tipo) {
        this.costo = costo;
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public TipoServicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoServicio tipo) {
        this.tipo = tipo;
    }

    public enum TipoServicio {
        REPARACION, MEJORA_CAPACIDAD, MEJORA_VELOCIDAD, GUARDIAS
    }

    public void aplicarServicio(Caravana caravana) {
        // Lógica para modificar la caravana según el servicio adquirido
    }
}

