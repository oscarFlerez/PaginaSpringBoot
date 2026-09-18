package com.dosideas.tiendadevideojuegos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatosIniciales {
    @Bean
    CommandLineRunner cargarVideojuegos(VideojuegoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Videojuego(
                        "Super Smash Bros.",
                        "Juego de lucha",
                        "https://www.euronics.es/blog/wp-content/uploads/2016/07/mejores-juegos.jpg"));
                repository.save(new Videojuego(
                        "Minecraft",
                        "Juego de construccion",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQy548-rWizZcGBtReNQ5tP5kRc9Rr4o2Q3HjqemchwYn1Vq70KhF0Dn6M&s=10"));
                repository.save(new Videojuego(
                        "Elden Ring",
                        "Juego de rol",
                        "https://store-images.s-microsoft.com/image/apps.21572.14220340964291907.89c95a82-805b-43b9-8393-3be8df29bd15.019f9a77-d622-467b-a294-de28fe09f661"));
            }
        };
    }
}
