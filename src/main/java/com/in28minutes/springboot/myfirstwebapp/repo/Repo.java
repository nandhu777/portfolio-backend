package com.in28minutes.springboot.myfirstwebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.myfirstwebapp.Contact;

public interface Repo extends JpaRepository< Contact, Long>  {

}
