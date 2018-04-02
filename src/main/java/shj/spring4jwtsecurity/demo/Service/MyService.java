package shj.spring4jwtsecurity.demo.Service;

import shj.spring4jwtsecurity.demo.Model.MyUser;

/**
 * Interface présentant les différents cas d'utilisation
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
public interface MyService {

  MyUser saveUser(MyUser user);

  void addRoleToUser(String userName, String roleName);

  MyUser findByUsername(String username);

}
