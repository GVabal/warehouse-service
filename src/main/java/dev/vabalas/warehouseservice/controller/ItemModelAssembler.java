package dev.vabalas.warehouseservice.controller;

import dev.vabalas.warehouseservice.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {

    @Override
    public EntityModel<Item> toModel(Item item) {
        return EntityModel.of(item,
                linkTo(methodOn(ItemController.class).getOne(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).getAll(Pageable.unpaged())).withRel("items")
        );
    }

}
