package dev.vabalas.warehouseservice.service;

import dev.vabalas.warehouseservice.dto.ItemDto;
import dev.vabalas.warehouseservice.entity.Item;
import dev.vabalas.warehouseservice.error.ItemNotFoundException;
import dev.vabalas.warehouseservice.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public List<Item> getAllItems() {
        LOGGER.info("getAllItems()");
        return itemRepository.findAll();
    }

    public Item getOneItem(Integer id) {
        LOGGER.info("getOneItem(id)");
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("No item with id " + id));
    }

    public Item addOneItem(ItemDto itemDto) {
        LOGGER.info("addOneItem(itemDto)");
        Item newItem = new Item(itemDto.getName(), itemDto.getQuantity(), itemDto.getExpirationDate());
        return itemRepository.save(newItem);
    }

    public Item updateOneItem(Integer id, ItemDto itemDto) {
        LOGGER.info("updateOneItem(id, itemDto)");
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
        LOGGER.info("deleteOneItem(id)");
        itemRepository.deleteById(id);
    }

    public List<Item> getItemsWithQuantityLessThan(Integer amount) {
        LOGGER.info("getItemsWithQuantityLessThan(amount)");
        return itemRepository.findAll().stream()
                .filter(item -> item.getQuantity() < amount)
                .collect(Collectors.toList());
    }

    public List<Item> getItemsWithExpirationDateBefore(LocalDate date) {
        LOGGER.info("getItemsWithExpirationDateBefore(date)");
        return itemRepository.findAll().stream()
                .filter(item -> item.getExpirationDate().isBefore(date))
                .collect(Collectors.toList());
    }
}
