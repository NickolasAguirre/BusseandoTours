package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Conductor;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer>{

}
