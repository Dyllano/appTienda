package co.com.bancolombia.almacen.service.impl;

import co.com.bancolombia.almacen.model.OrdenDeVenta;
import co.com.bancolombia.almacen.repository.OrdenDeVentaRepository;
import co.com.bancolombia.almacen.service.OrdenDeVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrdenDeVentaServiceImpl implements OrdenDeVentaService {

    @Autowired
    private OrdenDeVentaRepository ordenDeVentaRepository;

    @Override
    public Flux<OrdenDeVenta> getAllOrdenesDeVenta() {
        return ordenDeVentaRepository.findAll();
    }

    @Override
    public Mono<OrdenDeVenta> getOrdenDeVentaById(Integer id) {
        return ordenDeVentaRepository.findById(id);
    }

    @Override
    public Mono<OrdenDeVenta> createOrdenDeVenta(OrdenDeVenta ordenDeVenta) {
        return ordenDeVentaRepository.save(ordenDeVenta);
    }

    @Override
    public Mono<OrdenDeVenta> updateOrdenDeVenta(Integer id, OrdenDeVenta ordenDeVenta) {
        return ordenDeVentaRepository.findById(id)
                .flatMap(existingOrdenDeVenta -> {
                    existingOrdenDeVenta.setCarritoId(ordenDeVenta.getCarritoId());
                    existingOrdenDeVenta.setItems(ordenDeVenta.getItems());
                    existingOrdenDeVenta.setTotal(ordenDeVenta.getTotal());
                    existingOrdenDeVenta.setUpdatedAt(ordenDeVenta.getUpdatedAt());
                    return ordenDeVentaRepository.save(existingOrdenDeVenta);
                });
    }

    @Override
    public Mono<Void> deleteOrdenDeVenta(Integer id) {
        return ordenDeVentaRepository.deleteById(id);
    }
}