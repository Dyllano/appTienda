package co.com.bancolombia.almacen.service.impl;

import co.com.bancolombia.almacen.dto.AddProductToCartRequest;
import co.com.bancolombia.almacen.model.Carrito;
import co.com.bancolombia.almacen.model.ItemCompra;
import co.com.bancolombia.almacen.repository.CarritoRepository;
import co.com.bancolombia.almacen.repository.ItemCompraRepository;
import co.com.bancolombia.almacen.repository.ProductoRepository;
import co.com.bancolombia.almacen.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Override
    public Flux<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public Mono<Carrito> getCarritoById(Integer id) {
        return carritoRepository.findById(id);
    }

    @Override
    public Mono<Carrito> createCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public Mono<Carrito> updateCarrito(Integer id, Carrito carrito) {
        return carritoRepository.findById(id)
                .flatMap(existingCarrito -> {
                    existingCarrito.setItems(carrito.getItems());
                    existingCarrito.setTotal(carrito.getTotal());
                    existingCarrito.setUpdatedAt(carrito.getUpdatedAt());
                    return carritoRepository.save(existingCarrito);
                });
    }

    @Override
    public Mono<Void> deleteCarrito(Integer id) {
        return carritoRepository.deleteById(id);
    }

    @Override
    public Mono<Carrito> addProductToCart(AddProductToCartRequest request) {
        return carritoRepository.findById(request.getCarritoId())
                .flatMap(carrito -> productoRepository.findById(request.getProductoId())
                        .flatMap(producto -> {
                            ItemCompra itemCompra = new ItemCompra();
                            itemCompra.setProductoId(producto.getId());
                            itemCompra.setCantidad(request.getCantidad());
                            itemCompra.setPrecio(producto.getPrice() * request.getCantidad());
                            itemCompra.setUpdatedAt(LocalDateTime.now());
                            return itemCompraRepository.save(itemCompra)
                                    .flatMap(savedItem -> {
                                        carrito.getItems().add(savedItem);
                                        carrito.setTotal(carrito.getTotal() + savedItem.getPrecio());
                                        return carritoRepository.save(carrito);
                                    });
                        }));
    }

    @Override
    public Mono<Carrito> removeProductFromCart(Integer carritoId, Integer productoId) {
        return carritoRepository.findById(carritoId)
                .flatMap(carrito -> {
                    carrito.getItems().removeIf(item -> item.getProductoId().equals(productoId));
                    return carritoRepository.save(carrito);
                });
    }

    @Override
    public Mono<Carrito> updateProductQuantity(Integer carritoId, Integer productoId, Integer cantidad) {
        return carritoRepository.findById(carritoId)
                .flatMap(carrito -> {
                    carrito.getItems().stream()
                            .filter(item -> item.getProductoId().equals(productoId))
                            .forEach(item -> item.setCantidad(cantidad));
                    return carritoRepository.save(carrito);
                });
    }

    @Override
    public Mono<Carrito> getCartContents(Integer carritoId) {
        return carritoRepository.findById(carritoId);
    }

    @Override
    public Mono<Void> emptyCart(Integer carritoId) {
        return carritoRepository.findById(carritoId)
                .flatMap(carrito -> {
                    carrito.getItems().clear();
                    return carritoRepository.save(carrito).then();
                });
    }

    @Override
    public Mono<Double> calculateTotal(Integer carritoId) {
        return carritoRepository.findById(carritoId)
                .map(carrito -> carrito.getItems().stream()
                        .mapToDouble(ItemCompra::getPrecio)
                        .sum());
    }

    @Override
    public Mono<Void> registerOrder(Integer carritoId) {
        // Implementar lógica para registrar la orden de venta
        return Mono.empty();
    }
}