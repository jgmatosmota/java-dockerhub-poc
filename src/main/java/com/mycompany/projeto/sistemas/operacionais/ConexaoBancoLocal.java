package com.mycompany.projeto.sistemas.operacionais;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBancoLocal {
   private JdbcTemplate connection;
   public  ConexaoBancoLocal() {
       BasicDataSource dataSource = new BasicDataSource();

       dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://172.20.0.2:3306/meuDB?useSSL=false");
       dataSource.setUsername("meuDB");
       dataSource.setPassword("1234");

       // Conexão com um banco de dados MySQL em um contêiner Docker
       // O endereço IP do contêiner é 172.17.0.2 e a porta padrão do MySQL é 3306
       // Lembre-se de substituir o nome do banco de dados e as credenciais apropriadas

       this.connection = new JdbcTemplate(dataSource);

}

    public JdbcTemplate getConnection() {

        return connection;

    }
}
