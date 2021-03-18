package com.ds.repo;

import com.ds.entity.met.Dessert;
import com.ds.entity.met.Entree;
import com.ds.entity.met.Met;
import com.ds.entity.met.Plat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MetRepoImpl implements MetRepo{

    private final EntityManager em;

    @Autowired
    public MetRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addMet(String type , Met met) {
        String nom = met.getNom();
        float prix = met.getPrix();
        switch(type) {
            case "dessert" :
                met = new Dessert();
                break;
            case "entree" :
                met = new Entree();
                break;
            default :
                met = new Plat();
        }
        met.setPrix(prix);
        met.setNom(nom);
        em.persist(met);
    }

    @Override
    @Transactional
    public int deleteMet(String type , String nom) {
        type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        Query query = em.createQuery("delete from "+type+" where nom=:nom");
        query.setParameter("nom",nom);
        return query.executeUpdate();
    }

    @Override
    public Met getMet(String type ,String nom) {
        type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        Query query = em.createQuery("from "+type+" p where nom=:nom");
        query.setParameter("nom", nom);
        return (Met)query.getSingleResult();
    }
    @Override
    public List<Met> getMets() {
        return em.createNativeQuery("select nom , prix from plat union select nom,prix from dessert union select nom , prix from entree").getResultList();
    }

    @Override
    public List<Met> getMets(String type) {
        type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        return em.createQuery("from "+type).getResultList();
    }

    @Override
    public Met updateMet(Met met, String nom) {
        met.setNom(nom);
        return em.merge(met);
    }
}
