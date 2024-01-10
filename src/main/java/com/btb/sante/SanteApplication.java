package com.btb.sante;

import aj.org.objectweb.asm.TypeReference;
import com.btb.sante.entity.Category;
import com.btb.sante.entity.Service;
import com.btb.sante.repository.CategoryRepository;
import com.btb.sante.repository.ServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableJpaAuditing
public class SanteApplication {
    int number = 0;
    private ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {
        SpringApplication.run(SanteApplication.class, args);
    }

    //@Bean
    public CommandLineRunner run(CategoryRepository categoryRepository, ServiceRepository serviceRepository) {
        return args -> {
            List<Service> services = IntStream.rangeClosed(1, 5)
                    .mapToObj(i -> Service.builder().nom("service " + i).build())
                    .peek(s-> System.out.println("service"+ s.toString()))
                    .toList();
            serviceRepository.saveAll(services);

            services.forEach(service -> IntStream.rangeClosed(1, 10)
                    .mapToObj(cat -> Category.builder()
                            .nom("Category "+this.number++)
                            .service(service)
                            .build())
                    .peek(c-> System.out.println("categories"+ c.toString()))
                    .forEach(categoryRepository::save));

        };

    }

}
