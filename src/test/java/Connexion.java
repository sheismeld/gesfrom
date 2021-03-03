

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yves
 */
public class Connexion {
    private  Connection con;
    private static ResultSet rset;
    private static Statement ste;
    
    public  Connection connexion () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lo54formation";
            String user = "root";
            String password = "";
         
            try {
                con = DriverManager.getConnection(url, user, password);
                } catch (SQLException ex) {
              System.out.println("erreur de connexion : "+ex.getMessage());
            }
          
        } catch (ClassNotFoundException ex) {
            System.out.println("erreur de chargement de la classe : "+ex.getMessage());
        }
        
        return con;
    }
    
    public static void main(String argg []){

        Connexion c = new Connexion();
        try {
            

            ste = c.connexion().createStatement();
            String sql ="SELECT * FROM `formation` WHERE 1";
            rset = ste.executeQuery(sql);
            while(rset.next()){
                System.out.println(" Nom : "+rset.getNString("FRT_LIBELLE")+ "   Description : "+rset.getNString("FRT_DESCRIPTION"));
            }
        } catch (SQLException ex) {
            System.out.println("erreur de creation de statment : "+ex.getMessage());
        }
        
        System.out.println(c.connexion());
        
    }
    
}
