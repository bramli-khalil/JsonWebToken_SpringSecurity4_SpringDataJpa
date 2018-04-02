package shj.spring4jwtsecurity.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Objet métier représentant le role de l'utilisateur
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */

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
