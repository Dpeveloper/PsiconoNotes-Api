package com.devcorp.psiconote.dtos;

import java.util.HashSet;
import java.util.List;

public record UsuarioToSaveDto(String email,
                               String username,
                               String password,
                               List<Long> roles) {}
