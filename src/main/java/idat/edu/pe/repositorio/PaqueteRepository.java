package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Paquete;
import java.util.List;


@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Integer>{

	@Query("SELECT p FROM Paquete p WHERE" + " CONCAT (p.LugarDestino, p.FechaSalida, p.FechaRegreso)" +  " LIKE %?1%")
	
	public List<Paquete> findAll(String palabraClave);
}
