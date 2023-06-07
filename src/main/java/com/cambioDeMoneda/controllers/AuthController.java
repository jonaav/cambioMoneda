package com.cambioDeMoneda.controllers;

import com.cambioDeMoneda.dto.DtoAuthRespuesta;
import com.cambioDeMoneda.dto.DtoLogin;
import com.cambioDeMoneda.dto.DtoRegistro;
import com.cambioDeMoneda.entities.Rol;
import com.cambioDeMoneda.entities.Usuario;
import com.cambioDeMoneda.repository.IRolRepository;
import com.cambioDeMoneda.repository.IUsuarioRepository;
import com.cambioDeMoneda.security.JwtAuthenticationEntryPoint;
import com.cambioDeMoneda.security.JwtGenerador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(OperacionController.class);
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRolRepository rolRepository;
    private IUsuarioRepository usuarioRepository;
    private JwtGenerador jwtGenerador;
    @Autowired
    public  AuthController (AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRolRepository rolRepository,
                            IUsuarioRepository usuarioRepository, JwtGenerador jwtGenerador){
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.jwtGenerador = jwtGenerador;
    }

    @PostMapping("registro")
    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro){
        if(usuarioRepository.existsByUsername(dtoRegistro.getUsername())){
            return  new ResponseEntity<>("El usuario ya existe, Intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegistro.getUsername());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Rol roles = rolRepository.findByName("USER").get();
        usuario.setRoles(Collections.singletonList(roles));
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }


    @PostMapping("registroAdm")
    public ResponseEntity<String> registrarAdm(@RequestBody DtoRegistro dtoRegistro){
        if(usuarioRepository.existsByUsername(dtoRegistro.getUsername())){
            return  new ResponseEntity<>("El usuario ya existe, Intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dtoRegistro.getUsername());
        usuario.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Rol roles = rolRepository.findByName("ADMIN").get();
        usuario.setRoles(Collections.singletonList(roles));
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<DtoAuthRespuesta> login (@RequestBody DtoLogin dtoLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(),dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }

}
