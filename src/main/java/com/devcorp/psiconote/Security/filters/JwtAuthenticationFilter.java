package com.devcorp.psiconote.Security.filters;

import com.devcorp.psiconote.Security.jwt.JwtUtils;
import com.devcorp.psiconote.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.devcorp.psiconote.Security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Usuario usuario=null;
        String username="";
        String password="";

        try {
            usuario=new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            username=usuario.getUsername();
            password=usuario.getPassword();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user=(User)authResult.getPrincipal();
        String token=jwtUtils.generateToken(user.getUsername());

        response.addHeader("authorization",token);

        Map<String,Object> httpRequest=new HashMap<>();
        httpRequest.put("token",token);
        httpRequest.put("message","Autorización valida");
        httpRequest.put("username",user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpRequest));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
