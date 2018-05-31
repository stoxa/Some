package com.example.demo.controller;


import com.example.demo.data.Contact;
import com.example.demo.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("hello")
public class ContactsController {


    @Autowired
    private ContactService contactService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("contacts/nameFilter={nameFilter}")
    public @ResponseBody
    ResponseEntity<?> getArticleById(@PathVariable("nameFilter") String nameFilter) {
        List<Contact> contacts = null;
        if (!isValidRegexp(nameFilter))
            return new ResponseEntity(objectMapper.createObjectNode().put("errText", "incorrect regexp"), HttpStatus.BAD_REQUEST);

        contacts = contactService.getContacts(nameFilter);

        ArrayNode contactsArrayNode = objectMapper.valueToTree(contacts);
        ObjectNode contactsNode = objectMapper.createObjectNode();
        contactsNode.putArray("contacts").addAll(contactsArrayNode);

        if (contacts == null || contacts.size() == 0)
            return new ResponseEntity<>(contactsNode, HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(contactsNode, HttpStatus.OK);
    }


    private boolean isValidRegexp(String regexp) {
        if (regexp == null || regexp.isEmpty()) return false;
        try {
            Pattern.compile(regexp);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }


}
