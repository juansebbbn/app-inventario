package com.juan.app_inventario.controllers.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;



public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @Valid AuthCreateRoleRequest roleRequest) {
}
