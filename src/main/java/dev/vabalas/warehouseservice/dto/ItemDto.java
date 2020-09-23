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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append('}');
        return sb.toString();
    }
}
