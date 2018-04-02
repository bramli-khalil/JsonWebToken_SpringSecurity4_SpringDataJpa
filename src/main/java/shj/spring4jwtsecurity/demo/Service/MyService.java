package shj.spring4jwtsecurity.demo.Service;


import shj.spring4jwtsecurity.demo.Model.MyUser;

public interface MyService {

  public MyUser saveUser(MyUser user);

  public void addRoleToUser(String userName, String roleName);

  public MyUser findByUsername(String username);

}
