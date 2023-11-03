package mvc;

import lombok.extern.slf4j.Slf4j;
import mvc.service.entity.Role;
import mvc.service.entity.UserEntity;
import mvc.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class MvCshopApplication extends SpringBootServletInitializer implements CommandLineRunner  {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MvCshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity adminAccount = userRepository.findByRole(Role.ADMIN);
        if(null == adminAccount){
            UserEntity user = UserEntity.builder()
                .userId("admin")
                .userName("admin")
                .email("admin@gmail.com")
                .password(new BCryptPasswordEncoder().encode("ADMIN"))
                .role(Role.ADMIN)
                .build();
            userRepository.save(user);
            log.info("run");
        }
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MvCshopApplication.class);
    }

}
