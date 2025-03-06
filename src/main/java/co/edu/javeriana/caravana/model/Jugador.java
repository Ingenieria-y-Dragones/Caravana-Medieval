package co.edu.javeriana.caravana.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
//import java.util.*;

@MappedSuperclass
public abstract class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private Long tiempoJugado;


    public Jugador(Long id, String nombre, Long tiempoJugado) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.tiempoJugado = tiempoJugado;
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

    public Long getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(Long tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    
    }
    public abstract void realizarAccion();

}

