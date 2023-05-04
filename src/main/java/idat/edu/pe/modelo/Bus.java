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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*CRUD*/
@Entity
@Table(name = "Bus")
public class Bus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdBus;

	@Column
	private String Placa;

	@Column
	private String Tipo;

	
	@ManyToMany
	@JoinTable(name = "bus_conductor",
				joinColumns = @JoinColumn(name = "id_bus", nullable = false, 
				foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_bus) references Bus(id_bus)")),
				
				inverseJoinColumns = @JoinColumn(name = "id_conductor", nullable = false, 
				foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_conductor) references Conductor(id_conductor)"))
	
	)
	@JsonBackReference
	private List<Conductor> itemsConductores = new ArrayList<>();
	
	@ManyToMany(mappedBy = "itemsBuses")
	private List<Paquete> itemsPaquetes = new ArrayList<>();


	public Bus() {
		super();
	}

	public Bus(Bus bus) {
		this(bus.getIdBus(), bus.getPlaca(), bus.getTipo());
	}

	public Bus(Integer idBus, String placa, String tipo) {
		super();
		IdBus = idBus;
		Placa = placa;
		Tipo = tipo;
	}
	

	

	

	public Bus(Integer idBus, String placa, String tipo, List<Conductor> itemsConductores,
			List<Paquete> itemsPaquetes) {
		super();
		IdBus = idBus;
		Placa = placa;
		Tipo = tipo;
		this.itemsConductores = itemsConductores;
		this.itemsPaquetes = itemsPaquetes;
	}

	public Integer getIdBus() {
		return IdBus;
	}

	public void setIdBus(Integer idBus) {
		IdBus = idBus;
	}

	public String getPlaca() {
		return Placa;
	}

	public void setPlaca(String placa) {
		Placa = placa;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public List<Conductor> getItemsConductores() {
		return itemsConductores;
	}

	public void setItemsConductores(List<Conductor> itemsConductores) {
		this.itemsConductores = itemsConductores;
	}

	public List<Paquete> getItemsPaquetes() {
		return itemsPaquetes;
	}

	public void setItemsPaquetes(List<Paquete> itemsPaquetes) {
		this.itemsPaquetes = itemsPaquetes;
	}

	




	
}
