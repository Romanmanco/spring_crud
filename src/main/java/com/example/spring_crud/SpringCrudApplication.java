package com.example.spring_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);

//		ConfigurableApplicationContext context = SpringApplication.run(SpringCrudApplication.class, args);
//		EntryRepository repository = context.getBean(EntryRepository.class);
//
//		// save a couple of item
////		repository.save(new Entry(2, "Заголовок", "Текст", LocalDateTime.now(), LocalDateTime.now(), 1));
//
//
//		// fetch all items
//		Iterable<Entry> items = repository.findAll();
//		System.out.println("Items found with findAll():");
//		System.out.println("-------------------------------");
//		for (Entry item : items) {
//			System.out.println(item);
//		}
//		System.out.println();
//
//		// fetch an individual item by ID
////		Entry item = repository.findOne(1L);
////		System.out.println("Entry found with findOne(1L):");
////		System.out.println("--------------------------------");
////		System.out.println(item);
////		System.out.println();
//
//		context.close();
	}
}

