package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.PublicationBean;
import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MemberRepository;
import com.example.demo.dao.Membrepubrepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Membre_Pub_Ids;
import com.example.demo.entities.Membre_Publication;
import com.example.demo.proxies.PublicationProxy;
@Service
public class MemberImpl implements IMemberService {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheurRepository;
	@Autowired
	Membrepubrepository membrepubrepository;
	@Autowired
	PublicationProxy proxy;
	
	public Membre addMember(Membre m) {
		memberRepository.save(m);
		return m;
	}

	
	public void deleteMember(Long id) {
		
		memberRepository.deleteById(id);

	}
	public Membre updateMember(Membre m) {
		
		return memberRepository.saveAndFlush(m);
	}
	public Membre findMember(Long id) {
	Membre m= (Membre)memberRepository.findById(id).get();
		return m;
	}
	public List<Membre> findAll() {
		
		return memberRepository.findAll();
	}

	public Membre findByCin(String cin) {
		return memberRepository.findByCin(cin);
	}
	public Membre findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	public List<Membre> findByNom(String nom) {
		return memberRepository.findByNom(nom);
	}
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}
	public List<EnseignantChercheur> findByGrade(String grade) {
		
		return enseignantChercheurRepository.findByGrade(grade);
	}
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
	
		return enseignantChercheurRepository.findByEtablissement(etablissement);
	}
	public List<Etudiant> findAllEtudiants() {
		return etudiantRepository.findAll();
	}
	public List<EnseignantChercheur> findAllEnseignants() {
		return enseignantChercheurRepository.findAll();
	}


	@Override
	public Etudiant affecterencadrantToetudiant(Long idetd, Long idens) {
		// TODO Auto-generated method stub
		Etudiant etd= etudiantRepository.findById(idetd).get();
		EnseignantChercheur ens= enseignantChercheurRepository.findById(idens).get();
		etd.setEncadrant(ens);

		return etudiantRepository.save(etd);
	}


	@Override
	public void affecterauteurTopublication(Long idauteur, Long idpub) {
		Membre mbr= memberRepository.findById(idauteur).get();
		Membre_Publication mbs= new Membre_Publication();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Pub_Ids(idpub, idauteur));
		membrepubrepository.save(mbs);
	}


	@Override
	public List<PublicationBean> findPublicationparauteur(Long idauteur) {
		List<PublicationBean> pubs=new ArrayList<PublicationBean>();
	
		List< Membre_Publication> idpubs=membrepubrepository.findpubId(idauteur);
		
		idpubs.forEach(s->{
			System.out.println(s);
			pubs.add(proxy.recupererUnePublication(s.getId().getPublication_id()).getContent());
			
		}
		);
		
		return pubs;
	}
	

}
