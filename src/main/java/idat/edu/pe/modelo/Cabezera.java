package idat.edu.pe.modelo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "Cabecera")
public class Cabezera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdVentas;
	
	@Column
	private String Nombre;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate FechaEmision;
	
	@Column
	private String TipoDoc;
	
	@Column
	private Integer NumeroDoc;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_pasajero", nullable = true, unique = false, 
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_pasajero) references Pasajero(id_pasajero)"))
	@JsonBackReference
    private Pasajero pasajero;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_venta",
               joinColumns = @JoinColumn(name = "id_ventas"),
               inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Usuario> usuario = new ArrayList<>();
	
	
	@ManyToOne
	@JoinColumn(name = "id_paquete", nullable = true, unique = false, 
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)"))
	@JsonBackReference
    private Paquete paquete;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ventas")
    private List<Usuario> usuarios = new ArrayList<>();

	public Cabezera() {
		super();
	}

	public Cabezera(Integer idVentas, String nombre, LocalDate fechaEmision, String tipoDoc, Integer numeroDoc) {
		super();
		IdVentas = idVentas;
		Nombre = nombre;
		FechaEmision = fechaEmision;
		TipoDoc = tipoDoc;
		NumeroDoc = numeroDoc;
	}

	

	

	public Cabezera(Integer idVentas, String nombre, LocalDate fechaEmision, String tipoDoc, Integer numeroDoc,
			Pasajero pasajero, List<Usuario> usuario, Paquete paquete) {
		super();
		IdVentas = idVentas;
		Nombre = nombre;
		FechaEmision = fechaEmision;
		TipoDoc = tipoDoc;
		NumeroDoc = numeroDoc;
		this.pasajero = pasajero;
		this.usuario = usuario;
		this.paquete = paquete;
	}

	public Integer getIdVentas() {
		return IdVentas;
	}

	public void setIdVentas(Integer idVentas) {
		IdVentas = idVentas;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public LocalDate getFechaEmision() {
		return FechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		FechaEmision = fechaEmision;
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


	
	
	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}


	

	

}
