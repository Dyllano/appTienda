package co.com.bancolombia.almacen.service;

import co.com.bancolombia.almacen.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
    Flux<Producto> getAllProductos();
    Mono<Producto> getProductoById(Integer id);
    Mono<Producto> createProducto(Producto producto);
    Mono<Producto> updateProducto(Integer id, Producto producto);
    Mono<Void> deleteProducto(Integer id);
}