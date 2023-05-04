package idat.edu.pe.servicio;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;
import idat.edu.pe.repositorio.PasajeroRepository;

@Service
@Transactional
public class PasajeroServiceImplement implements PasajeroService{

	@Autowired
	private PasajeroRepository repository;
	

	@Override
	public void insert(Pasajero pasajero) {
		// TODO Auto-generated method stub
		repository.save(pasajero);
	}

	@Override
	public void update(Pasajero pasajero) {
		// TODO Auto-generated method stub
		repository.save(pasajero);
	}

	@Override
	public void delete(Integer IdPasajero) {
		// TODO Auto-generated method stub
		repository.deleteById(IdPasajero);
	}

	@Override
	public Pasajero findById(Integer IdPasajero) {
		// TODO Auto-generated method stub
		return repository.findById(IdPasajero).orElse(null);
	}

	@Override
	public Collection<Pasajero> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
}
