package com.company.beatsmebackend;

import com.company.beatsmebackend.repository.SongRepository;
import com.company.beatsmebackend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.company"})
public class BeatsMeBackendApplication {


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BeatsMeBackendApplication.class, args);
		StorageService storageService = applicationContext.getBean(StorageService.class);
		storageService.getFilenames();
		System.out.println(storageService.getFilenames());
	}

}
