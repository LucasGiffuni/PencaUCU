package com.pencaucu.backend.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencaucu.backend.model.responses.CrearPrediccionResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;

@Service
public class PrediccionService extends AbstractService {

    @Autowired
    PartidoService partidoService;

    public CrearPrediccionResponse cargarPrediccion(int idPartido, int resultadoEquipo1, int resultadoEquipo2, int CI) throws ClassNotFoundException, SQLException {
        createConection();
        String sql = "INSERT INTO prediccion VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, CI);
        preparedStmt.setInt(2, idPartido);
        int idGanador = partidoService.calcularGanador(idPartido, resultadoEquipo1, resultadoEquipo2);
        preparedStmt.setInt(3, idGanador);
        preparedStmt.setInt(4, resultadoEquipo1);
        preparedStmt.setInt(5, resultadoEquipo2);
        preparedStmt.setInt(6, 0);
        preparedStmt.execute();


        DefaultResponse dr = new DefaultResponse("200", "Prediccion cargada correctamente");
        return new CrearPrediccionResponse(dr, null);
    }

}
