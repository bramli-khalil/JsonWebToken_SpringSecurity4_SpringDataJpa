package shj.spring4jwtsecurity.demo.Service;

import org.springframework.stereotype.Component;
import shj.spring4jwtsecurity.demo.Model.MyUser;
import shj.spring4jwtsecurity.demo.Model.Post;
import shj.spring4jwtsecurity.demo.Model.Role;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Classe dédiée au remplissage de la base de données
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
@Component
public class DBSeeder {

  private MyService myService;

  public DBSeeder(MyService myService) {
    this.myService = myService;
  }

  @PostConstruct
  public void loadData(){
    Stream.of(new MyUser(null, "henri", "henri", new ArrayList<>()),
              new MyUser(null, "chloe", "chloe", new ArrayList<>()))
          .forEach(this.myService::saveUser);

    Stream.of(new Role(null,Role.ADMIN),
              new Role(null, Role.USER))
          .forEach(this.myService::saveRole);

    this.myService.addRoleToUser("henri", Role.ADMIN);
    this.myService.addRoleToUser("henri", Role.USER);
    this.myService.addRoleToUser("chloe", Role.USER);

    this.myService.savePost(new Post(null, "Post_1", "Contenu du post1",null), "henri");
    this.myService.savePost(new Post(null, "Post_2", "Contenu du post2",null), "chloe");

  }
}
