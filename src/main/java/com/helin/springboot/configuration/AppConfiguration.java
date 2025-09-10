package com.helin.springboot.configuration;

import com.helin.springboot.entity.Role;
import com.helin.springboot.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AppConfiguration {

    private RoleRepository roleRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            roleRepository.findByRoleName("USER")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setRoleName("USER");
                        return roleRepository.save(r);
                    });

            roleRepository.findByRoleName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setRoleName("ADMIN");
                        return roleRepository.save(r);
                    });
        };
    }

}
