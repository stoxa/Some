package com.example.demo.dao;

import com.example.demo.data.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContactsDaoImpl implements ContactsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> getContactsPack(int startId, int packSize) {
        return entityManager.createNativeQuery("select * from Contact where id >" +startId+ " order by id asc limit " + packSize,
                Contact.class).getResultList();
    }
}
