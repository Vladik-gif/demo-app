package com.example.springbootdockergradle.api.controllers;

import com.example.springbootdockergradle.api.service.ClientServer;
import com.example.springbootdockergradle.store.entity.ClientEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ClientController {

    private final ClientServer clientServer;
    private static final String CREATE = "/create";
    private static final String GETS = "/clients";
    private static final String GET_ID = "get/{id}";
    private static final String DELETE_ID = "/delete/{id}";

    public ClientController(ClientServer clientServer) {
        this.clientServer = clientServer;
    }

    @PostMapping(CREATE)
    public ClientEntity create(@RequestBody ClientEntity client){
        return clientServer.create(client);
    }

    @GetMapping(GETS)
    public List<ClientEntity> clients(){
        return clientServer.clients();
    }

    @GetMapping(GET_ID)
    public ClientEntity getId(@PathVariable Long id){
        return clientServer.getId(id);
    }

    @DeleteMapping(DELETE_ID)
    public void deleteId(@PathVariable Long id){
        clientServer.deleteId(id);
    }
}