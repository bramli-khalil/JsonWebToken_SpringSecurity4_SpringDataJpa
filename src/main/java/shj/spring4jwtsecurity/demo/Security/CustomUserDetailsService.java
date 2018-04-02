package shj.spring4jwtsecurity.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shj.spring4jwtsecurity.demo.Model.MyUser;
import shj.spring4jwtsecurity.demo.Service.MyService;

import java.util.ArrayList;
import java.util.Collection;

@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private MyService myService;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    MyUser user = this.myService.findByUsername(userName);

    if (user == null) throw new UsernameNotFoundException(userName);

    Collection<GrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

    return new User(user.getUsername(), user.getPassword(), authorities);
  }
}
