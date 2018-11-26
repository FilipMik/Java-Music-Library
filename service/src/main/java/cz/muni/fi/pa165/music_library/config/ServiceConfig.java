package cz.muni.fi.pa165.music_library.config;

import cz.muni.fi.pa165.music_library.ApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Filip Mik on 14. 11. 2018
 */

@Configuration
@Import(ApplicationContext.class)
@ComponentScan({ "cz.muni.fi.pa165.music_library.service", "cz.muni.fi.pa165.music_library.facade"})
public class ServiceConfig {

    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
