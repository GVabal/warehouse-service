package dev.vabalas.warehouseservice.controller;

import dev.vabalas.warehouseservice.dto.ItemDto;
import dev.vabalas.warehouseservice.entity.Item;
import dev.vabalas.warehouseservice.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAll() {
        LOGGER.info("GET /items");
        return itemService.getAllItems();
    }

    @GetMapping("{id}")
    public Item getOne(@PathVariable Integer id) {
        LOGGER.info("GET /items/" + id);
        return itemService.getOneItem(id);
    }

    @PostMapping
    public Item addOne(@RequestBody @Valid ItemDto itemDto) {
        LOGGER.info("POST /items with body: " + itemDto);
        return itemService.addOneItem(itemDto);
    }

    @PutMapping("{id}")
    public Item updateOne(@PathVariable Integer id, @RequestBody ItemDto itemDto) {
        LOGGER.info("PUT /items/" + id + " with body: " + itemDto);
        return itemService.updateOneItem(id, itemDto);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable Integer id) {
        LOGGER.info("DELETE /items/" + id);
        itemService.deleteOneItem(id);
    }

    @GetMapping("withQuantityLessThan/{amount}")
    public List<Item> getAllWithQuantityLessThan(@PathVariable Integer amount) {
        LOGGER.info("GET /items/withQuantityLessThan/" + amount);
        return itemService.getItemsWithQuantityLessThan(amount);
    }

    @GetMapping("withExpirationDateBefore/{date}")
    public List<Item> getAllWithExpirationDateBefore(@PathVariable String date) {
        LOGGER.info("GET /items/withExpirationDateBefore/" + date);
        return itemService.getItemsWithExpirationDateBefore(LocalDate.parse(date));
    }

}
