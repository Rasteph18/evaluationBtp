package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.VDetailsDevisUser;
import com.projet.evalBtp.repository.VDetailsDevisUserRepository;

@Service
public class VDetailsDevisUserService {
    
    @Autowired
    private VDetailsDevisUserRepository vDetailsDevisUserRepository;

    public List<VDetailsDevisUser> getByIdUser(int idUser)
    {
        return vDetailsDevisUserRepository.findByIdUser(idUser);
    }

    public VDetailsDevisUser getById(int id)
    {
        return vDetailsDevisUserRepository.findById(id).get();
    }

}
