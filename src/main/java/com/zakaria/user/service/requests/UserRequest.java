package com.zakaria.user.service.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    @NotEmpty
    @Size(min = 2)
    private String name;
    @NotNull
    private UUID userGroupId;


}
