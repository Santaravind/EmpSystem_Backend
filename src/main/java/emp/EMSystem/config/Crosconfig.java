package emp.EMSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Crosconfig {



    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
       return  new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
             registry.addMapping("/**")
                     //.allowedOrigins("http://localhost:*")//it change by gpt
                     .allowedMethods("GET","POST","PUT","DELETE")
                     .allowedOrigins("*");
                     //.allowCredentials(true); //gpt
           }
       };
    }
}
