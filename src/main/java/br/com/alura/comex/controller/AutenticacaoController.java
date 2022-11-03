package br.com.alura.comex.controller;

import br.com.alura.comex.controller.domain.LoginRequest;
import br.com.alura.comex.controller.domain.LoginTokenResponse;
import br.com.alura.comex.service.AutenticacaoService;
import br.com.alura.comex.service.TokenService;
import javax.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

  private final AuthenticationManager authenticationManager;
  private final AutenticacaoService autenticacaoService;
  private final TokenService tokenService;

  public AutenticacaoController(
      @Lazy AuthenticationManager authenticationManager,
      @Lazy AutenticacaoService autenticacaoService,
      @Lazy TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.autenticacaoService = autenticacaoService;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity<LoginTokenResponse> authenticate(@Valid @RequestBody LoginRequest request) {
    var userDetails = autenticacaoService.loadUserByUsername(request.getUsername());
    var token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
        userDetails.getPassword());
    try {
      var authentication = authenticationManager.authenticate(token);
      var response = new LoginTokenResponse(tokenService.createToken(authentication), "Bearer");
      return ResponseEntity.ok(response);
    } catch (BadCredentialsException | DisabledException | LockedException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
