package co.edu.javeriana.caravana.model;

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
}