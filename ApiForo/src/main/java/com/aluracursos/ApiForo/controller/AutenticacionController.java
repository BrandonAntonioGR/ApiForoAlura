package com.aluracursos.ApiForo.controller;

import com.aluracursos.ApiForo.dto.DatosAutenticacionUsuario;
import com.aluracursos.ApiForo.model.Usuario;
import com.aluracursos.ApiForo.repository.UsuarioRepository;
import com.aluracursos.ApiForo.security.DatosJWTToken;
import com.aluracursos.ApiForo.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")//pollo chan
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
    @PostMapping("/register")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Usuario usuario= new Usuario(datosAutenticacionUsuario.login(),"");
        var JWTtoken = tokenService.generarToken(usuario);
        Usuario usuarioConClave= new Usuario(datosAutenticacionUsuario.login(),JWTtoken);
        usuarioRepository.save(usuarioConClave);
        return ResponseEntity.ok().build();
    }

}
