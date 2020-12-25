package com.example.demo.controller;

import com.example.demo.entity.Publication;
import com.example.demo.service.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PublicationRestController {
    @Autowired
    IPublicationService iPublicationService;
    @PostMapping(value = "/publications/publication")
    public Publication addPublication(@RequestBody Publication pub){
        return iPublicationService.addPublication(pub);
    }
    @PutMapping(value="/publications/publication/{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication p)
    {
        p.setId(id);
        return iPublicationService.updatePublication(p);
    }
    @GetMapping(value = "/publications/{id}")
    public Publication findOnePublication(@PathVariable Long id)
    {
        return iPublicationService.findPublication(id);
    }
    @GetMapping(value = "/publications/")
    public List<Publication> findAll()
    {
        return iPublicationService.findAll();
    }
}
