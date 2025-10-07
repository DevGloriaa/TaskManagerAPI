package com.example.taskmanagerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    private final Environment env;

    public WebConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {


                boolean isLocal = false;
                for (String profile : env.getActiveProfiles()) {
                    if (profile.equalsIgnoreCase("dev") || profile.equalsIgnoreCase("local")) {
                        isLocal = true;
                        break;
                    }
                }


                String[] allowedOrigins = isLocal
                        ? new String[]{"http://localhost:3000", "https://optimustask.vercel.app"}
                        : new String[]{"https://optimustask.vercel.app"};

                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("Authorization", "Content-Type")
                        .allowCredentials(true);
            }
        };
    }
}
