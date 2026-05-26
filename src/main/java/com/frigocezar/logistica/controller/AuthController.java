package com.frigocezar.logistica.controller;

import com.frigocezar.logistica.dto.AutenticacaoDTO;
import com.frigocezar.logistica.dto.RegistroDTO;
import com.frigocezar.logistica.model.UsuarioModel;
import com.frigocezar.logistica.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO auth) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(auth.login(), auth.senha());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(
            @RequestBody @Valid RegistroDTO registroDto
    ) {

        if (this.usuarioRepository
                .findByLogin(registroDto.login())
                != null) {

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

        return ResponseEntity.ok().build();
    }

}
