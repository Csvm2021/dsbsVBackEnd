package certus.edu.pe.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productos")
@NamedQueries ({
    @NamedQuery(name = "Productos.findAdll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query="SELECT p FROM Productos p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Productos.findByNombre", query="SELECT p FROM Productos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Productos.findByProveedor", query="SELECT p FROM Productos p WHERE p.proveedor = :proveedor"),
    @NamedQuery(name = "Productos.findByPrecio", query="SELECT p FROM Productos p WHERE p.precio = :precio"),
    @NamedQuery(name = "Productos.findByDescripcion", query="SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByStock", query="SELECT p FROM Productos p WHERE p.stock = :stock")
})
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idproducto")
    private Integer idproducto;
    
    @Column(name ="nombre", nullable = false)
    private String nombre;
    
    @Column(name="proveedor", nullable = false)
    private String proveedor;
    
    @Column(name= "precio", nullable = false)
    private Double precio;
    
    @Column(name="descripcion", nullable = false)
    private String descripcion;
    
    @Column(name= "stock", nullable = false)
    private String stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoriasid")
    private Categorias categoriasid;
}