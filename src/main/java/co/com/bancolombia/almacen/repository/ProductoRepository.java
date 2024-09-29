package co.com.bancolombia.almacen.repository;

import co.com.bancolombia.almacen.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Integer> {
}