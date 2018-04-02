package shj.spring4jwtsecurity.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  public  static final String ADMIN = "ADMIN";
  public  static final String USER = "USER";

  @Id
  @GeneratedValue
  private Long id;
  private String roleName;
}
