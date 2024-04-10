package org.firewall.config;

import org.firewall.model.entity.CustomRules;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicReference;

@Configuration
public class Config {

    @Bean
    public AtomicReference<CustomRules> customRules() {
        return new AtomicReference<>();
    }
}
