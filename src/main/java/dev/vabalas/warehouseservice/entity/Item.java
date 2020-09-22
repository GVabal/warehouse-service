package dev.vabalas.warehouseservice.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QTY")
    private Integer quantity;

    @Column(name = "EXP_DATE")
    private LocalDate expirationDate;

    protected Item() { }

    public Item(String name, Integer quantity, LocalDate expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append('}');
        return sb.toString();
    }
}
