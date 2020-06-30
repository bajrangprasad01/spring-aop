package com.example.springaop.service;

import com.example.springaop.dao.Dao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business1 {
    @Autowired
    private Dao1 dao1;

    public String getBusiness() {
        return dao1.getDao1();
    }
}
