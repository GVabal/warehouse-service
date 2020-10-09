package dev.vabalas.warehouseservice.repository;

import dev.vabalas.warehouseservice.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Page<Item> findAll(Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.quantity < ?1")
    Page<Item> findAllWithQuantityLessThan(Integer amount, Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.expirationDate < ?1")
    Page<Item> findAllWithExpirationDateBefore(LocalDate date, Pageable pageable);
}
