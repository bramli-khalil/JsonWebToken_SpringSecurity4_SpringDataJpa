package shj.spring4jwtsecurity.demo.Security;

/**
 * Classe contenant les différentes constantes du Token JWT
 * @Project Spring4_Jwt_Security
 * @Author Henri Joel SEDJAME
 * @Date 02/04/2018
 */
class SecurityConstants {
    public static final String SECRET = "021487!";
    public static final String ROLE_KEY = "roles";
    public static final String AUTHORITY_KEY = "authority";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 1000*60*60*24*10; /*milliSeconds * seconds * minutes * heure* nb jour*/
}
