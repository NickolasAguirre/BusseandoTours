package idat.edu.pe.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/*CRUD*/
@Entity
@Table(name = "Paquete")
public class Paquete implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdPaquete;

	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate FechaSalida;

	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate FechaRegreso;

	@NotEmpty
	@Column
	private String LugarSalida;

	@NotEmpty
	@Column
	private String LugarDestino;

	@Column
	@NotEmpty
	private String Descripcion;

	@Column
	@NotEmpty
	private String Estado;

	@Column
	@NotNull
	private Double PrecioUnidad;

	private String imagen;

	@ManyToMany
	@JoinTable(name = "paquetes_buses", joinColumns = @JoinColumn(name = "id_paquete", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)")),

			inverseJoinColumns = @JoinColumn(name = "id_bus", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_bus) references Bus(id_bus)"))

	)
	@JsonBackReference
	private List<Bus> itemsBuses = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "paquetes_hoteleria", joinColumns = @JoinColumn(name = "id_paquete", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)")),

			inverseJoinColumns = @JoinColumn(name = "id_servicio_hoteleria", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_servicio_hoteleria) references servicio_hoteleria(id_servicio_hoteleria)"))

	)
	@JsonBackReference
	private List<ServicioHoteleria> itemsHoteleria = new ArrayList<>();

	public Paquete(LocalDate fechaSalida, LocalDate fechaRegreso, String lugarSalida, String lugarDestino,
			String descripcion, Double precioUnidad, String imagen, List<Bus> itemsBuses,
			List<ServicioHoteleria> itemsHoteleria, List<Alimentacion> itemsAlimentacion, List<Guia> itemsGuia,
			List<Cabezera> cabezera) {
		super();
		FechaSalida = fechaSalida;
		FechaRegreso = fechaRegreso;
		LugarSalida = lugarSalida;
		LugarDestino = lugarDestino;
		Descripcion = descripcion;
		PrecioUnidad = precioUnidad;
		this.imagen = imagen;
		this.itemsBuses = itemsBuses;
		this.itemsHoteleria = itemsHoteleria;
		this.itemsAlimentacion = itemsAlimentacion;
		this.itemsGuia = itemsGuia;
		this.cabezera = cabezera;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@ManyToMany
	@JoinTable(name = "paquetes_alimentacion", joinColumns = @JoinColumn(name = "id_paquete", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)")),

			inverseJoinColumns = @JoinColumn(name = "id_alimentacion", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_alimentacion) references alimentacion(id_alimentacion)"))

	)
	@JsonBackReference
	private List<Alimentacion> itemsAlimentacion = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "paquetes_guia", joinColumns = @JoinColumn(name = "id_paquete", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)")),

			inverseJoinColumns = @JoinColumn(name = "id_guia_turistico", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_guia_turistico) references guia(id_guia_turistico)"))

	)
	@JsonBackReference
	private List<Guia> itemsGuia = new ArrayList<>();

	@OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL)
	private List<Cabezera> cabezera = new ArrayList<>();

	@OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL)
	private List<Pasajero> pasajero = new ArrayList<>();

	public Paquete() {
	}

	public Paquete(Integer idPaquete, LocalDate fechaSalida, LocalDate fechaRegreso, String lugarSalida,
			String lugarDestino, String descripcion, Double precioUnidad) {
		super();
		this.IdPaquete = idPaquete;
		this.FechaSalida = fechaSalida;
		this.FechaRegreso = fechaRegreso;
		this.LugarSalida = lugarSalida;
		this.LugarDestino = lugarDestino;
		this.Descripcion = descripcion;
		this.PrecioUnidad = precioUnidad;
	}

	public Paquete(Integer idPaquete, LocalDate fechaSalida, LocalDate fechaRegreso, @NotEmpty String lugarSalida,
			@NotEmpty String lugarDestino, @NotEmpty String descripcion, @NotEmpty String estado,
			@NotNull Double precioUnidad, String imagen, List<Bus> itemsBuses,
			List<ServicioHoteleria> itemsHoteleria, List<Alimentacion> itemsAlimentacion, List<Guia> itemsGuia, List<Cabezera> cabezera, List<Pasajero> pasajero) {
		super();
		IdPaquete = idPaquete;
		FechaSalida = fechaSalida;
		FechaRegreso = fechaRegreso;
		LugarSalida = lugarSalida;
		LugarDestino = lugarDestino;
		Descripcion = descripcion;
		Estado = estado;
		PrecioUnidad = precioUnidad;
		this.imagen = imagen;
		this.itemsBuses = itemsBuses;
		this.itemsHoteleria = itemsHoteleria;
		this.itemsAlimentacion = itemsAlimentacion;
		this.itemsGuia = itemsGuia;
		this.cabezera = cabezera;
		this.pasajero = pasajero;
	}

	public Paquete(Integer idPaquete, LocalDate fechaSalida, LocalDate fechaRegreso, String lugarSalida,
			String lugarDestino, String descripcion, Double precioUnidad, List<Bus> itemsBuses,
			List<ServicioHoteleria> itemsHoteleria, List<Alimentacion> itemsAlimentacion, List<Guia> itemsGuia,
			List<Cabezera> cabezera) {
		super();
		IdPaquete = idPaquete;
		FechaSalida = fechaSalida;
		FechaRegreso = fechaRegreso;
		LugarSalida = lugarSalida;
		LugarDestino = lugarDestino;
		Descripcion = descripcion;
		PrecioUnidad = precioUnidad;
		this.itemsBuses = itemsBuses;
		this.itemsHoteleria = itemsHoteleria;
		this.itemsAlimentacion = itemsAlimentacion;
		this.itemsGuia = itemsGuia;
		this.cabezera = cabezera;
	}

	public Integer getIdPaquete() {
		return IdPaquete;
	}

	public void setIdPaquete(Integer idPaquete) {
		IdPaquete = idPaquete;
	}

	public LocalDate getFechaSalida() {
		return FechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		FechaSalida = fechaSalida;
	}

	public LocalDate getFechaRegreso() {
		return FechaRegreso;
	}

	public void setFechaRegreso(LocalDate fechaRegreso) {
		FechaRegreso = fechaRegreso;
	}

	public String getLugarSalida() {
		return LugarSalida;
	}

	public void setLugarSalida(String lugarSalida) {
		LugarSalida = lugarSalida;
	}

	public String getLugarDestino() {
		return LugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		LugarDestino = lugarDestino;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Double getPrecioUnidad() {
		return PrecioUnidad;
	}

	public void setPrecioUnidad(Double precioUnidad) {
		PrecioUnidad = precioUnidad;
	}

	public List<Bus> getItemsBuses() {
		return itemsBuses;
	}

	public void setItemsBuses(List<Bus> itemsBuses) {
		this.itemsBuses = itemsBuses;
	}

	public List<ServicioHoteleria> getItemsHoteleria() {
		return itemsHoteleria;
	}

	public void setItemsHoteleria(List<ServicioHoteleria> itemsHoteleria) {
		this.itemsHoteleria = itemsHoteleria;
	}

	public List<Alimentacion> getItemsAlimentacion() {
		return itemsAlimentacion;
	}

	public void setItemsAlimentacion(List<Alimentacion> itemsAlimentacion) {
		this.itemsAlimentacion = itemsAlimentacion;
	}

	public List<Guia> getItemsGuia() {
		return itemsGuia;
	}

	public void setItemsGuia(List<Guia> itemsGuia) {
		this.itemsGuia = itemsGuia;
	}

	public Paquete(LocalDate fechaSalida, LocalDate fechaRegreso, @NotEmpty String lugarSalida,
			@NotEmpty String lugarDestino, @NotEmpty String descripcion, @NotEmpty String estado,
			@NotNull Double precioUnidad, String imagen, List<Bus> itemsBuses, List<ServicioHoteleria> itemsHoteleria,
			List<Alimentacion> itemsAlimentacion, List<Guia> itemsGuia, List<Cabezera> cabezera) {
		super();
		FechaSalida = fechaSalida;
		FechaRegreso = fechaRegreso;
		LugarSalida = lugarSalida;
		LugarDestino = lugarDestino;
		Descripcion = descripcion;
		Estado = estado;
		PrecioUnidad = precioUnidad;
		this.imagen = imagen;
		this.itemsBuses = itemsBuses;
		this.itemsHoteleria = itemsHoteleria;
		this.itemsAlimentacion = itemsAlimentacion;
		this.itemsGuia = itemsGuia;
		this.cabezera = cabezera;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<Cabezera> getCabezera() {
		return cabezera;
	}

	public void setCabezera(List<Cabezera> cabezera) {
		this.cabezera = cabezera;
	}

	public List<Pasajero> getPasajero() {
		return pasajero;
	}

	public void setPasajero(List<Pasajero> pasajero) {
		this.pasajero = pasajero;
	}

}
