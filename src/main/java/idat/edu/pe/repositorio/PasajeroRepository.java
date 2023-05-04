package idat.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idat.edu.pe.modelo.Alimentacion;
import idat.edu.pe.modelo.Pasajero;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Integer>{

}
