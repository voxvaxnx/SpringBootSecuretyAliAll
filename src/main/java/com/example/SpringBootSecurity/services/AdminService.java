package com.example.SpringBootSecurity.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SOME_ROLE')") // блокируем доступ к методу всем кроме админа и сом рол
    public void doAdminStuff(){
        System.out.println("Админские движухи");
    }
}
