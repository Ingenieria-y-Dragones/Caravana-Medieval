package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Administrador extends Jugador { // Ahora extiende de Jugador
    @OneToOne
    private Sistema sistema;

    public Administrador() {
        super(); // Llama al constructor de Jugador
    }

    public Administrador(Long id, String nombre, Long tiempoJugado, Sistema sistema) {
        super(id, nombre, tiempoJugado);
        this.sistema = sistema;
    }

    public Sistema getSistema() { return sistema; }
    public void setSistema(Sistema sistema) { this.sistema = sistema; }

    public void realizarCRUD() {
        System.out.println(getNombre() + " está realizando operaciones CRUD.");
    }

    @Override
    public void realizarAccion() {
        realizarCRUD();
    }
}