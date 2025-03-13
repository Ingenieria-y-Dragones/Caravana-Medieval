package co.edu.javeriana.caravana.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comerciante extends Jugador {
    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
