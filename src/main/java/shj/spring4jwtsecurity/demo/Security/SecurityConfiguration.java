package shj.spring4jwtsecurity.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shj.spring4jwtsecurity.demo.Model.Role;

/**
 * Classe de configuration de la sécurité
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  @Qualifier("customUserDetailsService")
  private UserDetailsService service;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service)
      .passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
          .csrf().disable()                                                                           /* Désactiver la gestion des risques csrf par Spring*/
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)                 /* -Demander à spring de ne pas créer de session*/
        .and()
          .authorizeRequests()                                                                         /* Définir les différentes autorisations par rapport aux url*/
          .antMatchers("/users/**", "/login/**").permitAll()
          .antMatchers(HttpMethod.POST, "/posts/**").hasAuthority(Role.ADMIN)
          .anyRequest().authenticated()
      .and()
          .addFilter( new JWTAuthentificationFilter(authenticationManager()))                           /* Ajouter le filtre qui permet de générer le token jwt si l'authentification est réussie*/
          .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);   /*Ajouter le filtre qui vérifiera le token à chaque requête*/

  }
}
