package com.waracle.cakemanagerservice.resource;

import com.waracle.cakemanagerservice.model.Cake;
import com.waracle.cakemanagerservice.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cakes")
public class CakeResource {

    @Autowired
    CakeService cakeService;

    @GetMapping("/all")
    public ResponseEntity<List<Cake>> getCakes() {

        return ResponseEntity.ok(cakeService.finaAllCakes());
    }

    @PostMapping("/add")
    public ResponseEntity<Cake> addCake(@RequestBody Cake cake) {

        Cake newCake = cakeService.addCake(cake);
        return new ResponseEntity<>(newCake, HttpStatus.CREATED);
    }

}
