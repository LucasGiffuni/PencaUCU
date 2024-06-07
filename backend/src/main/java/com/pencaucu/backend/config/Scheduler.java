package com.pencaucu.backend.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pencaucu.backend.service.impl.PartidoService;

@Component
public class Scheduler {

    @Autowired
    PartidoService partidoService;

    @Value("spring.mail.host")
    private String host;

    @Value("spring.mail.port")
    private String port;

    @Value("spring.mail.username")
    private String username;

    @Value("spring.mail.password")
    private String password;

    @Autowired
    private JavaMailSender mailSender;


    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        System.out.println("Hola");
        sendMailToUsers();
    }


    private void sendMailToUsers(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("javier.moreno@correo.ucu.edu.uy");
        message.setSubject("subject");
        message.setText("body");
        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }



}
