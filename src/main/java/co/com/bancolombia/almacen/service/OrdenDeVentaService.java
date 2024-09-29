package co.com.bancolombia.almacen.service;

import co.com.bancolombia.almacen.model.OrdenDeVenta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenDeVentaService {
    Flux<OrdenDeVenta> getAllOrdenesDeVenta();
    Mono<OrdenDeVenta> getOrdenDeVentaById(Integer id);
    Mono<OrdenDeVenta> createOrdenDeVenta(OrdenDeVenta ordenDeVenta);
    Mono<OrdenDeVenta> updateOrdenDeVenta(Integer id, OrdenDeVenta ordenDeVenta);
    Mono<Void> deleteOrdenDeVenta(Integer id);
}