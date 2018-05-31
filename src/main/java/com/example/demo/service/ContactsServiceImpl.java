package com.example.demo.service;

import com.example.demo.dao.ContactsDao;
import com.example.demo.data.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ContactsServiceImpl implements ContactService {

    private static final int PACKSIZE = 1000;
    @Autowired
    private ContactsDao contactsDao;

    @Override
    public List<Contact> getContacts(String regexp) {
        List<Contact> contacts = new ArrayList<>();
        int startPosition = 0;
        while (true) {
            List<Contact> contactsPack = contactsDao.getContactsPack(startPosition, PACKSIZE);
            if(contactsPack.size()==0) break;
            startPosition = contactsPack.get(contactsPack.size()-1).getId();
            contacts.addAll(contactsPack.stream().filter(i -> i.getName().matches(regexp)).collect(Collectors.toList()));
        }
        return contacts;
    }

}
