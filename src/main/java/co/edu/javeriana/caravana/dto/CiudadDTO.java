package co.edu.javeriana.caravana.dto;

import java.util.Set;

import co.edu.javeriana.caravana.model.Ruta;

public class CiudadDTO {
    private Long id;
    private String nombre;
    private Double impuesto;
    private Set<Ruta> rutasEntrantes;
    private Set<Ruta> rutasSalientes;

    public CiudadDTO() {}

    public CiudadDTO(Long id, String nombre, Double impuesto, Set<Ruta> rutasEntrantes, Set<Ruta> rutasSalientes) {
        this.id = id;
        this.nombre = nombre;
        this.impuesto = impuesto;
        this.rutasEntrantes = rutasEntrantes;
        this.rutasSalientes = rutasSalientes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getImpuesto() { return impuesto; }
    public void setImpuesto(Double impuesto) { this.impuesto = impuesto; }

    public Set<Ruta> getRutasEntrantes() { return rutasEntrantes; }
    public void setRutasEntrantes(Set<Ruta> rutasEntrantes) { this.rutasEntrantes = rutasEntrantes; }

    public Set<Ruta> getRutasSalientes() { return rutasSalientes; }
    public void setRutasSalientes(Set<Ruta> rutasSalientes) { this.rutasSalientes = rutasSalientes; }
}


