package com.zakaria.user.service.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="user_groups")
public class UserGroup extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<PermissionGroup> permissionGroups = new ArrayList<>();

}
