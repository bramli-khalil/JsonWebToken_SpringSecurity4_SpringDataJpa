package shj.spring4jwtsecurity.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shj.spring4jwtsecurity.demo.Model.MyUser;

/**
 * Repository héritant de la classe JpaRepository
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

   MyUser findByUsername(String userName);
}
