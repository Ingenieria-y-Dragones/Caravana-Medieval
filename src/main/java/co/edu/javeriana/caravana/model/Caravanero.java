package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Caravanero extends Jugador {

    @ManyToOne
    private Caravana caravana;

    protected Caravanero() {
        super();
    }

    public Caravanero(Long id, String nombre, Long tiempoJugado, Caravana caravana) {
        super(id, nombre, tiempoJugado);
        this.caravana = caravana;
    }
    
    public Caravana getCaravana() {
        return caravana;
    }
    
    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }

    public void viajarEntreCiudades() {
        System.out.println(getNombre() + " está viajando entre ciudades.");
    }

    public void pagarServicios() {
        System.out.println(getNombre() + " está pagando por servicios.");
    }
    
    public void comerciar() {
        System.out.println(getNombre() + " está comerciando en " + caravana.getCiudadActual());
    }

    @Override
    public void realizarAccion() {
        comerciar();
        viajarEntreCiudades();
        pagarServicios();
    }
}

