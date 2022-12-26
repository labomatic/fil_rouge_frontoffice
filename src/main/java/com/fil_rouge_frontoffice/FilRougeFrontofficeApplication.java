package com.fil_rouge_frontoffice;

import com.fil_rouge_frontoffice.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class FilRougeFrontofficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilRougeFrontofficeApplication.class, args);
    }

}
