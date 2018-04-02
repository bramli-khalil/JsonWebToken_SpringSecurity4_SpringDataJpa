package shj.spring4jwtsecurity.demo.Service;


import shj.spring4jwtsecurity.demo.Model.MyUser;

public interface MyService {

  MyUser saveUser(MyUser user);

  void addRoleToUser(String userName, String roleName);

  MyUser findByUsername(String username);

}
