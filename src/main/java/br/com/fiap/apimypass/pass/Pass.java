package br.com.fiap.apimypass.pass;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "passwords")
@Data
public class Pass {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String url;
    String username;
    String password;
}
