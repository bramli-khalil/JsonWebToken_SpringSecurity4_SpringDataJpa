package shj.spring4jwtsecurity.demo.Service;

import shj.spring4jwtsecurity.demo.Model.MyUser;
import shj.spring4jwtsecurity.demo.Model.Post;
import shj.spring4jwtsecurity.demo.Model.Role;

import java.util.List;

/**
 * Interface présentant les différents cas d'utilisation
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
public interface MyService {

  /**
   * Sauvegarde d'un utilisateur
   * @param user
   * @return l'utilisatuer sauvegardé
   */
  MyUser saveUser(MyUser user);

  /**
   * Sauvegarder un role
   * @param role
   * @return
   */
  Role saveRole(Role role);

  /**
   * Ajout d'un role à un tilisateur
   * @param userName
   * @param roleName
   */
  void addRoleToUser(String userName, String roleName);

  /**
   * Sauvegarder un post
   * @param post
   * @return
   */
  Post savePost(Post post, String username);

  /**
   * Retrouvé un utilisateur par son login
   * @param username
   * @return
   */
  MyUser findByUsername(String username);

  /**
   * Trouver la liste des posts d'un utilisateur
   * @param username
   * @return
   */
  List<Post> getUserPosts(String username);

  /**
   * Renvoyer tous les posts
   * @return
   */
  List<Post> getAllPosts();

}
