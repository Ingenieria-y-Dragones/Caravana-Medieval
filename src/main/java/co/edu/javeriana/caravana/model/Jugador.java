package co.edu.javeriana.caravana.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Long tiempoJugado;

    protected Jugador() {}

    public Jugador(Long id, String nombre, Long tiempoJugado) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoJugado = tiempoJugado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getTiempoJugado() { return tiempoJugado; }
    public void setTiempoJugado(Long tiempoJugado) { this.tiempoJugado = tiempoJugado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(id, jugador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public abstract void realizarAccion();
}