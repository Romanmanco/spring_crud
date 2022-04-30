package com.example.spring_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);

//		ConfigurableApplicationContext context = SpringApplication.run(SpringCrudApplication.class, args);
//		ItemRepository repository = context.getBean(ItemRepository.class);
//
//		// save a couple of item
////		repository.save(new Item(2, "Заголовок", "Текст", LocalDateTime.now(), LocalDateTime.now(), 1));
//
//
//		// fetch all items
//		Iterable<Item> items = repository.findAll();
//		System.out.println("Items found with findAll():");
//		System.out.println("-------------------------------");
//		for (Item item : items) {
//			System.out.println(item);
//		}
//		System.out.println();
//
//		// fetch an individual item by ID
////		Item item = repository.findOne(1L);
////		System.out.println("Item found with findOne(1L):");
////		System.out.println("--------------------------------");
////		System.out.println(item);
////		System.out.println();
//
//		context.close();
	}
}

