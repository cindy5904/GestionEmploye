package org.example.gestionemploye.ressource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.gestionemploye.entity.Employe;
import org.example.gestionemploye.repository.EmployeRepository;
import org.example.gestionemploye.service.EmployeService;

import java.util.List;

@Path("/employes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeRessource {
    private EmployeService employeService;
    private EmployeRepository employeRepository;



    @Inject
    public EmployeRessource(EmployeService employeService, EmployeRepository employeRepository) {
        this.employeService = employeService;
        this.employeRepository = employeRepository;
    }

    @POST
    public Employe create(Employe employe) {
        return employeRepository.add(employe);
    }


    @GET
    @Path("{id}")
    public Employe findById(@PathParam("id")Long id) {
        return employeRepository.findById(id);
    }

    @GET
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @PUT
    @Path("{id}")
    public Employe update(@PathParam("id") Long id, String nom, String email, String telephone, String adresse) {
        Employe employe = employeRepository.findById(id);
        if (employe != null) {
            employe.setNom(nom);
            employe.setEmail(email);
            employe.setTelephone(telephone);
            employe.setAdresse(adresse);

            return employeRepository.update(employe);
        }
        return null;
    }

    @DELETE
    @Path("{id}")
    public boolean delete(@PathParam("id") Long id) {
        Employe employe = employeRepository.findById(id);
        if (employe != null) {
            employeRepository.delete(employe);
            return true;
        }
        return false;
    }

}
