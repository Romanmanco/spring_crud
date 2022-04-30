package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.model.dto.ItemDto;
import com.example.spring_crud.model.entity.Item;
import com.example.spring_crud.repository.ItemRepository;
import com.example.spring_crud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public String items(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/item/{id}")
    public ItemDto getItemById(final @PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }


    @GetMapping("/itemAdd")
    public String blogAdd(Model model){
        return "itemAdd";
    }

//    @PostMapping("/itemAdd")
//    public String blogItemAdd(@RequestParam String heading,
//                              @RequestParam String body,
//                              @RequestParam LocalDateTime timeCreate,
//                              @RequestParam LocalDateTime timeUpdate,
//                              @RequestParam User user,
//                              @RequestParam List<Tag> tagList,
//                              Model model){
//        Item item = new Item(heading, body, timeCreate, timeUpdate, user, tagList);
//        itemRepository.save(item);
//        return "redirect:/item";
//    }
}
