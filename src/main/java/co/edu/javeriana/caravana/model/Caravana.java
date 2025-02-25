package co.edu.javeriana.caravana.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Caravana {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Integer velocidad;
    private Float capacidadMaxima;
    private Integer dinero;
    private Integer hp;
    private Boolean proteccion;

    @ManyToOne
    private Ciudad ciudadActual;

    @OneToMany
    private List<Producto> inventario;

    @OneToMany(mappedBy = "caravana")
    private List<Jugador> jugadores;

    public Caravana(Float capacidadMaxima, Ciudad ciudadActual, Integer dinero, Integer hp, Long id, List<Producto> inventario, List<Jugador> jugadores, String nombre, Boolean proteccion, Integer velocidad) {
        this.capacidadMaxima = capacidadMaxima;
        this.ciudadActual = ciudadActual;
        this.dinero = dinero;
        this.hp = hp;
        this.id = id;
        this.inventario = inventario;
        this.jugadores = jugadores;
        this.nombre = nombre;
        this.proteccion = proteccion;
        this.velocidad = velocidad;
    }

    public void comprarProducto(Producto producto, int cantidad) {
        // Lógica de compra de productos
    }

    public void venderProducto(Producto producto, int cantidad) {
        // Lógica de venta de productos
    }

    public void viajar(Ciudad destino, Ruta ruta) {
        // Lógica de viaje
    }

    public void pagarServicio(Servicio servicio) {
        // Lógica de pago de servicios
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

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Float getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Float capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Integer getDinero() {
        return dinero;
    }

    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Boolean getProteccion() {
        return proteccion;
    }

    public void setProteccion(Boolean proteccion) {
        this.proteccion = proteccion;
    }

    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    public List<Producto> getInventario() {
        return inventario;
    }

    public void setInventario(List<Producto> inventario) {
        this.inventario = inventario;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


}
