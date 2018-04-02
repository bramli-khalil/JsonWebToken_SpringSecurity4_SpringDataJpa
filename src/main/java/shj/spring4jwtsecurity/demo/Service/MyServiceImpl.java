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

@Service
@Transactional
public class MyServiceImpl implements MyService {
  @Autowired
  MyUserRepository myUserRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

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
