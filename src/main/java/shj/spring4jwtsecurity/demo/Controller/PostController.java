package shj.spring4jwtsecurity.demo.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import shj.spring4jwtsecurity.demo.Model.Post;
import shj.spring4jwtsecurity.demo.Service.MyService;

import java.util.List;

/**
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */

@RestController
@RequestMapping("/posts")
public class PostController {

  private MyService myService;

  public PostController(MyService myService) {
    this.myService = myService;
  }

  @GetMapping("/{username}")
  public List<Post> getUserPosts(@PathVariable("username") String username){
    return this.myService.getUserPosts(username);
  }

  @GetMapping("/all")
  public List<Post> getAllPosts(){
    return this.myService.getAllPosts();
  }

  @PostMapping(value = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE )
  public Post savePost(@PathVariable("username")String username, @RequestBody Post post){
    return this.myService.savePost(post, username);
  }
}
