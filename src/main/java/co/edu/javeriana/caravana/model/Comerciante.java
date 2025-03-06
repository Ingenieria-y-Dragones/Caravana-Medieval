package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Comerciante extends Jugador {

    @ManyToOne
    private final Ciudad ciudad;

    public Comerciante(Long id, String nombre, Long tiempoJugado, Ciudad ciudad) {
        super(id, nombre, tiempoJugado);
        this.ciudad = ciudad;
    }

    public void comerciar() {
        System.out.println(getNombre() + " está comerciando en " + ciudad.getNombre());
    }

    @Override
    public void realizarAccion() {
        comerciar();
    }
}
