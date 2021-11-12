package com.centric.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String brand;

    @Transient
    @Getter
    @Setter
    private List<String> tags;

    @Getter
    @Setter
    @JsonIgnore
    private String tagArray;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private LocalDateTime createdAt;

}
