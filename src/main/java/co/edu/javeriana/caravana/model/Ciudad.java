package co.edu.javeriana.caravana.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private Double impuesto;

    @ElementCollection
    private Map<Producto, Integer> productosDisponibles; //Stock

    @ElementCollection
    private Map<Producto, Double> factoresDemanda; // FD por producto

    @ElementCollection
    private Map<Producto, Double> factoresOferta; // FO por producto

    @OneToMany
    private List<Servicio> serviciosDisponibles;

    @OneToMany
    private final Set<Ruta> rutasSalientes = new HashSet<>();

    public Ciudad() {}

    public Ciudad(Map<Producto, Double> factoresDemanda, Map<Producto, Double> factoresOferta, Long id, String nombre, Double impuesto,Map<Producto, Integer> productosDisponibles, List<Servicio> serviciosDisponibles) {
        this.factoresDemanda = factoresDemanda;
        this.factoresOferta = factoresOferta;
        this.id = id;
        this.nombre = nombre;
        this.productosDisponibles = productosDisponibles;
        this.serviciosDisponibles = serviciosDisponibles;
        this.impuesto = impuesto;
    }

    public double calcularPrecioVenta(Producto producto) {
        return factoresDemanda.get(producto) / (1 + productosDisponibles.getOrDefault(producto, 0));
    }

    public double calcularPrecioCompra(Producto producto) {
        return factoresOferta.get(producto) / (1 + productosDisponibles.getOrDefault(producto, 0));
    }

    public void realizarVenta(Producto producto, int cantidad) {
        // Lógica de venta de productos
    }

    public void realizarCompra(Producto producto, int cantidad) {
        // Lógica de compra de productos
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

    public Map<Producto, Integer> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(Map<Producto, Integer> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public Map<Producto, Double> getFactoresDemanda() {
        return factoresDemanda;
    }

    public void setFactoresDemanda(Map<Producto, Double> factoresDemanda) {
        this.factoresDemanda = factoresDemanda;
    }

    public Map<Producto, Double> getFactoresOferta() {
        return factoresOferta;
    }

    public void setFactoresOferta(Map<Producto, Double> factoresOferta) {
        this.factoresOferta = factoresOferta;
    }

    public List<Servicio> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    public void setServiciosDisponibles(List<Servicio> serviciosDisponibles) {
        this.serviciosDisponibles = serviciosDisponibles;
    }
    public Set<Ruta> getRutasSalientes() {
        return rutasSalientes;
    }

    public void agregarRutaSaliente(Ruta ruta) {
        rutasSalientes.add(ruta);
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

}

