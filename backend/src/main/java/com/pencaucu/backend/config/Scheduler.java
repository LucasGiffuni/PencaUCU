package com.pencaucu.backend.config;

import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pencaucu.backend.model.Partido;
import com.pencaucu.backend.service.impl.PartidoService;

@Component
public class Scheduler {

    @Autowired
    PartidoService partidoService;


    @Value("${spring.mail.body}")
    private String body;

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        sendMailToUsers();
    }

    private void sendMailToUsers() {
        StringBuilder messageBody  = new StringBuilder();

        SimpleMailMessage message = new SimpleMailMessage();
        Partido[] partidos = new Partido[0];
        try {
            partidos = partidoService.getPartidosDelDia().getPartidos();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        messageBody.append(body);
        messageBody.append("\n");

        for (Partido partido : partidos) {
            System.out.println(partido.toString());
            messageBody.append(partido.getNombreEquipo1() + " VS "+ partido.getNombreEquipo2());
        }

        message.setTo("lucasgiffuni@gmail.com");
        message.setSubject("Penca UCU");
        message.setText(messageBody.toString());
        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }

}
