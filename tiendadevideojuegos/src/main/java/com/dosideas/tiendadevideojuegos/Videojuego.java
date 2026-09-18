package com.dosideas.tiendadevideojuegos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String imagenUrl;

    protected Videojuego() {
    }

    public Videojuego(String titulo, String descripcion, String imagenUrl) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }
}
