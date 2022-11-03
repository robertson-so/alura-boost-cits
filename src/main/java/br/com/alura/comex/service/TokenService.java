package br.com.alura.comex.service;

import br.com.alura.comex.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.OffsetDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${jwt.expiration}")
  private Long expirationInDays;
  @Value("${jwt.secret}")
  private String secret;

  public String createToken(Authentication authentication) {
    var usuario = (Usuario) authentication.getPrincipal();
    return Jwts.builder()
        .setIssuer("Fubar")
        .setSubject(usuario.getUsername())
        .setIssuedAt(Date.from(OffsetDateTime.now().toInstant()))
        .setExpiration(Date.from(OffsetDateTime.now().plusDays(this.expirationInDays).toInstant()))
        .signWith(SignatureAlgorithm.HS256, this.secret)
        .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser()
          .setSigningKey(this.secret)
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getSubject(String token) {
    return Jwts.parser()
        .setSigningKey(this.secret)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
