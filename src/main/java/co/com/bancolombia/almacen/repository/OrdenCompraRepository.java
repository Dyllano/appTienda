package co.com.bancolombia.almacen.repository;

import co.com.bancolombia.almacen.model.OrdenCompra;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends ReactiveCrudRepository<OrdenCompra, Integer> {
}