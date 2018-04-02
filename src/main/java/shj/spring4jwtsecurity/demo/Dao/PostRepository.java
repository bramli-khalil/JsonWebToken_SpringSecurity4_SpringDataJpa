package shj.spring4jwtsecurity.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shj.spring4jwtsecurity.demo.Model.Post;

import java.util.List;

/**
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
@Repository
public interface PostRepository extends JpaRepository<Post,Long>{

  List<Post> findByAuthor_Username(String username);
}
