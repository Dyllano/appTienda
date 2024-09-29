package co.com.bancolombia.almacen.service;

import co.com.bancolombia.almacen.model.ItemCompra;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemCompraService {
    Flux<ItemCompra> getAllItemsCompra();
    Mono<ItemCompra> getItemCompraById(Integer id);
    Mono<ItemCompra> createItemCompra(ItemCompra itemCompra);
    Mono<ItemCompra> updateItemCompra(Integer id, ItemCompra itemCompra);
    Mono<Void> deleteItemCompra(Integer id);
}