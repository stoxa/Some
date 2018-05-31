package com.example.demo.service;

import com.example.demo.data.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts(String regexp);
}
