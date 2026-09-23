package com.dosideas.tiendadevideojuegos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListadoController {
    private final VideojuegoRepository videojuegoRepository;
    private final CarritoItemRepository carritoItemRepository;

    public ListadoController(VideojuegoRepository videojuegoRepository,
            CarritoItemRepository carritoItemRepository) {
        this.videojuegoRepository = videojuegoRepository;
        this.carritoItemRepository = carritoItemRepository;
    }

    @GetMapping("/listado")
    public String listado(@RequestParam(defaultValue = "") String q, Model model) {
        List<Videojuego> videojuegos = q.isBlank()
                ? videojuegoRepository.findAll()
                : videojuegoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(q, q);

        model.addAttribute("videojuegos", videojuegos);
        model.addAttribute("busqueda", q);
        model.addAttribute("carrito", carritoItemRepository.findAll());
        model.addAttribute("totalProductos", carritoItemRepository.findAll().stream()
            .mapToInt(CarritoItem::getCantidad)
            .sum());
        return "listado";
    }
}