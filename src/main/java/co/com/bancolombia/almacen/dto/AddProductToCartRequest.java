package co.com.bancolombia.almacen.dto;

import lombok.Data;

@Data
public class AddProductToCartRequest {
    private int carritoId;
    private int productoId;
    private int cantidad;
}