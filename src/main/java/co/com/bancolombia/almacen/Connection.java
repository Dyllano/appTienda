package co.com.bancolombia.almacen;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class Connection implements CommandLineRunner {

    private final DatabaseClient databaseClient;

    public Mono<Void> createTables() {
        String createProductoTable = "CREATE TABLE IF NOT EXISTS producto (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "price DOUBLE PRECISION," +
                "description TEXT," +
                "image_url TEXT," +
                "stock INT," +
                "updated_at TIMESTAMP" +
                ");";

        String createItemCompraTable = "CREATE TABLE IF NOT EXISTS item_compra (" +
                "id SERIAL PRIMARY KEY," +
                "producto_id INT REFERENCES producto(id)," +
                "carrito_id INT REFERENCES carrito(id)," +
                "cantidad INT," +
                "precio DOUBLE PRECISION," +
                "updated_at TIMESTAMP" +
                ");";

        String createCarritoTable = "CREATE TABLE IF NOT EXISTS carrito (" +
                "id SERIAL PRIMARY KEY," +
                "total DOUBLE PRECISION," +
                "created_at TIMESTAMP," +
                "updated_at TIMESTAMP" +
                ");";

        String createOrdenDeVentaTable = "CREATE TABLE IF NOT EXISTS orden_de_venta (" +
                "id SERIAL PRIMARY KEY," +
                "carrito_id INT REFERENCES carrito(id)," +
                "total DOUBLE PRECISION," +
                "created_at TIMESTAMP," +
                "updated_at TIMESTAMP" +
                ");";

        String createOrdenCompraTable = "CREATE TABLE IF NOT EXISTS orden_compra (" +
                "id SERIAL PRIMARY KEY," +
                "usuario_id INT," +
                "total DOUBLE PRECISION," +
                "created_at TIMESTAMP," +
                "updated_at TIMESTAMP" +
                ");";

        return databaseClient.sql(createProductoTable)
                .then()
                .then(databaseClient.sql(createItemCompraTable).then())
                .then(databaseClient.sql(createCarritoTable).then())
                .then(databaseClient.sql(createOrdenDeVentaTable).then())
                .then(databaseClient.sql(createOrdenCompraTable).then());
    }

    @Override
    public void run(String... args) throws Exception {
        createTables().subscribe();
    }
}