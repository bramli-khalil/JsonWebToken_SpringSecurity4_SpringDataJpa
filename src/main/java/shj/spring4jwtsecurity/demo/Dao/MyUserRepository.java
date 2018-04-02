package shj.spring4jwtsecurity.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shj.spring4jwtsecurity.demo.Model.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

  public MyUser findByUsername(String userName);
}
