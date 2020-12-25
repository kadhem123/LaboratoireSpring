package com.example.demo.service;

import com.example.demo.entity.Publication;

import java.util.List;

public interface IPublicationService {
    public Publication addPublication(Publication p);
    public void deletePublication(Long id);
    public Publication updatePublication(Publication p);
    public Publication findPublication(long id);
    public List<Publication> findAll();
}
