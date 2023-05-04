package idat.edu.pe.servicio;

import java.util.Collection;

import idat.edu.pe.modelo.Conductor;

public interface ConductorService {

	public abstract void insert(Conductor conductor);
	public abstract void update(Conductor conductor);
	public abstract void delete(Integer IdConductor);
	public abstract Conductor findById(Integer IdConductor);
	public abstract Collection<Conductor> findAll();
}
