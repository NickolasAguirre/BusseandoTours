package idat.edu.pe.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.edu.pe.modelo.Conductor;

import idat.edu.pe.repositorio.ConductorRepository;
import javax.transaction.Transactional;


@Service
@Transactional
public class ConductorServiceImplement implements ConductorService{

	@Autowired
	private ConductorRepository repository;
	
	
	@Override
	public void insert(Conductor conductor) {
		// TODO Auto-generated method stub
		repository.save(conductor);
	}

	@Override
	public void update(Conductor conductor) {
		// TODO Auto-generated method stub
		repository.save(conductor);
	}

	@Override
	public void delete(Integer IdConductor) {
		// TODO Auto-generated method stub
		repository.deleteById(IdConductor);
	}

	@Override
	public Conductor findById(Integer IdConductor) {
		// TODO Auto-generated method stub
		return repository.findById(IdConductor).orElse(null);
	}

	@Override
	public Collection<Conductor> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
