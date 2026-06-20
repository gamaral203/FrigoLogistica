package com.frigocezar.logistica.controller;


import com.frigocezar.logistica.dto.AutenticacaoDTO;
import com.frigocezar.logistica.dto.LoginResponseDTO;
import com.frigocezar.logistica.dto.RegistroDTO;
import com.frigocezar.logistica.model.UsuarioModel;
import com.frigocezar.logistica.repository.UsuarioRepository;
import com.frigocezar.logistica.security.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController implements com.frigocezar.logistica.docs.AuthControllerDoc {

    private final TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO auth) {
        log.info("Tentativa de login para {}", auth.login());
        var usernamePassword = new UsernamePasswordAuthenticationToken(auth.login(), auth.senha());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);

        UsuarioModel user = (UsuarioModel) authenticate.getPrincipal();
        var token = tokenService.gerarToken(user);

        log.info("Usuário autenticado: {}", user.getLogin());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(
            @RequestBody @Valid RegistroDTO registroDto
    ) {

        log.info("Registrando novo usuário: {}", registroDto.login());

        if (this.usuarioRepository
                .findByLogin(registroDto.login())
                != null) {

            log.warn("Tentativa de registro com login existente: {}", registroDto.login());
            return ResponseEntity.badRequest().build();
        }

        String senhaCriptografada =
                new BCryptPasswordEncoder()
                        .encode(registroDto.senha());

        UsuarioModel novoUsuario = new UsuarioModel(
                registroDto.login(),
                senhaCriptografada,
                registroDto.role()
        );

        this.usuarioRepository.save(novoUsuario);

        log.info("Usuário registrado com sucesso: {}", registroDto.login());

        return ResponseEntity.ok().build();
    }

}
