/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfarmacy01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class database {
    
    public static Connection conexaoDB(){
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver"); 
            
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/farmacia", "root", ""); 
            return conexao; 
            
        } catch (ClassNotFoundException | SQLException e) { System.out.println("erro conectando: ");
            return null; 
        }
       
    }
    
}
