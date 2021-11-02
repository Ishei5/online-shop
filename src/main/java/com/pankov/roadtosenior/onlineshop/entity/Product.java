package com.pankov.roadtosenior.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Double price;
    private LocalDateTime creationDate;
    private String description;
}
