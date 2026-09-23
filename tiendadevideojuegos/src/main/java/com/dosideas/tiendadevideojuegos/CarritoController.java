package com.dosideas.tiendadevideojuegos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarritoController {
    private final CarritoItemRepository carritoItemRepository;
    private final VideojuegoRepository videojuegoRepository;

    public CarritoController(CarritoItemRepository carritoItemRepository,
            VideojuegoRepository videojuegoRepository) {
        this.carritoItemRepository = carritoItemRepository;
        this.videojuegoRepository = videojuegoRepository;
    }

    @PostMapping("/carrito/agregar/{videojuegoId}")
    public String agregar(@PathVariable Long videojuegoId, RedirectAttributes redirectAttributes) {
        videojuegoRepository.findById(videojuegoId).ifPresentOrElse(videojuego -> {
            CarritoItem item = carritoItemRepository.findByVideojuegoId(videojuegoId)
                    .orElseGet(() -> new CarritoItem(videojuego));
            if (item.getId() != null) {
                item.aumentarCantidad();
            }
            carritoItemRepository.save(item);
        }, () -> redirectAttributes.addFlashAttribute("mensaje", "El videojuego no existe."));

        return "redirect:/listado";
    }

    @PostMapping("/carrito/{itemId}/aumentar")
    public String aumentar(@PathVariable Long itemId) {
        carritoItemRepository.findById(itemId).ifPresent(item -> {
            item.aumentarCantidad();
            carritoItemRepository.save(item);
        });
        return "redirect:/listado";
    }

    @PostMapping("/carrito/{itemId}/disminuir")
    public String disminuir(@PathVariable Long itemId) {
        carritoItemRepository.findById(itemId).ifPresent(item -> {
            item.disminuirCantidad();
            if (item.getCantidad() <= 0) {
                carritoItemRepository.delete(item);
            } else {
                carritoItemRepository.save(item);
            }
        });
        return "redirect:/listado";
    }

    @PostMapping("/carrito/{itemId}/eliminar")
    public String eliminar(@PathVariable Long itemId) {
        carritoItemRepository.deleteById(itemId);
        return "redirect:/listado";
    }

    @PostMapping("/carrito/vaciar")
    public String vaciar() {
        carritoItemRepository.deleteAll();
        return "redirect:/listado";
    }
}
