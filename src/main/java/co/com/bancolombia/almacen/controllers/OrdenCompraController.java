package co.com.bancolombia.almacen.controller;

import co.com.bancolombia.almacen.model.OrdenCompra;
import co.com.bancolombia.almacen.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ordenes-de-compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @GetMapping
    public Flux<OrdenCompra> getAllOrdenesDeCompra() {
        return ordenCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<OrdenCompra> getOrdenCompraById(@PathVariable Integer id) {
        return ordenCompraRepository.findById(id);
    }

    @PostMapping
    public Mono<OrdenCompra> createOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @PutMapping("/{id}")
    public Mono<OrdenCompra> updateOrdenCompra(@PathVariable Integer id, @RequestBody OrdenCompra ordenCompra) {
        return ordenCompraRepository.findById(id)
                .flatMap(existingOrdenCompra -> {
                    existingOrdenCompra.setUsuarioId(ordenCompra.getUsuarioId());
                    existingOrdenCompra.setItems(ordenCompra.getItems());
                    existingOrdenCompra.setTotal(ordenCompra.getTotal());
                    existingOrdenCompra.setUpdatedAt(ordenCompra.getUpdatedAt());
                    return ordenCompraRepository.save(existingOrdenCompra);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteOrdenCompra(@PathVariable Integer id) {
        return ordenCompraRepository.deleteById(id);
    }
}