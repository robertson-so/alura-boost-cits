package br.com.alura.comex.config;

import br.com.alura.comex.filter.AutenticacaoViaTokenFilter;
import br.com.alura.comex.service.TokenService;
import br.com.alura.comex.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final AuthenticationConfiguration configuration;
  private final TokenService tokenService;
  private final UsuarioService usuarioService;

  public SecurityConfiguration(AuthenticationConfiguration configuration, TokenService tokenService,
      UsuarioService usuarioService) {
    this.configuration = configuration;
    this.tokenService = tokenService;
    this.usuarioService = usuarioService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests()
        .antMatchers(HttpMethod.GET, "/api/categorias", "/api/produtos").permitAll()
        .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(
            new AutenticacaoViaTokenFilter(this.tokenService, this.usuarioService),
            UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return configuration.getAuthenticationManager();
  }

}
