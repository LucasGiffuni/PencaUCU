package com.pencaucu.backend.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.hash.Hashing;
import com.pencaucu.backend.BackendApplication;
import com.pencaucu.backend.model.Alumno;
import com.pencaucu.backend.model.Carrera;
import com.pencaucu.backend.model.DatabaseUser;
import com.pencaucu.backend.model.responses.CreateAlumnoResponse;
import com.pencaucu.backend.model.responses.DefaultResponse;
import com.pencaucu.backend.model.responses.LoginResponse;
import com.pencaucu.backend.model.responses.ObtenerCarrerasResponse;
import com.pencaucu.backend.model.responses.RegisterResponse;
import com.pencaucu.backend.security.JwtUtilService;

@Service
public class UserServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    public UserServiceImpl(String connectionString, String databaseName, String databaseUser, String databasePassword) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
    }

    public UserServiceImpl() {
    }

    @Autowired
    private JwtUtilService jwtUtilService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private Connection con;

    @Value("${spring.datasource.connectionString}")
    private String connectionString;
    @Value("${spring.datasource.databaseName}")
    private String databaseName;
    @Value("${spring.datasource.databaseUsername}")
    private String databaseUser;
    @Value("${spring.datasource.databasePassword}")
    private String databasePassword;

    private void createConection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        logger.info(connectionString + databaseName
                + databaseUser + databasePassword);
        this.con = DriverManager.getConnection(
                connectionString + databaseName + " ?enabledTLSProtocols=TLSv1.2", databaseUser, databasePassword);
    }

    public List<DatabaseUser> getAllUsers() throws SQLException, ClassNotFoundException {

        createConection();
        List<DatabaseUser> users = new ArrayList<>();

        String sql = "select userId from LOGIN";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {
            DatabaseUser u = new DatabaseUser(rs.getString(1));
            users.add(u);
            logger.info(u.getUsername());
        }
        return users;
    }

    public LoginResponse login(String username, String password) {
        String encryptedpassword = null;
        LoginResponse response = new LoginResponse();

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(password.getBytes());

            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            createConection();
            String sql = "select Password from LOGIN where userId = ?";

            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, username);
            ResultSet resultSet = preparedStmt.executeQuery();
            resultSet.absolute(1); // Go directly to 2nd row

            System.out.println("DATA: " + resultSet.getString(1));

            if (resultSet.getString(1).equals(encryptedpassword)) {

                Alumno a = new Alumno();
                sql = "Select * from ALUMNO f where f.userId = ?";

                PreparedStatement preparedStmt2 = con.prepareStatement(sql);
                preparedStmt2.setString(1, username);

                ResultSet resultSet2 = preparedStmt2.executeQuery();
                while (resultSet2.next()) {
                    a.setCedulaIdentidad(String.valueOf(resultSet2.getInt(1)));
                    a.setNombre(resultSet2.getString(2));
                    a.setApellido(resultSet2.getString(3));
                    a.setFechaNacimiento(resultSet2.getDate(4).toString());
                    a.setEmail(resultSet2.getString(5));
                    a.setIdCarrera(String.valueOf(resultSet2.getInt(6)));
                }

                final String token = jwtUtilService
                        .generateToken(new User(username, encryptedpassword, new ArrayList<>()));

                DefaultResponse dR = new DefaultResponse("200", "OK");
                response.setResponse(dR);
                response.setJWT(token);
                response.setAlumno(a);
                return response;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            DefaultResponse dR = new DefaultResponse("500", "Error");
            response.setResponse(dR);
            return response;
        }

        return null;
    }

    public RegisterResponse register(String username, String password) throws NoSuchAlgorithmException {
        String encryptedpassword = null;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(password.getBytes());

            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            createConection();
            String sql = " insert into LOGIN (userId, password, rol)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, encryptedpassword);
            preparedStmt.setString(3, "ALUMNO");

            preparedStmt.execute();

            System.out.println("Plain-text password: " + password);
            System.out.println("Encrypted password using MD5: " + encryptedpassword);
            con.close();

            final String token = jwtUtilService
                    .generateToken(new User(username, encryptedpassword, new ArrayList<>()));

            RegisterResponse response = new RegisterResponse();
            DefaultResponse defaultResponse = new DefaultResponse("200", "OK");
            response.setResponse(defaultResponse);
            response.setMessage("Usuario " + username + " creado!");
            response.setJWT(token);
            return response;

        } catch (Exception e) {
            System.out.println(e);
        }

        RegisterResponse response = new RegisterResponse();
        DefaultResponse defaultResponse = new DefaultResponse("400", "Error");
        response.setResponse(defaultResponse);
        response.setMessage("Error creando usuario " + username);
        return response;
    }

    public CreateAlumnoResponse createAlumno(Alumno alumno) {
        try {
            createConection();
            String sql = "insert into ALUMNO (cedulaIdentidad, nombre, apellido, fechaNacimiento, email, idCarrera, userId, idCampeon, idSubcampeon)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.parseInt(alumno.getCedulaIdentidad()));
            preparedStmt.setString(2, alumno.getNombre());
            preparedStmt.setString(3, alumno.getApellido());
            preparedStmt.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            preparedStmt.setString(5, alumno.getEmail());
            preparedStmt.setInt(6, Integer.parseInt(alumno.getIdCarrera()));
            preparedStmt.setString(7, alumno.getUserId());
            preparedStmt.setInt(8, Integer.parseInt(alumno.getIdCampeon()));
            preparedStmt.setInt(9, Integer.parseInt(alumno.getIdSubcampeon()));

            preparedStmt.execute();

            DefaultResponse defaultResponse = new DefaultResponse("200", "Alumno creado Correctamente");
            CreateAlumnoResponse response = new CreateAlumnoResponse(defaultResponse, alumno);
            con.close();
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            DefaultResponse defaultResponse = new DefaultResponse("400", "Alumno ya creado");
            CreateAlumnoResponse response = new CreateAlumnoResponse(defaultResponse, alumno);
            return response;

        }
    }

    public ObtenerCarrerasResponse obtenerCarreras() throws SQLException, ClassNotFoundException {
        createConection();
        DefaultResponse defaultResponse = new DefaultResponse("200", "Carreras obtenidas correctamente");
        List<Carrera> carreras = new ArrayList<>();

        String sql = "select * from CARRERA";
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {
            Carrera c = new Carrera(rs.getString(1), rs.getString(2));
            carreras.add(c);

        }
        ObtenerCarrerasResponse response = new ObtenerCarrerasResponse(defaultResponse,
                carreras.toArray(new Carrera[0]));

        return response;
    }

}
