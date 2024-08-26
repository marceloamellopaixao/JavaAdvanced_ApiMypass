package br.com.fiap.apimypass.pass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {
    @Autowired
    PassRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Pass> findAll(){
        return repository.findAll();
    }

    public Pass create(Pass pswd){
        pswd.setPassword(passwordEncoder.encode(pswd.getPassword()));
        return repository.save(pswd);
    }
}
