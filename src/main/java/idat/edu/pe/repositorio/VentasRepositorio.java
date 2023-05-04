package idat.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Cabezera;
import idat.edu.pe.modelo.Paquete;

@Repository
public interface VentasRepositorio extends JpaRepository<Cabezera, Integer> {
	@Query("SELECT p FROM Cabezera p WHERE" + " CONCAT (p.FechaEmision)" + " LIKE %?1%")

	public List<Cabezera> findAll(String palabraClave);
}
