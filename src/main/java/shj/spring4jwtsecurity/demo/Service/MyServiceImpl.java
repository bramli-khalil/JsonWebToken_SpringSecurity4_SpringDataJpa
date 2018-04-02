package shj.spring4jwtsecurity.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shj.spring4jwtsecurity.demo.Dao.MyUserRepository;
import shj.spring4jwtsecurity.demo.Dao.PostRepository;
import shj.spring4jwtsecurity.demo.Dao.RoleRepository;
import shj.spring4jwtsecurity.demo.Model.MyUser;
import shj.spring4jwtsecurity.demo.Model.Post;
import shj.spring4jwtsecurity.demo.Model.Role;
import shj.spring4jwtsecurity.demo.Service.MyService;

import java.util.List;

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

  private PostRepository postRepository;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public MyServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, MyUserRepository myUserRepository, PostRepository postRepository) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleRepository = roleRepository;
    this.myUserRepository = myUserRepository;
    this.postRepository = postRepository;
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

  /**
   * Trouver la liste des posts d'un utilisateur
   *
   * @param username
   * @return
   */
  @Override
  public List<Post> getUserPosts(String username) {
    return this.postRepository.findByAuthor_Username(username);
  }

  /**
   * Renvoyer tous les posts
   *
   * @return
   */
  @Override
  public List<Post> getAllPosts() {
    return this.postRepository.findAll();
  }

  /**
   * Sauvegarder un post
   *
   * @param post
   * @param username
   * @return
   */
  @Override
  public Post savePost(Post post, String username) {
    MyUser user = this.findByUsername(username);
    post.setAuthor(user);
    return this.postRepository.save(post);
  }

  /**
   * Sauvegarder un role
   *
   * @param role
   * @return
   */
  @Override
  public Role saveRole(Role role) {
    return this.roleRepository.save(role);
  }
}
