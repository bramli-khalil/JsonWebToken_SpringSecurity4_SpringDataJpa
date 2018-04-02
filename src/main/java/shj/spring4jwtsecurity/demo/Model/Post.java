package shj.spring4jwtsecurity.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
  @Id
  @GeneratedValue
  private Long id;
  @NotEmpty
  private String title;
  @Lob
  private String content;
  @ManyToOne()
  @JoinColumn(name = "id_author", nullable = false)
  private MyUser author;
}
