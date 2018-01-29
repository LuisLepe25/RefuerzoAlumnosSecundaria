/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luisd
 */
public class DAO {
    private static final String URL = "jdbc:h2:./Preguntas";    
    private static final String DRIVERNAME = "org.h2.Driver";   
    private static final String USERNAME = "sa";   
    private static final String PASSWORD = "";
    private static Connection con;
    private static String urlstring;

    public void conectar() throws SQLException, ClassNotFoundException{
        Class.forName(DRIVERNAME);
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    //Desconectar a la Base de datos
    public void desconectar() throws SQLException, ClassNotFoundException{
        con.close();
    }
    
    /**
     * Este metodo solo debe usarse si se borra la base de datos embebida
     * @throws SQLException 
     */
    public void crearBaseDeDatos() throws SQLException{
        String sql = "";
        sql += "CREATE TABLE Materia ( materia_id int NOT NULL AUTO_INCREMENT, materia_nombre char(100) NOT NULL, PRIMARY KEY (materia_id));";
        sql += "CREATE TABLE Usuario (usuario_id int NOT NULL AUTO_INCREMENT, usuario_nickname char(50) NOT NULL, PRIMARY KEY (usuario_id));";
        sql += "CREATE TABLE Pregunta ( pregunta_id int NOT NULL AUTO_INCREMENT, pregunta_contenido char(MAX) NOT NULL,  pregunta_materia int NOT NULL,  PRIMARY KEY (pregunta_id), FOREIGN KEY (pregunta_materia) REFERENCES Materia(materia_id));";
        sql += "CREATE TABLE PreguntasUsuario ( usuario_id int NOT NULL, pregunta_id int NOT NULL, FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id), FOREIGN KEY (pregunta_id) REFERENCES Pregunta(pregunta_id));";
        PreparedStatement ps1 = con.prepareStatement(sql);
        ps1.executeUpdate();
        String sql2 = "INSERT INTO MATERIA (materia_nombre) VALUES ('Matematicas'); INSERT INTO MATERIA (materia_nombre) VALUES ('Espanol'); INSERT INTO MATERIA (materia_nombre) VALUES('Ingles'); INSERT INTO MATERIA (materia_nombre) VALUES('Artes'); INSERT INTO MATERIA (materia_nombre) VALUES('Biologia'); INSERT INTO MATERIA (materia_nombre) VALUES('Historia'); INSERT INTO MATERIA (materia_nombre) VALUES('Geografia');";
        PreparedStatement ps = con.prepareStatement(sql2);
        ps.executeUpdate();        
    }
    
    public void selectPrueba() throws SQLException {
        String sql = "SELECT * FROM materia;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int i;
        String str;
        
        while(rs.next()){
            i = rs.getInt("materia_id");
            str = rs.getString("materia_nombre");
            System.out.println("ID: " + i + " NAME: " + str);
        }
    }
}
