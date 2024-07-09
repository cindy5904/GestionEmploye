package org.example.gestionemploye.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.gestionemploye.entity.Employe;
import org.example.gestionemploye.repository.EmployeRepository;

import java.beans.JavaBean;
import java.util.List;


public class EmployeService {
    private EmployeRepository employeRepository;

    @Inject
    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Employe add(String nom, String email, String telephone, String adresse) {
        Employe employe = Employe.builder()
                .nom(nom)
                .email(email)
                .telephone(telephone)
                .adresse(adresse)
                .build();
        return employeRepository.add(employe);
    }

    public Employe findById(Long id) {
        return employeRepository.findById(id);
    }

    public Employe findByDepartement(String nom) {
        return (Employe) employeRepository.findByDepartement(nom);
    }

    public Employe findByPoste(String nom) {
        return (Employe) employeRepository.findByPoste(nom);
    }

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

    public boolean delete(long id){
        Employe employe = employeRepository.findById(id);
        return employeRepository.delete(employe);
    }

    public List<Employe> findAll(){
        return employeRepository.findAll();
    }

}
