package dev.vabalas.warehouseservice.dto;

import java.time.LocalDate;

public class ItemDto {

    private String name;
    private Integer quantity;
    private LocalDate expirationDate;

    public ItemDto() { }

    public ItemDto(String name, Integer quantity, LocalDate expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
