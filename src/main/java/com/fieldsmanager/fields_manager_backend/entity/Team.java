
package com.fieldsmanager.fields_manager_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Team() {}

    public Team(String name) { this.name = name; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
