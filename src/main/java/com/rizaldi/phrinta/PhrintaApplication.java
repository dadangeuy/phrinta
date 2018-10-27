package com.rizaldi.phrinta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "file:/var/phrinta/application.properties", ignoreResourceNotFound = true)
})
public class PhrintaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhrintaApplication.class, args);
    }
}
