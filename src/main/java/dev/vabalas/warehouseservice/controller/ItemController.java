package dev.vabalas.warehouseservice.controller;

import dev.vabalas.warehouseservice.dto.ItemDto;
import dev.vabalas.warehouseservice.entity.Item;
import dev.vabalas.warehouseservice.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;
    private final ItemModelAssembler assembler;
    private final PagedResourcesAssembler<Item> pagedAssembler;

    public ItemController(ItemService itemService,
                          ItemModelAssembler assembler,
                          PagedResourcesAssembler<Item> pagedAssembler) {
        this.itemService = itemService;
        this.assembler = assembler;
        this.pagedAssembler = pagedAssembler;
    }

    @GetMapping
    public PagedModel<EntityModel<Item>> getAll(Pageable pageable) {
        LOGGER.info("GET /items");
        Page<Item> items = itemService.getAllItems(pageable);
        return pagedAssembler.toModel(items, assembler);
    }

    @GetMapping("{id}")
    public EntityModel<Item> getOne(@PathVariable Integer id) {
        LOGGER.info("GET /items/{}", id);
        return assembler.toModel(itemService.getOneItem(id));
    }

    @PostMapping
    public EntityModel<Item> addOne(@RequestBody @Valid ItemDto itemDto) {
        LOGGER.info("POST /items with body: {}", itemDto);
        return assembler.toModel(itemService.addOneItem(itemDto));
    }

    @PutMapping("{id}")
    public EntityModel<Item> updateOne(@PathVariable Integer id, @RequestBody ItemDto itemDto) {
        LOGGER.info("PUT /items/{} with body: {}", id, itemDto);
        return assembler.toModel(itemService.updateOneItem(id, itemDto));
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable Integer id) {
        LOGGER.info("DELETE /items/{}", id);
        itemService.deleteOneItem(id);
    }

    @GetMapping("withQuantityLessThan/{amount}")
    public PagedModel<EntityModel<Item>> getAllWithQuantityLessThan(@PathVariable Integer amount, Pageable pageable) {
        LOGGER.info("GET /items/withQuantityLessThan/{}", amount);
        return pagedAssembler.toModel(itemService.getItemsWithQuantityLessThan(amount, pageable), assembler);
    }

    @GetMapping("withExpirationDateBefore/{date}")
    public PagedModel<EntityModel<Item>> getAllWithExpirationDateBefore(@PathVariable String date, Pageable pageable) {
        LOGGER.info("GET /items/withExpirationDateBefore/{}", date);
        return pagedAssembler.toModel(itemService.getItemsWithExpirationDateBefore(LocalDate.parse(date), pageable), assembler);
    }

}
