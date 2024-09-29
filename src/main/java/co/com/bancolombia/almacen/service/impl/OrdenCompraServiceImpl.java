package co.com.bancolombia.almacen.service.impl;

import co.com.bancolombia.almacen.model.OrdenCompra;
import co.com.bancolombia.almacen.repository.OrdenCompraRepository;
import co.com.bancolombia.almacen.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Override
    public Flux<OrdenCompra> getAllOrdenesDeCompra() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public Mono<OrdenCompra> getOrdenCompraById(Integer id) {
        return ordenCompraRepository.findById(id);
    }

    @Override
    public Mono<OrdenCompra> createOrdenCompra(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @Override
    public Mono<OrdenCompra> updateOrdenCompra(Integer id, OrdenCompra ordenCompra) {
        return ordenCompraRepository.findById(id)
                .flatMap(existingOrdenCompra -> {
                    existingOrdenCompra.setUsuarioId(ordenCompra.getUsuarioId());
                    existingOrdenCompra.setItems(ordenCompra.getItems());
                    existingOrdenCompra.setTotal(ordenCompra.getTotal());
                    existingOrdenCompra.setUpdatedAt(ordenCompra.getUpdatedAt());
                    return ordenCompraRepository.save(existingOrdenCompra);
                });
    }

    @Override
    public Mono<Void> deleteOrdenCompra(Integer id) {
        return ordenCompraRepository.deleteById(id);
    }
}