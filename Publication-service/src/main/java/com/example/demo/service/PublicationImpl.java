package com.example.demo.service;

import com.example.demo.dao.PublicationRepository;
import com.example.demo.entity.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationImpl implements IPublicationService{
@Autowired
    PublicationRepository publicationRepository;

    @Override
    public Publication addPublication(Publication p) {
        publicationRepository.save(p);
        return p;
   }

    @Override
    public void deletePublication(Long id) {
    publicationRepository.deleteById(id);
    }

    @Override
    public Publication updatePublication(Publication p) {
        return publicationRepository.saveAndFlush(p);
    }

    @Override
    public Publication findPublication(long id) {
        Publication p=publicationRepository.findById(id).get();
        return p;
    }
    @Override
    public List<Publication> findAll() {

        return publicationRepository.findAll();
    }
}
