package org.tpo3_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@ConfigurationPropertiesScan
public class FlashcardsApp {

    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(FlashcardsApp.class);

       FlashcardsController controller = context.getBean(FlashcardsController.class);
       controller.run();

    }

}
