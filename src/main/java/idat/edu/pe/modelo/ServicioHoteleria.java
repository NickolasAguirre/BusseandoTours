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

@Entity
@Table(name = "ServicioHoteleria")
public class ServicioHoteleria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdServicioHoteleria;
	
	@Column
	private String descripcion;
	
	
	@Column
	private Integer DiasServicio;
	
	
	@Column
	private String Lugar;
	
	
	@Column
	private String Tipo;


	@ManyToMany(mappedBy = "itemsHoteleria")
	private List<Paquete> itemsPaquetes = new ArrayList<>();
	
	public ServicioHoteleria() {}


	public ServicioHoteleria(Integer idServicioHoteleria, String descripcion, Integer diasServicio, String lugar,
			String tipo) {
		super();
		this.IdServicioHoteleria = idServicioHoteleria;
		this.descripcion = descripcion;
		this.DiasServicio = diasServicio;
		this.Lugar = lugar;
		this.Tipo = tipo;
	}

	




	public ServicioHoteleria(Integer idServicioHoteleria, String descripcion, Integer diasServicio, String lugar,
			String tipo, List<Paquete> itemsPaquetes) {
		super();
		IdServicioHoteleria = idServicioHoteleria;
		this.descripcion = descripcion;
		DiasServicio = diasServicio;
		Lugar = lugar;
		Tipo = tipo;
		this.itemsPaquetes = itemsPaquetes;
	}


	public Integer getIdServicioHoteleria() {
		return IdServicioHoteleria;
	}


	public void setIdServicioHoteleria(Integer idServicioHoteleria) {
		IdServicioHoteleria = idServicioHoteleria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getDiasServicio() {
		return DiasServicio;
	}


	public void setDiasServicio(Integer diasServicio) {
		DiasServicio = diasServicio;
	}


	public String getLugar() {
		return Lugar;
	}


	public void setLugar(String lugar) {
		Lugar = lugar;
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
