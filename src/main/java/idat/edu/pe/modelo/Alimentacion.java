package idat.edu.pe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*CRUD*/
@Entity
@Table(name = "Alimentacion")
public class Alimentacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdAlimentacion;
	
	@Column
	private Double Costo;
	
	@Column
	private String Dias;
	
	@Column
	private String Tipo;
	
	@ManyToMany(mappedBy = "itemsAlimentacion")
	private List<Paquete> itemsPaquetes = new ArrayList<>();
	

	public Alimentacion() {
	}

	public Alimentacion(Integer idAlimentacion, Double costo, String dias, String tipo) {
		super();
		this.IdAlimentacion = idAlimentacion;
		this.Costo = costo;
		this.Dias = dias;
		this.Tipo = tipo;
	}
	
	




	public Alimentacion(Integer idAlimentacion, Double costo, String dias, String tipo, List<Paquete> itemsPaquetes) {
		super();
		IdAlimentacion = idAlimentacion;
		Costo = costo;
		Dias = dias;
		Tipo = tipo;
		this.itemsPaquetes = itemsPaquetes;
	}

	public Integer getIdAlimentacion() {
		return IdAlimentacion;
	}

	public void setIdAlimentacion(Integer idAlimentacion) {
		IdAlimentacion = idAlimentacion;
	}

	public Double getCosto() {
		return Costo;
	}

	public void setCosto(Double costo) {
		Costo = costo;
	}

	public String getDias() {
		return Dias;
	}

	public void setDias(String dias) {
		Dias = dias;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public List<Paquete> getItemsPaquetes() {
		return itemsPaquetes;
	}

	public void setItemsPaquetes(List<Paquete> itemsPaquetes) {
		this.itemsPaquetes = itemsPaquetes;
	}

	


	
}
