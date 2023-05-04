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
@Table(name = "Conductor")
public class Conductor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdConductor;

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
	private String TipoBrevete;

	@Column
	private String TipoDoc;

	@Column
	private Integer NumeroDoc;
	
	@ManyToMany(mappedBy = "itemsConductores")
	private List<Bus> itemsBuses = new ArrayList<>();

	public Conductor() {
		super();
	}

	public Conductor(Conductor conductor) {
		this(conductor.getIdConductor(), conductor.getNombre(), conductor.getApellidoPaterno(),
				conductor.getApellidoPaterno(), conductor.getTelefono(), conductor.getCorreo(),
				conductor.getTipoBrevete(), conductor.getTipoDoc(), conductor.getNumeroDoc());
	}

	public Conductor(Integer idConductor, String nombre, String apellidoPaterno, String apellidoMaterno,
			Integer telefono, String correo, String tipoBrevete, String tipoDoc, Integer numeroDoc) {
		super();
		IdConductor = idConductor;
		Nombre = nombre;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Telefono = telefono;
		Correo = correo;
		TipoBrevete = tipoBrevete;
		TipoDoc = tipoDoc;
		NumeroDoc = numeroDoc;
	}
	
	


	public Conductor(Integer idConductor, String nombre, String apellidoPaterno, String apellidoMaterno,
			Integer telefono, String correo, String tipoBrevete, String tipoDoc, Integer numeroDoc, List<Bus> itemsBuses) {
		super();
		IdConductor = idConductor;
		Nombre = nombre;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Telefono = telefono;
		Correo = correo;
		TipoBrevete = tipoBrevete;
		TipoDoc = tipoDoc;
		NumeroDoc = numeroDoc;
		this.itemsBuses = itemsBuses;
	}

	public Integer getIdConductor() {
		return IdConductor;
	}

	public void setIdConductor(Integer idConductor) {
		IdConductor = idConductor;
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

	public String getTipoBrevete() {
		return TipoBrevete;
	}

	public void setTipoBrevete(String tipoBrevete) {
		TipoBrevete = tipoBrevete;
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

	public List<Bus> getItemsBuses() {
		return itemsBuses;
	}

	public void setItemsBuses(List<Bus> itemsBuses) {
		this.itemsBuses = itemsBuses;
	}

	


}
