package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Comerciante extends Jugador {
    @ManyToOne
    private Ciudad ciudad;

    protected Comerciante() {
        super();
    }

    public Comerciante(Long id, String nombre, Long tiempoJugado, Ciudad ciudad) {
        super(id, nombre, tiempoJugado);
        this.ciudad = ciudad;
    }

    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }

    public void comerciar() {
        System.out.println(getNombre() + " está comerciando en " + ciudad.getNombre());
    }

    @Override
    public void realizarAccion() {
        comerciar();
    }
}
