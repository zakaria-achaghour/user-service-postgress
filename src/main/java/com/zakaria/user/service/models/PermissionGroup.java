package com.zakaria.user.service.models;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="permission_groups")
public class PermissionGroup extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String permissions;
}
