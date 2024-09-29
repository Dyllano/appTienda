package co.com.bancolombia.almacen.service;

import co.com.bancolombia.almacen.model.OrdenCompra;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenCompraService {
    Flux<OrdenCompra> getAllOrdenesDeCompra();
    Mono<OrdenCompra> getOrdenCompraById(Integer id);
    Mono<OrdenCompra> createOrdenCompra(OrdenCompra ordenCompra);
    Mono<OrdenCompra> updateOrdenCompra(Integer id, OrdenCompra ordenCompra);
    Mono<Void> deleteOrdenCompra(Integer id);
}