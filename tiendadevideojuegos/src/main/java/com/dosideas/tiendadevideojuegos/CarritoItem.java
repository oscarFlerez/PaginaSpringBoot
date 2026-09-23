package com.dosideas.tiendadevideojuegos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Videojuego videojuego;

    private int cantidad;

    protected CarritoItem() {
    }

    public CarritoItem(Videojuego videojuego) {
        this.videojuego = videojuego;
        this.cantidad = 1;
    }

    public Long getId() {
        return id;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void aumentarCantidad() {
        cantidad++;
    }

    public void disminuirCantidad() {
        cantidad--;
    }
}
