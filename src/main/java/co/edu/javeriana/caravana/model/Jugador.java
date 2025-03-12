package co.edu.javeriana.caravana.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long tiempoJugado;

    // Relaciones de las subclases originales
    @ManyToOne
    @JoinColumn(name = "caravana_id")
    private Caravana caravana;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @OneToOne
    @JoinColumn(name = "sistema_id")
    private Sistema sistema;

    @Enumerated(EnumType.STRING)
    private TipoRol rol;

    public enum TipoRol {
        ADMINISTRADOR, CARAVANERO, COMERCIANTE
    }

    // Constructores
    public Jugador() {}

    public Jugador(Long id, String nombre, Long tiempoJugado, TipoRol rol) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoJugado = tiempoJugado;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getTiempoJugado() { return tiempoJugado; }
    public void setTiempoJugado(Long tiempoJugado) { this.tiempoJugado = tiempoJugado; }
    public TipoRol getRol() { return rol; }
    public void setRol(TipoRol rol) { this.rol = rol; }
    public Caravana getCaravana() { return caravana; }
    public void setCaravana(Caravana caravana) { this.caravana = caravana; }
    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }
    public Sistema getSistema() { return sistema; }
    public void setSistema(Sistema sistema) { this.sistema = sistema; }

    // Lógica de acciones según rol
    public void realizarAccion() {
        switch(rol) {
            case CARAVANERO -> {
                comerciar();
                viajarEntreCiudades();
                pagarServicios();
            }
            case COMERCIANTE -> comerciar();
            case ADMINISTRADOR -> realizarCRUD();
        }
    }

    // Métodos específicos (ahora en Jugador)
    private void comerciar() {
        if (rol == TipoRol.CARAVANERO) {
            System.out.println(nombre + " está comerciando en " + caravana.getCiudadActual());
        } else if (rol == TipoRol.COMERCIANTE) {
            System.out.println(nombre + " está comerciando en " + ciudad.getNombre());
        }
    }

    private void viajarEntreCiudades() {
        System.out.println(nombre + " está viajando entre ciudades.");
    }

    private void pagarServicios() {
        System.out.println(nombre + " está pagando por servicios.");
    }

    private void realizarCRUD() {
        System.out.println(nombre + " está realizando operaciones CRUD en el sistema " );
    }

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
}