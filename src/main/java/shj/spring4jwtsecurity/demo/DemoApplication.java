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
public class DemoApplication{

  public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



}
