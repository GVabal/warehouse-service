package dev.vabalas.warehouseservice.controller;

import dev.vabalas.warehouseservice.dto.ItemDto;
import dev.vabalas.warehouseservice.entity.Item;
import dev.vabalas.warehouseservice.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("{id}")
    public Item getOne(@PathVariable Integer id) {
        return itemService.getOneItem(id);
    }

    @PostMapping
    public Item addOne(@RequestBody ItemDto itemDto) {
        return itemService.addOneItem(itemDto);
    }

    @PutMapping("{id}")
    public Item updateOne(@PathVariable Integer id, @RequestBody ItemDto itemDto) {
        return itemService.updateOneItem(id, itemDto);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable Integer id) {
        itemService.deleteOneItem(id);
    }

    @GetMapping("with/quantityLessThan/{amount}")
    public List<Item> getAllWithQuantityLessThan(@PathVariable Integer amount) {
        return itemService.getItemsWithQuantityLessThan(amount);
    }

    @GetMapping("with/expirationDateBefore/{date}")
    public List<Item> getAllWithExpirationDateBefore(@PathVariable String date) {
        return itemService.getItemsWithExpirationDateBefore(LocalDate.parse(date));
    }

}
