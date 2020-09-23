package dev.vabalas.warehouseservice.service;

import dev.vabalas.warehouseservice.entity.Item;
import dev.vabalas.warehouseservice.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    List<Item> items = List.of(
            new Item("Item 1", 1, LocalDate.parse("2000-12-31")),
            new Item("Item 2", 10, LocalDate.parse("2010-12-31")),
            new Item("Item 3", 100, LocalDate.parse("2020-12-31"))
    );
    Page<Item> itemsMock = new PageImpl<>(items, Pageable.unpaged(), items.size());

    @Mock
    private ItemRepository itemRepositoryMock;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        Mockito.when(itemRepositoryMock.findAll(Pageable.unpaged())).thenReturn(itemsMock);
    }

    @Test
    void returns_correct_number_of_items_with_quantity_less_than() {
        Assertions.assertEquals(
                itemService.getItemsWithQuantityLessThan(50, Pageable.unpaged()).getSize(),
                2);
    }

    @Test
    void returns_correct_number_of_items_with_expiration_date_before() {
        Assertions.assertEquals(
                itemService.getItemsWithExpirationDateBefore(LocalDate.parse("2015-12-31"), Pageable.unpaged()).getSize(),
                2);
    }
}
