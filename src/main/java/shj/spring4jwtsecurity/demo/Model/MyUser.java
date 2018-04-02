package shj.spring4jwtsecurity.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shj.spring4jwtsecurity.demo.Model.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String password;
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private Collection<Role> roles;

  @JsonIgnore
  public String getPassword() {
    return password;
  }
  @JsonSetter
  public void setPassword(String password) {
    this.password = password;
  }
}
