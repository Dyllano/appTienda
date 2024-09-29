package co.com.bancolombia.almacen.controller;

import co.com.bancolombia.almacen.model.Producto;
import co.com.bancolombia.almacen.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public Flux<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Producto> getProductoById(@PathVariable Integer id) {
        return productoRepository.findById(id);
    }

    @PostMapping
    public Mono<Producto> createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Mono<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        return productoRepository.findById(id)
                .flatMap(existingProducto -> {
                    existingProducto.setName(producto.getName());
                    existingProducto.setPrice(producto.getPrice());
                    existingProducto.setDescription(producto.getDescription());
                    existingProducto.setImageUrl(producto.getImageUrl());
                    existingProducto.setStock(producto.getStock());
                    existingProducto.setUpdatedAt(producto.getUpdatedAt());
                    return productoRepository.save(existingProducto);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProducto(@PathVariable Integer id) {
        return productoRepository.deleteById(id);
    }
}