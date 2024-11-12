package com.juan.app_inventario.controllers.dto;

import org.springframework.validation.annotation.Validated;
import java.util.List;
import javax.validation.constraints.Size;

@Validated
public record AuthCreateRoleRequest(
        @Size(max = 3, message = "The user cannot have more than 3 roles") List<String> roleListName) {
}
