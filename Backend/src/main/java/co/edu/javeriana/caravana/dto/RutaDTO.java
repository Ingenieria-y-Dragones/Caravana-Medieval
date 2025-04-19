package co.edu.javeriana.caravana.dto;

import co.edu.javeriana.caravana.model.TipoPeligro;

public class RutaDTO {

    private Long id;
    private String nombre;
    private Float distancia;
    private Float danio;
    private TipoPeligro peligro;

    public RutaDTO() {
    }

    public RutaDTO(Long id, String nombre, Float distancia, Float danio, TipoPeligro peligro) {
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.danio = danio;
        this.peligro = peligro;
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

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public Float getDanio() {
        return danio;
    }

    public void setDanio(Float danio) {
        this.danio = danio;
    }

    public TipoPeligro getPeligro() {
        return peligro;
    }

    public void setPeligro(TipoPeligro peligro) {
        this.peligro = peligro;
    }
}
