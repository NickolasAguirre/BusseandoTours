package idat.edu.pe.servicio;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Cabezera;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.repositorio.PasajeroRepository;
import idat.edu.pe.repositorio.VentasRepositorio;

@Service
@Transactional
public class VentasServiceImplement implements VentasService{

	@Autowired
	private VentasRepositorio repository;
	
	@Override
	public void insert(Cabezera cabezera) {
		// TODO Auto-generated method stub
		repository.save(cabezera);
	}

	@Override
	public void update(Cabezera cabezera) {
		// TODO Auto-generated method stub
		repository.save(cabezera);
	}

	@Override
	public void delete(Integer IdVentas) {
		// TODO Auto-generated method stub
		repository.deleteById(IdVentas);
	}

	@Override
	public Cabezera findById(Integer IdVentas) {
		// TODO Auto-generated method stub
		return repository.findById(IdVentas).orElse(null);
	}

	@Override
	public Collection<Cabezera> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Cabezera> listAll() {
		// TODO Auto-generated method stub
		return (List<Cabezera>) repository.findAll() ;
	}

	@Override
	public List<Cabezera> listAll(String palabraClave) {
		if(palabraClave != null) {
			return repository.findAll(palabraClave);
		}
		return repository.findAll();
	}

	
	 
}
