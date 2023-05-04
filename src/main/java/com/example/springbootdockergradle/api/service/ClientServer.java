package com.example.springbootdockergradle.api.service;

import com.example.springbootdockergradle.store.entity.ClientEntity;
import com.example.springbootdockergradle.store.repository.ClientRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServer {

    private final ClientRepository clientRepository;

    public ClientServer(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "ClientServer::getUsername", key = "#client.username"),
                    @Cacheable(value = "ClientServer::getCiti", key = "#client.citi"),
            }
    )
    public ClientEntity create(ClientEntity client){
        return clientRepository.save(client);
    }

    @Cacheable(value = "UserService::getId", key = "#id")
    public ClientEntity getId(Long id){
        return clientRepository.getReferenceById(id);
    }

    public List<ClientEntity> clients(){
        return clientRepository.findAll();
    }

    @Cacheable(value = "UserService::deleteId", key = "#id")
    public void deleteId(Long id){
        clientRepository.deleteById(id);
    }
}