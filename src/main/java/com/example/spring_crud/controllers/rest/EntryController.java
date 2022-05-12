package com.example.spring_crud.controllers.rest;

import com.example.spring_crud.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {
    @Autowired
    EntryService entryService;

//    @GetMapping("/items")
//    public String items(Model model) {
//        Iterable<Entry> items = itemRepository.findAll();
//        model.addAttribute("items", items);
//        return "items";
//    }
//
//    @GetMapping("/item/{id}")
//    public EntryDto getItemById(final @PathVariable("id") Long id) {
//        return entryService.getItemById(id);
//    }
//
//
//    @GetMapping("/itemAdd")
//    public String blogItemAdd(Model model){
//        return "itemAdd";
//    }
//
//    @PostMapping("/itemAdd")
//    public String blogItemAdd(@PathVariable(value = "id") Long itemId,
//                              @RequestParam String heading,
//                              @RequestParam String body,
//                              @RequestParam LocalDateTime timeCreate,
//                              @RequestParam LocalDateTime timeUpdate,
//                              @RequestParam User user,
//                              @RequestParam List<Tag> tagList,
//                              Model model){
//        Entry item = new Entry(itemId, heading, body, timeCreate, timeUpdate, user, tagList);
//        itemRepository.save(item);
//        return "redirect:/items";
//    }
//
//    @GetMapping("/item/{id}/edit")
//    public String blogItemEdit(@PathVariable(value = "id") Long itemId, Model model) throws Exception {
//        if (!entryService.postByIdIsPresent(itemId)) {
//            return "redirect:/items";
//        }
//        model.addAttribute("post", entryService.getItemById(itemId));
//        return "itemEdit";
//    }
//
//    @PostMapping("/item/{id}/edit")
//    public String blogItemUpdate(@PathVariable(value = "id") Long postId,
//                                 @RequestParam String heading,
//                                 @RequestParam String body,
//                                 @RequestParam LocalDateTime timeCreate,
//                                 @RequestParam LocalDateTime timeUpdate,
//                                 @RequestParam User user,
//                                 @RequestParam List<Tag> tagList,
//                                 Model model){
//        Entry item = itemRepository.findById(postId).orElseThrow();
//        item.setHeading(heading);
//        item.setBody(body);
//        item.setTimeCreate(timeCreate);
//        item.setTimeUpdate(timeUpdate);
//        item.setUser(user);
//        item.setTagList(tagList);
//        itemRepository.save(item);
//
//        return "redirect:/items";
//    }
//
//    @PostMapping("/item/{id}/remove")
//    public String blogItemDelete(@PathVariable(value = "id") Long itemId, Model model){
//        Entry item = itemRepository.findById(itemId).orElseThrow();
//        itemRepository.delete(item);
//
//        return "redirect:/items";
//    }
}
