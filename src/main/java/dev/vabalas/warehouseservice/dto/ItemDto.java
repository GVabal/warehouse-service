package dev.vabalas.warehouseservice.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ItemDto {

    @NotNull
    private String name;
    @NotNull
    private Integer quantity;
    @NotNull
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
