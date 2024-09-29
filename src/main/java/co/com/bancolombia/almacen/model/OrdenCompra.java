package co.com.bancolombia.almacen.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ordenes_de_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "usuario_id")
    private int usuarioId;

    @OneToMany
    @JoinColumn(name = "orden_id")
    private List<ItemCompra> items;

    private double total;

    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime updatedAt;
}