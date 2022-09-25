package com.zakaria.user.service.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PermissionGroupRequest {

    @NotEmpty
    @Size(min = 2)
    private String name;
    @NotEmpty
    private List<String> permissions;
}
