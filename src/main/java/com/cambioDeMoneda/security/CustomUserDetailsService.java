package com.cambioDeMoneda.security;

import com.cambioDeMoneda.entities.Rol;
import com.cambioDeMoneda.entities.Usuario;
import com.cambioDeMoneda.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private IUsuarioRepository usuarioRepository;
    @Autowired
    public CustomUserDetailsService(IUsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //Método para obtener una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAutorities(List<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }

    //Método para traer un usuario con todos sus datos
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuario.getUsername(), usuario.getPassword(), mapToAutorities(usuario.getRoles()));
    }
}
