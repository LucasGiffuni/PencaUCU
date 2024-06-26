package com.pencaucu.backend.config;

import java.sql.SQLException;
import java.util.List;
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
import com.pencaucu.backend.service.impl.UserServiceImpl;

@Component
public class Scheduler {

    @Autowired
    PartidoService partidoService;

    @Autowired
    UserServiceImpl userService;

    @Value("${spring.mail.body}")
    private String body;

    @Autowired
    private JavaMailSender mailSender;

    // cron = "0 0 0 * * *" pa que se ejecute una vez al día
    // fixedRate = 1000 para la prueba
    @Scheduled(cron = "0 0 0 * * *")
    public void reportCurrentTime() throws ClassNotFoundException, SQLException {

        Partido[] partidos = partidoService.getPartidosDelDia().getPartidos();

        System.out.println(partidos[0]);

        if (partidos.length > 0) {
            sendMailToUsers(partidos);

        }
    }

    private void sendMailToUsers(Partido[] partidos) throws ClassNotFoundException, SQLException {

        List<String> mailsToSend = userService.obtenerUsuariosEnvioMail();

        for (String email : mailsToSend) {

            StringBuilder messageBody = new StringBuilder();

            SimpleMailMessage message = new SimpleMailMessage();

            messageBody.append(body);
            messageBody.append("\n");

            for (Partido partido : partidos) {
                System.out.println(partido.toString());
                messageBody.append(partido.getNombreEquipo1() + " VS " + partido.getNombreEquipo2());
                messageBody.append("\n");
            }

            message.setTo(email);
            message.setSubject("Penca UCU");
            message.setText(messageBody.toString());
            mailSender.send(message);
            System.out.println("Mail Sent Successfully...");
        }

    }

}
