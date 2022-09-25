package com.zakaria.user.service.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="user_group_id")
    private UserGroup group;
}
