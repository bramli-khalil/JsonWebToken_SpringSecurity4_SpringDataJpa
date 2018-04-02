package shj.spring4jwtsecurity.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MyController {


  @GetMapping("greet")
  public String greeting(){
    return "Hello World";
  }
}
