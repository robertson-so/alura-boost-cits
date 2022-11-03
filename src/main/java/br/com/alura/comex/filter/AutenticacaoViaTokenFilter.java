package br.com.alura.comex.filter;

import br.com.alura.comex.service.TokenService;
import br.com.alura.comex.service.UsuarioService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UsuarioService usuarioService;

  public AutenticacaoViaTokenFilter(
      @Lazy TokenService tokenService,
      @Lazy UsuarioService usuarioService) {
    this.tokenService = tokenService;
    this.usuarioService = usuarioService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    var token = getAuthorizationToken(request);
    if (this.tokenService.isTokenValid(token)) {
      authorizeToken(token);
    }
    filterChain.doFilter(request, response);
  }

  private String getAuthorizationToken(HttpServletRequest httpServletRequest) {
    var value = httpServletRequest.getHeader("Authorization");
    if (null == value || value.isBlank() || !value.startsWith("Bearer")) {
      return null;
    }
    return value.replace("Bearer ", "");
  }

  private void authorizeToken(String token) {
    var username = this.tokenService
        .getSubject(token);
    var user = this.usuarioService
        .findByEmail(username)
        .orElseThrow();
    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    SecurityContextHolder.getContext()
        .setAuthentication(authentication);
  }
}
