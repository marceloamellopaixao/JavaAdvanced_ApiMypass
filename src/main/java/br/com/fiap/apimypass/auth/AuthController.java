package br.com.fiap.apimypass.auth;

import br.com.fiap.apimypass.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) {

        var user = userRepository.findByUsername(credentials.username())
                .orElseThrow(() -> new RuntimeException("Access Denied"));

        if ( !passwordEncoder.matches( credentials.password(), user.getPassword()) )
            throw new RuntimeException("Access Denied");


        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        Algorithm algorithm = Algorithm.HMAC256("assinatura");
        String token = JWT.create()
                .withIssuer("apimypass")
                .withSubject(credentials.username())
                .withClaim("role","admin")
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new Token(token);
    }
}
