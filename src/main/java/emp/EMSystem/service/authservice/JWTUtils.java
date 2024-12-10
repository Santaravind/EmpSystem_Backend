package emp.EMSystem.service.authservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JWTUtils {
    private SecretKey key;
    private  static long EXPIRATION_TIME=86400000;

public JWTUtils(){
    String secretString= "YUhSMGNITTZMeTkzWldKaGRHVXRhRzkzZEM1amIyMGlhWE1pTkdjdE9TNWpiMjA9";
    byte [] keyBytes= Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
    this.key=new SecretKeySpec(keyBytes,"HmacSHA256");

}

public String generateToken(UserDetails userDetails){
    return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()*EXPIRATION_TIME))
            .signWith(key)
            .compact();
}

public  String generateRefreshToken(HashMap<String , Objects>claims, UserDetails userDetails){
    return Jwts.builder()
            .claims(claims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()*EXPIRATION_TIME))
            .signWith(key)
            .compact();
}

public String extractUsername(String token){
    return  extractClaims(token, Claims::getSubject);
}

private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
    return claimsTFunction.apply(Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload());
}

public boolean istokenValid(String token,UserDetails userDetails){
    final  String username=extractUsername(token);
    return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
}

public  boolean isTokenExpired(String token){
    return extractClaims(token,Claims::getExpiration).before(new Date());
}

}
