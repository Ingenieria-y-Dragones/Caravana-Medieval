package co.edu.javeriana.caravana.dto;

public class RutaDTO {
    private Long id;
    private String nombre;
    private Double distancia;
    private Boolean segura; // `true` si es segura, `false` si es peligrosa
    private Integer daño; // Daño recibido en rutas inseguras

    public RutaDTO() {
    }

    public RutaDTO(Long id, String nombre, Double distancia, Boolean segura, Integer daño) {
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.segura = segura;
        this.daño = daño;
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

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Boolean getSegura() {
        return segura;
    }

    public void setSegura(Boolean segura) {
        this.segura = segura;
    }

    public Integer getDaño() {
        return daño;
    }

    public void setDaño(Integer daño) {
        this.daño = daño;
    }

    
}

