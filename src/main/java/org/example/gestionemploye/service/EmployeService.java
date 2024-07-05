package org.example.gestionemploye.service;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.gestionemploye.entity.Employe;
import org.example.gestionemploye.repository.EmployeRepository;

import java.util.List;
@ApplicationScoped
public class EmployeService {
    private EmployeRepository employeRepository;

    @Inject
    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    @Transactional
    public Employe add(String nom, String email, String telephone, String adresse) {
        Employe employe = Employe.builder()
                .nom(nom)
                .email(email)
                .telephone(telephone)
                .adresse(adresse)
                .build();
        return employeRepository.add(employe);
    }
    @Transactional
    public Employe findById(Long id) {
        return employeRepository.findById(id);
    }
    @Transactional
    public Employe findByDepartement(String nom) {
        return (Employe) employeRepository.findByDepartement(nom);
    }
    @Transactional
    public Employe findByPoste(String nom) {
        return (Employe) employeRepository.findByPoste(nom);
    }
    @Transactional
    public Employe update(Long id, String nom, String email, String telephone, String adresse) {
        Employe employe = employeRepository.findById(id);
        if(employe != null) {
            employe.setNom(nom);
            employe.setEmail(email);
            employe.setTelephone(telephone);
            employe.setAdresse(adresse);
            return employeRepository.update(employe);
        } else {
            return null;
        }
    }
    @Transactional
    public boolean delete(long id){
        Employe employe = employeRepository.findById(id);
        return employeRepository.delete(employe);
    }
    @Transactional
    public List<Employe> findAll(){
        return employeRepository.findAll();
    }

}
