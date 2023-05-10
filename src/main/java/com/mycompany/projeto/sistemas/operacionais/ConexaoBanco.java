/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.sistemas.operacionais;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jgmat
 */
public class ConexaoBanco {
   private JdbcTemplate connection;
   public  ConexaoBanco() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");


        dataSource.setUrl("jdbc:sqlserver://hemeratech.database.windows.net:1433;database=hemeratech;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");


        dataSource.setUsername("hemeratech");

        dataSource.setPassword("#Gfgrupo7");

    this.connection = new JdbcTemplate(dataSource);

}

    public JdbcTemplate getConnection() {

        return connection;

    }
    
    
        
        
        
        
    
}
    