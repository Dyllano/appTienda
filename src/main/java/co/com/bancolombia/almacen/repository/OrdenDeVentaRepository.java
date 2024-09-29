package co.com.bancolombia.almacen.repository;

import co.com.bancolombia.almacen.model.OrdenDeVenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeVentaRepository extends ReactiveCrudRepository<OrdenDeVenta, Integer> {
}