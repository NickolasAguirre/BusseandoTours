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
@Table(name = "Guia")
public class Guia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdGuiaTuristico;

	@Column
	private String Nombre;

	@Column
	private String ApellidoPaterno;

	@Column
	private String ApellidoMaterno;

	@Column
	private Integer Telefono;

	@Column
	private String Correo;

	@Column
	private String Direccion;

	@Column
	private String TipoDoc;

	@Column
	private Integer NumeroDoc;

	
	@ManyToMany(mappedBy = "itemsGuia")
	private List<Paquete> itemsPaquetes = new ArrayList<>();
	
	
	/*
	 * public Guia() { super(); }
	 */

	public Guia() {
		// TODO Auto-generated constructor stub
	}

	public Guia(Guia guia) {
		this(guia.getIdGuiaTuristico(), guia.getNombre(), guia.getApellidoPaterno(), guia.getApellidoMaterno(),
				guia.getTelefono(), guia.getCorreo(), guia.getDireccion(), guia.getTipoDoc(), guia.getNumeroDoc());
	}

	public Guia(Integer idGuiaTuristico, String nombre, String apellidoPaterno, String apellidoMaterno,
			Integer telefono, String correo, String direccion, String tipoDoc, Integer numeroDoc) {
		super();
		IdGuiaTuristico = idGuiaTuristico;
		Nombre = nombre;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Telefono = telefono;
		Correo = correo;
		Direccion = direccion;
		TipoDoc = tipoDoc;
		NumeroDoc = numeroDoc;
	}

	
	
	public Guia(Integer idGuiaTuristico, String nombre, String apellidoPaterno, String apellidoMaterno,
			Integer telefono, String correo, String direccion, String tipoDoc, Integer numeroDoc, List<Paquete> itemsPaquetes) {
		super();
		IdGuiaTuristico = idGuiaTuristico;
		Nombre = nombre;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Telefono = telefono;
		Correo = correo;
		Direccion = direccion;
		TipoDoc = tipoDoc;
		NumeroDoc = numeroDoc;
		this.itemsPaquetes = itemsPaquetes;
	}

	public Integer getIdGuiaTuristico() {
		return IdGuiaTuristico;
	}

	public void setIdGuiaTuristico(Integer idGuiaTuristico) {
		IdGuiaTuristico = idGuiaTuristico;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		ApellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		ApellidoMaterno = apellidoMaterno;
	}

	public Integer getTelefono() {
		return Telefono;
	}

	public void setTelefono(Integer telefono) {
		Telefono = telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTipoDoc() {
		return TipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		TipoDoc = tipoDoc;
	}

	public Integer getNumeroDoc() {
		return NumeroDoc;
	}

	public void setNumeroDoc(Integer numeroDoc) {
		NumeroDoc = numeroDoc;
	}

	public List<Paquete> getItemsPaquetes() {
		return itemsPaquetes;
	}

	public void setItemsPaquetes(List<Paquete> itemsPaquetes) {
		this.itemsPaquetes = itemsPaquetes;
	}

	

}
