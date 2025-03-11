package co.edu.javeriana.caravana.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sistema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long tiempoLimiteJuego;

    private Double gananciasMinimas;

    @OneToMany
    private List<Caravana> caravanas;
    
    @OneToMany
    private List<Mapa> mapas;

    @OneToMany
    private List<Producto> productos;

    public Sistema(List<Caravana> caravanas, Double gananciasMinimas, Long id, List<Mapa> mapas, Long tiempoLimiteJuego, List<Producto> productos) {
        this.caravanas = caravanas;
        this.gananciasMinimas = gananciasMinimas;
        this.id = id;
        this.mapas = mapas;
        this.tiempoLimiteJuego = tiempoLimiteJuego;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTiempoLimiteJuego() {
        return tiempoLimiteJuego;
    }

    public void setTiempoLimiteJuego(Long tiempoLimiteJuego) {
        this.tiempoLimiteJuego = tiempoLimiteJuego;
    }

    public Double getGananciasMinimas() {
        return gananciasMinimas;
    }

    public void setGananciasMinimas(Double gananciasMinimas) {
        this.gananciasMinimas = gananciasMinimas;
    }

    public List<Caravana> getCaravanas() {
        return caravanas;
    }

    public void setCaravanas(List<Caravana> caravanas) {
        this.caravanas = caravanas;
    }

    public List<Mapa> getMapas() {
        return mapas;
    }

    public void setMapas(List<Mapa> mapas) {
        this.mapas = mapas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }




}
