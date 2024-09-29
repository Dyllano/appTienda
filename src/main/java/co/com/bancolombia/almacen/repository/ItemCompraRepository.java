package co.com.bancolombia.almacen.repository;

import co.com.bancolombia.almacen.model.ItemCompra;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends ReactiveCrudRepository<ItemCompra, Integer> {
}