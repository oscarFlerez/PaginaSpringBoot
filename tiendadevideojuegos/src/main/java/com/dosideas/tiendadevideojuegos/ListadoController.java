package com.dosideas.tiendadevideojuegos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListadoController {
    private final VideojuegoRepository videojuegoRepository;

    public ListadoController(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    @GetMapping("/listado")
    public String listado(@RequestParam(defaultValue = "") String q, Model model) {
        List<Videojuego> videojuegos = q.isBlank()
                ? videojuegoRepository.findAll()
                : videojuegoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(q, q);

        model.addAttribute("videojuegos", videojuegos);
        model.addAttribute("busqueda", q);
        return "listado";
    }
}