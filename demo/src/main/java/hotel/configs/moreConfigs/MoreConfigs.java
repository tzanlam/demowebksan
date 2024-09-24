package hotel.configs.moreConfigs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoreConfigs {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
