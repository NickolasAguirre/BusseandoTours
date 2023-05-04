package idat.edu.pe.servicio;

import java.util.Collection;

import idat.edu.pe.modelo.Pasajero;

public interface PasajeroService {

	public abstract void insert(Pasajero pasajero);
	public abstract void update(Pasajero pasajero);
	public abstract void delete(Integer IdPasajero);
	public abstract Pasajero findById(Integer IdPasajero);
	public abstract Collection<Pasajero> findAll();
}
