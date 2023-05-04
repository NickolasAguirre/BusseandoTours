package idat.edu.pe.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*CRUD*/
@Entity
@Table(name = "Pasajero")
public class Pasajero implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdPasajero;
	
	@NotEmpty
	@Column
	private String Nombre;
	
	
	@NotEmpty
	@Column
	private String ApellidoPaterno;
	
	@NotEmpty
	@Column
	private String ApellidoMaterno;
	
	@NotNull
	@Column
	private Integer Telefono;
	
	@NotEmpty
	@Column
	private String Correo;
	
	@NotEmpty
	@Column
	private String TipoDoc;
	
	@NotEmpty
	@Column
	private String NumDoc;
	
	@OneToMany(mappedBy = "pasajero",cascade = CascadeType.ALL)
	private List<Direccion> direccion	= new ArrayList<>();

	@OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL)
	private List<Cabezera> cabezera = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_paquete", nullable = true, unique = false, 
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)"))
	@JsonBackReference
    private Paquete paquete;
	
	
	
	public Pasajero() {}
	
	
	public Pasajero(Integer idPasajero, String nombre, String apellidoPaterno, String apellidoMaterno, Integer telefono,
			String correo, String tipoDoc, String numDoc, List<Direccion> direccion, List<Cabezera> cabezera) {
		super();
		IdPasajero = idPasajero;
		Nombre = nombre;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Telefono = telefono;
		Correo = correo;
		TipoDoc = tipoDoc;
		NumDoc = numDoc;
		this.direccion = direccion;
		this.cabezera = cabezera;
	}


	


	public Pasajero(Integer idPasajero, @NotEmpty String nombre, @NotEmpty String apellidoPaterno,
			@NotEmpty String apellidoMaterno, @NotNull Integer telefono, @NotEmpty String correo,
			@NotEmpty String tipoDoc, @NotEmpty String numDoc, List<Direccion> direccion, List<Cabezera> cabezera,
			Paquete paquete) {
		super();
		IdPasajero = idPasajero;
		Nombre = nombre;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Telefono = telefono;
		Correo = correo;
		TipoDoc = tipoDoc;
		NumDoc = numDoc;
		this.direccion = direccion;
		this.cabezera = cabezera;
		this.paquete = paquete;
	}


	public Pasajero(Integer idPasajero, String nombre, String apellidoPaterno, String apellidoMaterno, Integer telefono,
			String correo, String tipoDoc, String numDoc) {
		super();
		this.IdPasajero = idPasajero;
		this.Nombre = nombre;
		this.ApellidoPaterno = apellidoPaterno;
		this.ApellidoMaterno = apellidoMaterno;
		this.Telefono = telefono;
		this.Correo = correo;
		this.TipoDoc = tipoDoc;
		this.NumDoc = numDoc;
	}

	public Integer getIdPasajero() {
		return IdPasajero;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}

	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}

	public Integer getTelefono() {
		return Telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public String getTipoDoc() {
		return TipoDoc;
	}

	public String getNumDoc() {
		return NumDoc;
	}

	public void setIdPasajero(Integer idPasajero) {
		this.IdPasajero = idPasajero;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.ApellidoPaterno = apellidoPaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.ApellidoMaterno = apellidoMaterno;
	}

	public void setTelefono(Integer telefono) {
		this.Telefono = telefono;
	}

	public void setCorreo(String correo) {
		this.Correo = correo;
	}

	public void setTipoDoc(String tipoDoc) {
		this.TipoDoc = tipoDoc;
	}

	public void setNumDoc(String numDoc) {
		this.NumDoc = numDoc;
	}



	private List<Direccion> getDireccion() {
		return direccion;
	}





	private void setDireccion(List<Direccion> direccion) {
		this.direccion = direccion;
	}


	public List<Cabezera> getCabezera() {
		return cabezera;
	}


	public void setCabezera(List<Cabezera> cabezera) {
		this.cabezera = cabezera;
	}


	public Paquete getPaquete() {
		return paquete;
	}


	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}



	

	
	
	

}
