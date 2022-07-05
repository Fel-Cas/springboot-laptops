package com.store.technology;

import com.store.technology.entities.Laptop;
import com.store.technology.respositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TechnologyApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(TechnologyApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1= new Laptop(null,"HP",2019,1200.67,"Negro");
		Laptop laptop2= new Laptop(null,"DELL",2022,1800.67,"Gris");

		repository.save(laptop1);
		repository.save(laptop2);
	}

}
