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
    private static final String URL = "jdbc:h2:~/test";    
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
    
    public void selectPrueba() throws SQLException {
        String sql = "SELECT * FROM Materia ORDER BY materia_id;";
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
