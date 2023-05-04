package idat.edu.pe.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import idat.edu.pe.controlador.dto.UsuarioRegistroDTO;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Usuario;




public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
	public Optional<Usuario> findById(Long id);

	
	
	
}
