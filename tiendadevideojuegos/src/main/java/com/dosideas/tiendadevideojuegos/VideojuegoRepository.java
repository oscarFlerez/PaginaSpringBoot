package com.dosideas.tiendadevideojuegos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    List<Videojuego> findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
            String titulo, String descripcion);
}
