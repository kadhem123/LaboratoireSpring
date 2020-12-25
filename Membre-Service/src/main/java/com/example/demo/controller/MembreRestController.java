package com.example.demo.controller;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.PublicationBean;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;



@RestController
public class MembreRestController {
	@Autowired
	IMemberService iMemberService;
	@Autowired
	PublicationProxy publicationproxy;
	
	@GetMapping(value = "/membres")
	public List<Membre> findAllmembres()
	{
		return iMemberService.findAll();
	}

	@GetMapping(value = "/membres/{id}")
	public Membre findoneMembre(@PathVariable Long id)
	{
		return iMemberService.findMember(id);
	}
	
	@PostMapping(value = "/membres/etudiant")
	public Membre addMembre(@RequestBody Etudiant etd)
	{
		return iMemberService.addMember(etd);
	}

	@PostMapping(value = "/membres/enseignant")
	public Membre addMembre(@RequestBody EnseignantChercheur ens)
	{
		return iMemberService.addMember(ens);
	}
	@PutMapping(value="/membres/etudiant/{id}")
	public Membre updatemembre(@PathVariable Long id, @RequestBody Etudiant p)
	{
		p.setId(id);
		return iMemberService.updateMember(p);
	}

	@PutMapping(value="/membres/enseignant/{id}")
	public Membre updateMembre(@PathVariable Long id, @RequestBody EnseignantChercheur p)
	{
		p.setId(id);
	       return iMemberService.updateMember(p);
	}
	@PutMapping(value="/membres/etudiant")
	public Membre affecter(@RequestParam Long idetd , @RequestParam Long idens )
	{
		
	       return iMemberService.affecterencadrantToetudiant(idetd, idens);
	}
	@PutMapping(value="/membres/publication")
	public void affecterAuteurToPublication(@RequestParam Long idetd , @RequestParam Long idpub )
	{

		 iMemberService.affecterauteurTopublication(idetd, idpub);
	}
	@GetMapping("/publications")
	public CollectionModel<PublicationBean>listerpublication()
	{
		return publicationproxy.listeDesPublications();
		
	}
	@GetMapping("/publications/{id}")
	public EntityModel<PublicationBean> listerunepublication(@PathVariable Long id)
	{
		return publicationproxy.recupererUnePublication(id);
		
	}
	@GetMapping("/publications/auteur/{id}")
	public List<PublicationBean>listerpublicationbymembre(@PathVariable(name="id") Long idaut)
	{
		return iMemberService.findPublicationparauteur(idaut);		
	}
	
	@GetMapping("/fullmember/{id}")
	public Membre findAFullMember(@PathVariable(name="id") Long id)
	{
		Membre mbr=iMemberService.findMember(id);
		mbr.setPubs(iMemberService.findPublicationparauteur(id));
		return mbr;
	}

	
}
