package co.com.bancolombia.almacen.service;

import co.com.bancolombia.almacen.dto.AddProductToCartRequest;
import co.com.bancolombia.almacen.model.Carrito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarritoService {
    Flux<Carrito> getAllCarritos();
    Mono<Carrito> getCarritoById(Integer id);
    Mono<Carrito> createCarrito(Carrito carrito);
    Mono<Carrito> updateCarrito(Integer id, Carrito carrito);
    Mono<Void> deleteCarrito(Integer id);
    Mono<Carrito> addProductToCart(AddProductToCartRequest request);
    Mono<Carrito> removeProductFromCart(Integer carritoId, Integer productoId);
    Mono<Carrito> updateProductQuantity(Integer carritoId, Integer productoId, Integer cantidad);
    Mono<Carrito> getCartContents(Integer carritoId);
    Mono<Void> emptyCart(Integer carritoId);
    Mono<Double> calculateTotal(Integer carritoId);
    Mono<Void> registerOrder(Integer carritoId);
}