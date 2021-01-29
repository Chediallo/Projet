/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.gui_gestion_etudiant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mohamed
 */
public class Connecter {
     Connection con;
    public Connecter(){
    try{
    Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException e){
    System.err.println(e);
            }
    try{
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/guiprojet?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "Kokounonou");
    }catch(SQLException e){System.err.println(e);}
    }
    Connection obtenirconnexion(){return con;}
    
    
    
    
}
