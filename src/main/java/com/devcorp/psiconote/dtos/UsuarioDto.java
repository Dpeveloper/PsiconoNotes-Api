package com.devcorp.psiconote.dtos;

import java.util.List;

public record UsuarioDto(Long id,
                         String email,
                         String username,
                         String password) {}
