package com.dosideas.tiendadevideojuegos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VideojuegoCrudController {
    private final VideojuegoRepository videojuegoRepository;
    private final CarritoItemRepository carritoItemRepository;

    public VideojuegoCrudController(VideojuegoRepository videojuegoRepository,
            CarritoItemRepository carritoItemRepository) {
        this.videojuegoRepository = videojuegoRepository;
        this.carritoItemRepository = carritoItemRepository;
    }

    @GetMapping("/videojuegos")
    public String administrar(Model model) {
        model.addAttribute("videojuegos", videojuegoRepository.findAll());
        model.addAttribute("videojuego", new Videojuego());
        return "videojuegos";
    }

    @GetMapping("/videojuegos/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return videojuegoRepository.findById(id)
                .map(videojuego -> {
                    model.addAttribute("videojuegos", videojuegoRepository.findAll());
                    model.addAttribute("videojuego", videojuego);
                    return "videojuegos";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "El videojuego no existe.");
                    return "redirect:/videojuegos";
                });
    }

    @PostMapping("/videojuegos/guardar")
    public String guardar(@RequestParam(required = false) Long id,
            @RequestParam String titulo,
            @RequestParam String descripcion,
            @RequestParam String imagenUrl,
            RedirectAttributes redirectAttributes) {
        if (titulo.isBlank() || descripcion.isBlank() || imagenUrl.isBlank()) {
            redirectAttributes.addFlashAttribute("mensaje", "Todos los campos son obligatorios.");
            return "redirect:/videojuegos";
        }

        if (id == null) {
            videojuegoRepository.save(new Videojuego(titulo.trim(), descripcion.trim(), imagenUrl.trim()));
            redirectAttributes.addFlashAttribute("mensaje", "Videojuego agregado correctamente.");
        } else {
            videojuegoRepository.findById(id).ifPresentOrElse(videojuego -> {
                videojuego.actualizar(titulo.trim(), descripcion.trim(), imagenUrl.trim());
                videojuegoRepository.save(videojuego);
            }, () -> redirectAttributes.addFlashAttribute("mensaje", "El videojuego no existe."));
        }

        return "redirect:/videojuegos";
    }

    @PostMapping("/videojuegos/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (videojuegoRepository.existsById(id)) {
            carritoItemRepository.deleteByVideojuegoId(id);
            videojuegoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Videojuego eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "El videojuego no existe.");
        }
        return "redirect:/videojuegos";
    }
}
