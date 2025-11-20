package Print3D.test.services;

import Print3D.test.model.Usuario;
import Print3D.test.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List <Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(int id, Usuario datosNuevos) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();

            usuarioExistente.setNombre(datosNuevos.getNombre());
            usuarioExistente.setEmail(datosNuevos.getEmail());
            usuarioExistente.setTelefono(datosNuevos.getTelefono());
            usuarioExistente.setContrasenia(datosNuevos.getContrasenia());
            usuarioExistente.setRegion(datosNuevos.getRegion());
            usuarioExistente.setComuna(datosNuevos.getComuna());
            usuarioExistente.setFechaCreacion(datosNuevos.getFechaCreacion());
            usuarioExistente.setRol(datosNuevos.getRol());

            return usuarioRepository.save(usuarioExistente);
        } else {
            return null;
        }
    }
    
    @Transactional
    public void deleteUsuario(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> findUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }
}
