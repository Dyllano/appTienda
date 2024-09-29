package co.com.bancolombia.almacen.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ordenes_de_venta")
public class OrdenDeVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "carrito_id")
    private int carritoId;

    @OneToMany
    @JoinColumn(name = "orden_id")
    private List<ItemCompra> items;

    private double total;

    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime updatedAt;
}