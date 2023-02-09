package com.example.prep1.domain.entities;

import com.example.prep1.domain.enums.ShipType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ShipType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(targetEntity = Ship.class, mappedBy = "category")
    private Set<Ship> ships;

    public Category() {}

    public Category(ShipType name) {
        this.name = name;
    }

    public ShipType getName() {
        return name;
    }

    public void setName(ShipType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Ship> getShips() {
        return ships;
    }
}
