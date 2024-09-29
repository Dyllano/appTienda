package co.com.bancolombia.almacen.controller;

import co.com.bancolombia.almacen.model.ItemCompra;
import co.com.bancolombia.almacen.repository.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items-compra")
public class ItemCompraController {

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @GetMapping
    public Flux<ItemCompra> getAllItemsCompra() {
        return itemCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ItemCompra> getItemCompraById(@PathVariable Integer id) {
        return itemCompraRepository.findById(id);
    }

    @PostMapping
    public Mono<ItemCompra> createItemCompra(@RequestBody ItemCompra itemCompra) {
        return itemCompraRepository.save(itemCompra);
    }

    @PutMapping("/{id}")
    public Mono<ItemCompra> updateItemCompra(@PathVariable Integer id, @RequestBody ItemCompra itemCompra) {
        return itemCompraRepository.findById(id)
                .flatMap(existingItemCompra -> {
                    existingItemCompra.setProductoId(itemCompra.getProductoId());
                    existingItemCompra.setCantidad(itemCompra.getCantidad());
                    existingItemCompra.setPrecio(itemCompra.getPrecio());
                    existingItemCompra.setUpdatedAt(itemCompra.getUpdatedAt());
                    return itemCompraRepository.save(existingItemCompra);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteItemCompra(@PathVariable Integer id) {
        return itemCompraRepository.deleteById(id);
    }
}