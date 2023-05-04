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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*CRUD*/
@Entity
@Table(name = "Direccion")
public class Direccion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdDireccion;
	
	@Column
	private String  Distrito;


	
	@ManyToOne
	@JoinColumn(name = "id_pasajero", nullable = true, unique = false, 
				foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_pasajero) references Pasajero(id_pasajero)"))
	@JsonBackReference
	private Pasajero pasajero;
	
	public Direccion() {}
	
	public Direccion(Integer idDireccion, String distrito) {
		super();
		this.IdDireccion = idDireccion;
		this.Distrito = distrito;
	}

	public Integer getIdDireccion() {
		return IdDireccion;
	}

	public String getDistrito() {
		return Distrito;
	}

	public void setIdDireccion(Integer idDireccion) {
		this.IdDireccion = idDireccion;
	}

	public void setDistrito(String distrito) {
		this.Distrito = distrito;
	}
	
	

}
