package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import java.util.*;

@Entity
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id", nullable = false)
    private Ciudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id", nullable = false)

    private Ciudad ciudadDestino;

    private Double distancia;
    private Boolean segura; // `true` si es segura, `false` si es peligrosa
    private Integer daño; // Daño recibido en rutas inseguras

    public Ruta() {}

    public Ruta(Long id, Ciudad ciudadOrigen, Ciudad ciudadDestino, Double distancia, Boolean segura, Integer daño) {
        this.id = id;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
        this.segura = segura;
        this.daño = daño;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Boolean getSegura() {
        return segura;
    }

    public void setSegura(Boolean segura) {
        this.segura = segura;
    }

    public Integer getDaño() {
        return daño;
    }

    public void setDaño(Integer daño) {
        this.daño = daño;
    }
    public boolean esRutaSegura() {
        return segura;
    }

    public double calcularTiempoViaje(Caravana caravana) {
        return distancia / caravana.getVelocidad();
    }
}
