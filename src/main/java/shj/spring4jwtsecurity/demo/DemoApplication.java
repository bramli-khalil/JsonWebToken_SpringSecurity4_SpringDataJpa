package shj.spring4jwtsecurity.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shj.spring4jwtsecurity.demo.Dao.RoleRepository;
import shj.spring4jwtsecurity.demo.Model.MyUser;
import shj.spring4jwtsecurity.demo.Model.Role;
import shj.spring4jwtsecurity.demo.Service.MyService;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

  private MyService myService;
  private RoleRepository roleRepository;

  @Autowired
  public DemoApplication(MyService myService,  RoleRepository roleRepository) {
    this.myService = myService;
    this.roleRepository = roleRepository;
  }

  public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @Override
  public void run(String... strings) throws Exception {

    Stream.of(new Role(null, Role.ADMIN), new Role(null, Role.USER)).forEach(role -> this.roleRepository.save(role));

    Stream.of(new MyUser(null, "henri","henri", new ArrayList<>()), new MyUser(null, "chloe", "chloe", new ArrayList<>()))
      .forEach(user -> this.myService.saveUser(user));

    this.myService.addRoleToUser("henri", Role.USER);
    this.myService.addRoleToUser("henri", Role.ADMIN);
    this.myService.addRoleToUser("chloe", Role.USER);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
	  return new BCryptPasswordEncoder();
  }

}
