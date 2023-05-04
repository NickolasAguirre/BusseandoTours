package idat.edu.pe.servicio;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.repositorio.PaqueteRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class PaqueteServiceImplement implements PaqueteService {
	
	
	
	@Autowired
	private PaqueteRepository repository;
	
	
	
	@Override
	public void insert(Paquete paquete) {
		// TODO Auto-generated method stub
		repository.save(paquete);
	}

	@Override
	public void update(Paquete paquete) {
		// TODO Auto-generated method stub
		repository.save(paquete);
	}

	@Override
	public void delete(Integer IdPaquete) {
		// TODO Auto-generated method stub
		repository.deleteById(IdPaquete);
	}

	@Override
	public Paquete findById(Integer IdPaquete) {
		// TODO Auto-generated method stub
		return repository.findById(IdPaquete).orElse(null);
	}

	@Override
	public Collection<Paquete> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	//para la busqueda
	public List<Paquete> listAll(String palabraClave) {
		
		if(palabraClave != null) {
			return repository.findAll(palabraClave);
		}
		return repository.findAll();
	}

}
