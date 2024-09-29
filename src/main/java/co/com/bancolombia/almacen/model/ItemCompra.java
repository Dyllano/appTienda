package co.com.bancolombia.almacen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;
@Data
@Table("item_compra")
public class ItemCompra {

    @Id
    private Integer id;

    private Integer productoId;

    private Integer cantidad;

    private Double precio;

    private LocalDateTime updatedAt;

}