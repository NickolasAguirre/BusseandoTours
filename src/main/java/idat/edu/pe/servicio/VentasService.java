package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import idat.edu.pe.modelo.Cabezera;
import idat.edu.pe.modelo.Paquete;


public interface VentasService {
	
	public List<Cabezera> listAll();
	public List<Cabezera> listAll(String palabraClave);
	public abstract void insert(Cabezera cabezera);
	public abstract void update(Cabezera cabezera);
	public abstract void delete(Integer IdVentas);
	public abstract Cabezera findById(Integer IdVentas);
	public abstract Collection<Cabezera> findAll();
}
