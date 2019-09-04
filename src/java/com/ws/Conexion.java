/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
public class Conexion {
    Connection conexion;
    Statement Sentencias;
    ResultSet Datos;
    PreparedStatement psPrepararaSentencia;
    
    public Conexion() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // String url= "jdbc:sqlserver://localhost:1433;databaseName=curso2;integratedSecurity=fals";
            String url= "jdbc:mysql://localhost:3306/prueba";
            conexion= DriverManager.getConnection(url, "root", "");
            //Sentencias = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Sentencias = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet Consultar1 (){
        try {
            Datos = Sentencias.executeQuery("SELECT * FROM registro");
           
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }
 
    public boolean insertUpdate(String nombre, String cedula, String huella_imagen, String huella_template_iso19794){
        String sql="insert into registro( nombre, cedula, huella_imagen, huella_template_iso19794) values(?,?,?,?)";
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
           
            pst.setString(1, nombre);
            pst.setString(2, cedula);
            pst.setString(3, huella_imagen);
            pst.setString(4, huella_template_iso19794);
            
             int n = pst.executeUpdate();
            if (n>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return false;
    }
    
}
