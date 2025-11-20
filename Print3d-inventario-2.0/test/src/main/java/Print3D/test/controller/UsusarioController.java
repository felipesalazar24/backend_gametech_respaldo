package Print3D.test.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Print3D.test.model.Usuario;
import Print3D.test.services.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/usuarios")
public class UsusarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("")
    public ResponseEntity<Usuario> CreateUser(@RequestBody Usuario usuario){
        
        if( usuario.getNombre() == null || usuario.getNombre().isEmpty() ||
            usuario.getEmail() == null || usuario.getEmail().isEmpty() ||
            usuario.getContrasenia() == null || usuario.getContrasenia().isEmpty() ||
            usuario.getRegion() == null || usuario.getRegion().isEmpty() ||
            usuario.getComuna() == null || usuario.getComuna().isEmpty() ||
            usuario.getRol() == null || usuario.getRol().isEmpty()){
                return ResponseEntity.badRequest().build();
            }
        if (usuario.getTelefono() <=0) {
            usuario.setTelefono(0);
        }

        if (usuario.getFechaCreacion() == null) {
            usuario.setFechaCreacion(LocalDate.now());
        }

        Usuario usuarioCreado = usuarioService.addUsuario(usuario);

        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {
        return usuarioService.findUsuarioById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> updateUusario(@PathVariable("id") int id, @RequestBody Usuario usuarioAct) {
        
        Usuario actualizado = usuarioService.updateUsuario(id, usuarioAct);
        if (actualizado != null){
            return ResponseEntity.ok(actualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUsuarioByid(@PathVariable("id") int id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    
}