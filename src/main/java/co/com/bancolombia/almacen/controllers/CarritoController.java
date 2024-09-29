package co.com.bancolombia.almacen.controllers;

import co.com.bancolombia.almacen.dto.AddProductToCartRequest;
import co.com.bancolombia.almacen.model.Carrito;
import co.com.bancolombia.almacen.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/add-product")
    public Mono<Carrito> addProductToCart(@RequestBody AddProductToCartRequest request) {
        return carritoService.addProductToCart(request);
    }

    @DeleteMapping("/remove-product/{carritoId}/{productoId}")
    public Mono<Carrito> removeProductFromCart(@PathVariable Integer carritoId, @PathVariable Integer productoId) {
        return carritoService.removeProductFromCart(carritoId, productoId);
    }

    @PutMapping("/update-product-quantity/{carritoId}/{productoId}/{cantidad}")
    public Mono<Carrito> updateProductQuantity(@PathVariable Integer carritoId, @PathVariable Integer productoId, @PathVariable Integer cantidad) {
        return carritoService.updateProductQuantity(carritoId, productoId, cantidad);
    }

    @GetMapping("/contents/{carritoId}")
    public Mono<Carrito> getCartContents(@PathVariable Integer carritoId) {
        return carritoService.getCartContents(carritoId);
    }

    @DeleteMapping("/empty/{carritoId}")
    public Mono<Void> emptyCart(@PathVariable Integer carritoId) {
        return carritoService.emptyCart(carritoId);
    }

    @GetMapping("/calculate-total/{carritoId}")
    public Mono<Double> calculateTotal(@PathVariable Integer carritoId) {
        return carritoService.calculateTotal(carritoId);
    }

    @PostMapping("/register-order/{carritoId}")
    public Mono<Void> registerOrder(@PathVariable Integer carritoId) {
        return carritoService.registerOrder(carritoId);
    }
}