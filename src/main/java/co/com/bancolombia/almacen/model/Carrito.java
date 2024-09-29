package co.com.bancolombia.almacen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.MappedCollection;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Table("carritos")
public class Carrito {

    @Id
    private Integer id;

    @MappedCollection(idColumn = "carrito_id")
    private List<ItemCompra> items;

    private Double total;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}