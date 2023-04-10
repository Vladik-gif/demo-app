package com.example.springbootdockergradle.controllers;

import com.example.springbootdockergradle.store.entity.ClientEntity;
import com.example.springbootdockergradle.store.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final ClientRepository clientRepository;

    public HomeController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public List<ClientEntity> clients(){
        return clientRepository.findAll();
    }

    @GetMapping("/")
    public String home(){
        return "Hello!!!";
    }
}
