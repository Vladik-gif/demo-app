package com.example.springbootdockergradle.api.service;

import com.example.springbootdockergradle.store.entity.ClientEntity;
import com.example.springbootdockergradle.store.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServer {

    private final ClientRepository clientRepository;

    public ClientServer(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @CachePut(value = "create")
    public ClientEntity create(ClientEntity client){
        return clientRepository.save(client);
    }

    @Cacheable(
            value = "getId",
            key = "#id",
            unless = "#result.id < 12000"
    )
    public ClientEntity getId(Long id){
        return clientRepository.getReferenceById(id);
    }

    @CachePut(value = "clients")
    public List<ClientEntity> clients(){
        return clientRepository.findAll();
    }

    @CacheEvict(value = "deleteId", key = "#id")
    public void deleteId(Long id){
        clientRepository.deleteById(id);
    }
}