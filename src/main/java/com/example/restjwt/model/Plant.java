package com.example.restjwt.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plants")
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "family")
    private String family;

    @Column(name = "scientific_name")
    private String scientificName;

}
