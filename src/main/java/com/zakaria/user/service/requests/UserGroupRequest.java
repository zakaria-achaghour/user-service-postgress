package com.zakaria.user.service.requests;

import com.zakaria.user.service.models.PermissionGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserGroupRequest {
    @NotEmpty
    @Size(min = 2)
    private String name;
    @NotEmpty
    @NotNull
    private ArrayList<UUID> permissionGroups;


}
