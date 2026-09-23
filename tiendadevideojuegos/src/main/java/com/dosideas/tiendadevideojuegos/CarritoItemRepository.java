package com.dosideas.tiendadevideojuegos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    Optional<CarritoItem> findByVideojuegoId(Long videojuegoId);
}
