package com.example.demo.dao;

import com.example.demo.data.Contact;

import java.util.List;

public interface ContactsDao {
    List<Contact> getContactsPack(int startId, int packSize);
}
