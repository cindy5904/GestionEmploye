package org.example.gestionemploye.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.example.gestionemploye.entity.Employe;
import org.hibernate.Transaction;

import java.util.List;

@ApplicationScoped
public class EmployeRepository extends BaseRepository<Employe> {
    public EmployeRepository() {
        super();
    }

    @Override
    public Employe add(Employe entity) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return entity;
    }

    @Override
    public Employe findById(Long id) {
        Employe employe = null;
        try {
            session = sessionFactory.openSession();
            employe = session.get(Employe.class,id);
            if (employe == null){
                throw new NotFoundException("Employé non trouvé");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employe;

    }

    @Override
    public Employe findByName(String nom) {
        Employe employe = null;
        try {
            session = sessionFactory.openSession();
            employe = session.get(Employe.class,nom);
            if (employe == null){
                throw new NotFoundException("Employé non trouvé");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employe;
    }

    @Override
    public Employe update(Employe entity) {
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

        return entity;
    }

    @Override
    public boolean delete(Employe entity) {
        Transaction transaction = null;
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
            result = true;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

        return result;
    }
    public List<Employe> findByDepartement(String departementNom) {
        List<Employe> employes = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM Employe e WHERE e.departement.nom = :departementNom";
            employes = session.createQuery(hql, Employe.class)
                    .setParameter("departementNom", departementNom)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employes;
    }

    public List<Employe> findByPoste(String posteNom) {
        List<Employe> employes = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM Employe e WHERE e.poste.nom = :posteNom";
            employes = session.createQuery(hql, Employe.class)
                    .setParameter("posteNom", posteNom)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employes;
    }

    public List<Employe> findAll(){
        List<Employe> employes = null;
        try {
            session = sessionFactory.openSession();
            employes = session.createQuery("from Employe ").list();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employes;

    }

}
