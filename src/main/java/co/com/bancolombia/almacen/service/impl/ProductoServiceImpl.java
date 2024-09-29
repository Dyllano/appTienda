package co.com.bancolombia.almacen.service.impl;

import co.com.bancolombia.almacen.model.Producto;
import co.com.bancolombia.almacen.repository.ProductoRepository;
import co.com.bancolombia.almacen.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Flux<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Mono<Producto> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Mono<Producto> createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Producto> updateProducto(Integer id, Producto producto) {
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

    @Override
    public Mono<Void> deleteProducto(Integer id) {
        return productoRepository.deleteById(id);
    }
}