package co.com.bancolombia.almacen.service.impl;

import co.com.bancolombia.almacen.model.ItemCompra;
import co.com.bancolombia.almacen.repository.ItemCompraRepository;
import co.com.bancolombia.almacen.service.ItemCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemCompraServiceImpl implements ItemCompraService {

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Override
    public Flux<ItemCompra> getAllItemsCompra() {
        return itemCompraRepository.findAll();
    }

    @Override
    public Mono<ItemCompra> getItemCompraById(Integer id) {
        return itemCompraRepository.findById(id);
    }

    @Override
    public Mono<ItemCompra> createItemCompra(ItemCompra itemCompra) {
        return itemCompraRepository.save(itemCompra);
    }

    @Override
    public Mono<ItemCompra> updateItemCompra(Integer id, ItemCompra itemCompra) {
        return itemCompraRepository.findById(id)
                .flatMap(existingItemCompra -> {
                    existingItemCompra.setProductoId(itemCompra.getProductoId());
                    existingItemCompra.setCantidad(itemCompra.getCantidad());
                    existingItemCompra.setPrecio(itemCompra.getPrecio());
                    existingItemCompra.setUpdatedAt(itemCompra.getUpdatedAt());
                    return itemCompraRepository.save(existingItemCompra);
                });
    }

    @Override
    public Mono<Void> deleteItemCompra(Integer id) {
        return itemCompraRepository.deleteById(id);
    }
}