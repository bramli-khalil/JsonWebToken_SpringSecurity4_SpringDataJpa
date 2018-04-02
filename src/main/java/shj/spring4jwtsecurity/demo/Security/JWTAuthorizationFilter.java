package shj.spring4jwtsecurity.demo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class JWTAuthorizationFilter extends OncePerRequestFilter{

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    /* On ajoute des entêtes à la réponse*/
      /*Pour les autorisatons Cross-origin*/
    response.addHeader(HeadersConstants.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
      /*Pour autoriser les entêtes*/
    response.addHeader(HeadersConstants.ACCESS_CONTROL_ALLOW_HEADERS, HeadersConstants.ORIGIN
                                                                          + HeadersConstants.ACCEPT
                                                                          + HeadersConstants.X_REQUESTED_WITH
                                                                          + HeadersConstants.CONTENT_TYPE
                                                                          + HeadersConstants.ACCESS_CONTROL_REQUEST_METHOD
                                                                          + HeadersConstants.ACCESS_CONTROL_REQUEST_HEADERS
                                                                          + HeadersConstants.AUTHORIZATIONS);
      /* pour exposer les entêtes */
    response.addHeader(HeadersConstants.ACCESS_CONTROL_EXPOSE_HEADERS, HeadersConstants.ACCESS_CONTROL_ALLOW_ORIGIN
                                                                          + HeadersConstants.ACCESS_CONTROL_ALLOW_CREDENTIALS
                                                                          + HeadersConstants.AUTHORIZATIONS);


    if (request.getMethod().equals("OPTIONS")){
      response.setStatus(HttpServletResponse.SC_OK);
    }

    /*On récupère le json wen token*/
    String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);

    /*Si le token n'existe pas ou si il ne commence pas par le préfixe attendu, on continue*/
    if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
      filterChain.doFilter(request, response);
      return;
    }

    /*Sinon, on récuupère les informations du token pour l'autorisation de la requête*/

    Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody();

    String username = claims.getSubject();

    ArrayList<Map<String, String>> roles = ( ArrayList<Map<String, String>>) claims.get(SecurityConstants.ROLE_KEY);

    Collection<GrantedAuthority> authorities = new ArrayList<>();

    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.get(SecurityConstants.AUTHORITY_KEY))));

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    filterChain.doFilter(request, response);
  }
}
