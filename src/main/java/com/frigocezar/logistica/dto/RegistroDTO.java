package com.frigocezar.logistica.dto;

import com.frigocezar.logistica.enums.UsuarioRole;

public record RegistroDTO(String login, String senha, UsuarioRole role) {
}
