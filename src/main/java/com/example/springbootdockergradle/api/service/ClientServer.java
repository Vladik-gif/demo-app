package com.example.springbootdockergradle.api.service;

import com.example.springbootdockergradle.store.entity.ClientEntity;
import com.example.springbootdockergradle.store.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@EnableCaching
public class ClientServer {

    private final ClientRepository clientRepository;

    public ClientServer(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @CachePut(value = "create")
    public ClientEntity create(ClientEntity client){
        return clientRepository.save(client);
    }

    @Transactional
    @Cacheable(value = "getId", key = "#id")
    public ClientEntity getId(Long id){
        return clientRepository.getReferenceById(id);
    }

    @Transactional
    @CachePut(value = "clients")
    public List<ClientEntity> clients(){
        return clientRepository.findAll();
    }

    @Transactional
    @CacheEvict(value = "deleteId", key = "#id")
    public void deleteId(Long id){
        clientRepository.deleteById(id);
    }
}