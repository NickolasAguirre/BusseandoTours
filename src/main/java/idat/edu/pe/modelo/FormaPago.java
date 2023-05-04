package idat.edu.pe.modelo;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*CRUD*/
@Entity
@Table(name = "FormaPago")
public class FormaPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdFormaPago;
	
	@Column
	private String Descripcion;
	
	@Column
	private String Nombre;
	
	@OneToOne
	@JoinColumn(name = "id_ventas", nullable = true, unique = false, 
				foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_ventas) references cabecera(id_ventas)"))
	@JsonBackReference
	private Cabezera cabezera;
	
	
	public FormaPago() {
		super();
	}

	public FormaPago(Integer idFormaPago, String descripcion, String nombre) {
		super();
		IdFormaPago = idFormaPago;
		Descripcion = descripcion;
		Nombre = nombre;
	}

	



	public FormaPago(Integer idFormaPago, String descripcion, String nombre, Cabezera cabezera) {
		super();
		IdFormaPago = idFormaPago;
		Descripcion = descripcion;
		Nombre = nombre;
		this.cabezera = cabezera;
	}

	public Integer getIdFormaPago() {
		return IdFormaPago;
	}

	public void setIdFormaPago(Integer idFormaPago) {
		IdFormaPago = idFormaPago;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Cabezera getCabezera() {
		return cabezera;
	}

	public void setCabezera(Cabezera cabezera) {
		this.cabezera = cabezera;
	}


	
	

}
