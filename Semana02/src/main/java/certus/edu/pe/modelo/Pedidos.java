package certus.edu.pe.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//Lombok
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name ="pedidos")
@NamedQueries({
	@NamedQuery(name="Pedidos.findAll", query ="SELECT f FROM Pedidos f")
	,@NamedQuery(name ="Pedidos.findByIdPedido", query = "SELECT f FROM Pedidos f WHERE f.idpedido = :idpedido")
	,@NamedQuery(name = "Pedidos.findFecha", query = "SELECT f FROM Pedidos f WHERE f.fecha = : fecha")
	,@NamedQuery(name = "Pedidos.findTotal", query = "SELECT f FROM Pedidos f WHERE f.total = : total")})

public class Pedidos implements Serializable {
	

	private static final long serialVersionUID=1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="idpedido")
	private Integer idpedido;
	
	@Basic(optional = false)
	@Column(name= "fecha")
	private String fecha;
	
	@Basic(optional = false)
	@Column(name="total")
	private String total;
	
	@JoinColumn(name = "clientesid", referencedColumnName = "idCliente")
	@ManyToOne(optional = false)
	private Clientes clientesid;
}