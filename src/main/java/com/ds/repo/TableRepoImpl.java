package com.ds.repo;

import com.ds.entity.Client;
import com.ds.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TableRepoImpl implements TableRepo{
    private final EntityManager em;

    @Autowired
    public TableRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addTable(Table table) {
        em.persist(table);
    }

    @Override
    @Transactional
    public int deleteTable(int id) {
        Query query = em.createQuery("delete from Table where num=:num");
        query.setParameter("num",id);
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public Table getTable(int id) {
        return em.find(Table.class,id);
    }

    @Override
    @Transactional
    public List<Table> getTables() {
        return em.createQuery("from Table").getResultList();
    }

    @Override
    @Transactional
    public Table updateTable(Table table,int id)
    {
        table.setNum(id);
        return em.merge(table);
    }
}
