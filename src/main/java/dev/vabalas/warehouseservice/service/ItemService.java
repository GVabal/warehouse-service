package dev.vabalas.warehouseservice.service;

import dev.vabalas.warehouseservice.dto.ItemDto;
import dev.vabalas.warehouseservice.entity.Item;
import dev.vabalas.warehouseservice.error.ItemNotFoundException;
import dev.vabalas.warehouseservice.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<Item> getAllItems(Pageable pageable) {
        LOGGER.info("Getting all items");
        return itemRepository.findAll(pageable);
    }

    public Item getOneItem(Integer id) {
        LOGGER.info("Getting item with id " + id);
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("No item with id " + id));
    }

    public Item addOneItem(ItemDto itemDto) {
        LOGGER.info("Adding item " + itemDto);
        Item newItem = new Item(itemDto.getName(), itemDto.getQuantity(), itemDto.getExpirationDate());
        return itemRepository.save(newItem);
    }

    public Item updateOneItem(Integer id, ItemDto itemDto) {
        LOGGER.info("Updating item with id " + id + ". Updates: " + itemDto);
        return itemRepository.findById(id).map(item -> {
            if (itemDto.getName() != null)
                item.setName(itemDto.getName());
            if (itemDto.getQuantity() != null)
                item.setQuantity(itemDto.getQuantity());
            if (itemDto.getExpirationDate() != null)
                item.setExpirationDate(itemDto.getExpirationDate());
            return itemRepository.save(item);
        }).orElseGet(() -> {
            Item newItem = new Item(itemDto.getName(), itemDto.getQuantity(), itemDto.getExpirationDate());
            newItem.setId(id);
            return itemRepository.save(newItem);
        });
    }

    public void deleteOneItem(Integer id) {
        LOGGER.info("Deleting item with id " + id);
        itemRepository.deleteById(id);
    }

    public Page<Item> getItemsWithQuantityLessThan(Integer amount, Pageable pageable) {
        LOGGER.info("Getting items with quantity less than " + amount);
        List<Item> items = itemRepository.findAll(pageable).stream()
                .filter(item -> item.getQuantity() < amount)
                .collect(Collectors.toList());
        return new PageImpl<>(items, pageable, items.size());
    }

    public Page<Item> getItemsWithExpirationDateBefore(LocalDate date,  Pageable pageable) {
        LOGGER.info("Getting items with expiration date before " + date);
        List<Item> items =  itemRepository.findAll(pageable).stream()
                .filter(item -> item.getExpirationDate().isBefore(date))
                .collect(Collectors.toList());
        return new PageImpl<>(items, pageable, items.size());
    }
}
