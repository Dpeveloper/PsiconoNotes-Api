package com.devcorp.psiconote.Security.service;

import com.devcorp.psiconote.entities.Usuario;
import com.devcorp.psiconote.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("El usuario "+username+"no existe"));

        Collection<? extends GrantedAuthority> authorities=usuario.getRoles().stream()
                .map(rol->new SimpleGrantedAuthority("ROLE_".concat(rol.getNombre())))
                .collect(Collectors.toSet());

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
