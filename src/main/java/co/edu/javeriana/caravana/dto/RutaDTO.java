package co.edu.javeriana.caravana.dto;

import jakarta.validation.constraints.NotNull;

public class RutaDTO {
    private Long id;
    private String nombre;
    private Double distancia;
    private Boolean segura;
    private Integer daño;

    @NotNull(message = "Debe seleccionar una ciudad de origen")
    private String ciudadOrigen; // Usamos String en lugar de Ciudad

    @NotNull(message = "Debe seleccionar una ciudad de destino")
    private String ciudadDestino; // Usamos String en lugar de Ciudad

    public RutaDTO() {}

    public RutaDTO(Long id, String nombre, Double distancia, Boolean segura, Integer daño, String ciudadOrigen, String ciudadDestino) {
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.segura = segura;
        this.daño = daño;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getDistancia() { return distancia; }
    public void setDistancia(Double distancia) { this.distancia = distancia; }

    public Boolean getSegura() { return segura; }
    public void setSegura(Boolean segura) { this.segura = segura; }

    public Integer getDaño() { return daño; }
    public void setDaño(Integer daño) { this.daño = daño; }

    public String getCiudadOrigen() { return ciudadOrigen; }
    public void setCiudadOrigen(String ciudadOrigenNombre) { this.ciudadOrigen = ciudadOrigenNombre; }

    public String getCiudadDestino() { return ciudadDestino; }
    public void setCiudadDestino(String ciudadDestinoNombre) { this.ciudadDestino = ciudadDestinoNombre; }
}
