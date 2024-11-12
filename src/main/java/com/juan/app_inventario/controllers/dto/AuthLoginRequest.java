package com.juan.app_inventario.controllers.dto;

import javax.validation.constraints.NotBlank;


public record AuthLoginRequest(@NotBlank String username,
                               @NotBlank String password) {
}
