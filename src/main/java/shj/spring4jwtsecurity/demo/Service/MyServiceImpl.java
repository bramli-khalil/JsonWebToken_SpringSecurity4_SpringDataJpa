package shj.spring4jwtsecurity.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shj.spring4jwtsecurity.demo.Dao.MyUserRepository;
import shj.spring4jwtsecurity.demo.Dao.RoleRepository;
import shj.spring4jwtsecurity.demo.Model.MyUser;
import shj.spring4jwtsecurity.demo.Model.Role;
import shj.spring4jwtsecurity.demo.Service.MyService;

/**
 * Implémentation de l'interface MyService
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
@Service
@Transactional
public class MyServiceImpl implements MyService {

  private MyUserRepository myUserRepository;

  private RoleRepository roleRepository;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public MyServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, MyUserRepository myUserRepository) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleRepository = roleRepository;
    this.myUserRepository = myUserRepository;
  }


  @Override
  public MyUser saveUser(MyUser user) {
    String password = user.getPassword();
    String encodePassword = this.bCryptPasswordEncoder.encode(password);

    user.setPassword(encodePassword);
    return this.myUserRepository.save(user);
  }

  @Override
  public void addRoleToUser(String userName, String roleName) {
    Role role = this.roleRepository.findByRoleName(roleName);
    MyUser user = this.myUserRepository.findByUsername(userName);
    user.getRoles().add(role);
  }

  @Override
  public MyUser findByUsername(String username) {
    return this.myUserRepository.findByUsername(username);
  }
}
