package one.neopro.edu.neoproedu;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NeoproEduApplication {
    private static final Logger LOGGER = LogManager.getLogger(NeoproEduApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(NeoproEduApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public void run(ApplicationArguments applicationArguments) throws Exception {
        LOGGER.debug("Debugging log");
        LOGGER.info("Info log");
        LOGGER.warn("Hey, This is a warning!");
        LOGGER.error("Oops! We have an Error. OK");
        LOGGER.fatal("Damn! Fatal error. Please fix me.");
        LOGGER.info("start logger");
    }
}
