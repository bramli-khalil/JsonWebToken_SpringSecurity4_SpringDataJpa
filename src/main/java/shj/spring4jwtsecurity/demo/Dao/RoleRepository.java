package shj.spring4jwtsecurity.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shj.spring4jwtsecurity.demo.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

  Role findByRoleName(String roleName);
}
