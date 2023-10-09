package com.lemoncat.tbtasksrservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "icon")
    private String icon;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;
}
