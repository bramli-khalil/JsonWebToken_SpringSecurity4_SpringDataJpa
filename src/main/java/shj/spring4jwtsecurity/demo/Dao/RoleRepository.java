package shj.spring4jwtsecurity.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shj.spring4jwtsecurity.demo.Model.Role;

/**
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

  Role findByRoleName(String roleName);
}
