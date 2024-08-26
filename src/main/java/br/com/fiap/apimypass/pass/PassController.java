package br.com.fiap.apimypass.pass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/pass")
public class PassController {
    @Autowired
    PassService service;

    @GetMapping
    public List<Pass> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Pass> create(@RequestBody Pass pswd, UriComponentsBuilder uriBuilder){
        service.create(pswd);

        var uri = uriBuilder
                .path("/pass/{id}")
                .buildAndExpand(pswd.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(pswd);
    }
}
