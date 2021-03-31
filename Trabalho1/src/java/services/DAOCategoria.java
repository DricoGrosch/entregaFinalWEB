/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author Drico
 */
public class DAOCategoria {
  public static boolean persist(Categoria ct) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ct);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
    
    public static Categoria getOne(Long pId){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Categoria.class, pId);    
    }   
    
       public static boolean excluir(Long pId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");
        EntityManager em = emf.createEntityManager();
        Categoria cg = em.find(Categoria.class, pId);
        try {
            em.getTransaction().begin();
            em.remove(cg);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
    public static List<Categoria> getAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Categoria> tq = em.createQuery("select c  from Categoria c", Categoria.class);
        return tq.getResultList();
    }
    
    public static boolean editar(Categoria ct){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prova1PU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ct);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
  
}
