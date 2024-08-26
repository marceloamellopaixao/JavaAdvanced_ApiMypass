package br.com.fiap.apimypass.pass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassRepository extends JpaRepository<Pass, Long> {
    Optional<Pass> findByPassword(String password);
}
