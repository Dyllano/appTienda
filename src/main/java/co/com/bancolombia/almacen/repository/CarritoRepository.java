package co.com.bancolombia.almacen.repository;

import co.com.bancolombia.almacen.model.Carrito;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends ReactiveCrudRepository<Carrito, Integer> {
}