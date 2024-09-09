package no.configuaration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigs {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
