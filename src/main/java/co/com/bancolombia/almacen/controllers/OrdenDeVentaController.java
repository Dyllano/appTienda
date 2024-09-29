package co.com.bancolombia.almacen.controller;

import co.com.bancolombia.almacen.model.OrdenDeVenta;
import co.com.bancolombia.almacen.repository.OrdenDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ordenes-de-venta")
public class OrdenDeVentaController {

    @Autowired
    private OrdenDeVentaRepository ordenDeVentaRepository;

    @GetMapping
    public Flux<OrdenDeVenta> getAllOrdenesDeVenta() {
        return ordenDeVentaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<OrdenDeVenta> getOrdenDeVentaById(@PathVariable Integer id) {
        return ordenDeVentaRepository.findById(id);
    }

    @PostMapping
    public Mono<OrdenDeVenta> createOrdenDeVenta(@RequestBody OrdenDeVenta ordenDeVenta) {
        return ordenDeVentaRepository.save(ordenDeVenta);
    }

    @PutMapping("/{id}")
    public Mono<OrdenDeVenta> updateOrdenDeVenta(@PathVariable Integer id, @RequestBody OrdenDeVenta ordenDeVenta) {
        return ordenDeVentaRepository.findById(id)
                .flatMap(existingOrdenDeVenta -> {
                    existingOrdenDeVenta.setCarritoId(ordenDeVenta.getCarritoId());
                    existingOrdenDeVenta.setItems(ordenDeVenta.getItems());
                    existingOrdenDeVenta.setTotal(ordenDeVenta.getTotal());
                    existingOrdenDeVenta.setUpdatedAt(ordenDeVenta.getUpdatedAt());
                    return ordenDeVentaRepository.save(existingOrdenDeVenta);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteOrdenDeVenta(@PathVariable Integer id) {
        return ordenDeVentaRepository.deleteById(id);
    }
}