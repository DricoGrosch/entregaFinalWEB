/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author Drico
 */
public class DAOProduto {
  public static boolean persist(Produto ct) {
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
    
    public static Produto getOne(Long pId){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");  
      EntityManager em = emf.createEntityManager();
      return em.find(Produto.class, pId);    
    }   
    
    public static boolean excluir(Long pId){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");  
      EntityManager em = emf.createEntityManager();
      em.remove(pId); 
      return true;
    }
    public static List<Produto> getAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Produto> tq = em.createQuery("select c  from Produto c", Produto.class);
        return tq.getResultList();
    }
    
    public static boolean editar(Produto ct){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabalho1PU");
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
