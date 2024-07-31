package com.in28minutes.springboot.myfirstwebapp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ContactDto {
	
	
	private String name;
	private String email;
	private String text;
	

}
