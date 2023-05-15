/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.sistemas.operacionais;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author jgmat
 */
public class TesteBanco {

    public static void main(String[] args) {
        // EXEMPLO POC(PROOF OF CONCEPT) DA CONEXÃO COM BANCO DE DADOS MY SQL LOCAL EM UM CONTAINER E MANIPULANDO DADOS
        Scanner leitorB = new Scanner(System.in);
        ConexaoBancoLocal conexaoBancoLocal = new ConexaoBancoLocal();
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate conexao = conexaoBanco.getConnection();
        JdbcTemplate conexaoLocal = conexaoBancoLocal.getConnection();
        String emailUsuarioSessaoLocal = "";
        String emailUsuarioSessaoNuvem = "";
        List<ObjetoUsuario> listaObjetoUsuario;
        List<Componentes> listaComponentesUsuario;


        Runnable criarTabela = () -> {
            conexaoLocal.execute("create table Funcionario(id int AUTO_INCREMENT PRIMARY KEY, email varchar(45), senha varchar(45));");
        };

        Runnable deletarTabelas = () -> {
            conexaoLocal.execute("drop table Funcionario;");
            conexaoLocal.execute("drop table Componentes;");
        };

        Runnable criarTabelaComponentes = () -> {
            conexaoLocal.execute("create table Componentes(id int AUTO_INCREMENT PRIMARY KEY, SistemaOperacional varchar(45), ModeloProcessador varchar(45), MacAddress varchar(45), MemoriaTotal varchar(45), MemoriaArmazenamento varchar(45), EmailUsuario varchar(45));");
        };

        Scanner leitor = new Scanner(System.in);
        Integer opcao;
        do {
            System.out.println("-".repeat(30));
            System.out.println("Parte Nuvem");
            System.out.println("1 - Logar nuvem");
            System.out.println("2 - Inserir dados compomentes sqlNuvem");
            System.out.println("Parte mySQL Local Container");
            System.out.println("3 - Login Local");
            System.out.println("4 - Criar usuário mysql container");
            System.out.println("5 - Criar tabelas de usuário e componente");
            System.out.println("6 - Deletar tabela de usuário e componente");
            System.out.println("7 - Mostrar lista de usuários mysqlLocal");
            System.out.println("8 - Inserir dados de componentes no mySqlLocal");
            System.out.println("9 - Printar tabela Componentes mySql e SqlServer");
            System.out.println("0 - Sair");
            System.out.println("-".repeat(30));
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:

                    System.out.println("-".repeat(30));
                    System.out.println("teste sql na azure");
                    System.out.println("login");
                    String email = leitorB.nextLine();
                    System.out.println("senha");
                    String senha = leitorB.nextLine();
                    listaObjetoUsuario = conexao.query("select *" + " from Funcionario where email = ? and senha = ?",
                            new ObjetoUsuarioRowMapper(), email, senha);

                    if (listaObjetoUsuario.stream().anyMatch(usuario ->
                            usuario.getEmailUsuario().equalsIgnoreCase(email)
                                    && usuario.getSenhaUsuario().equals(senha))) {
                        emailUsuarioSessaoNuvem = email;
                        System.out.println("Login deu certo!");
                    } else {
                        System.out.println("Deu errado");
                    }
                    break;

                case 2:
                    if(emailUsuarioSessaoNuvem.equals("")){
                        System.out.println("deve estar logado");
                    }else{
                        System.out.println("-".repeat(30));
                        ComponentesMaquina componentes = new ComponentesMaquina();
                        System.out.println("SistemaOperacional");
                        System.out.println(componentes.getSistemaOperacional());
                        System.out.println("ModeloProcessador");
                        System.out.println(componentes.getModeloProcessador());
                        System.out.println("MacAddress");
                        System.out.println(componentes.getHostName());
                        System.out.println("MemoriaTotal");
                        System.out.println(componentes.getMemoriaTotal());
                        System.out.println("MemoriaArmazenamento");
                        System.out.println(componentes.getMemoriaArmazenamento());
                        String finalEmailUsuarioSessaoNuvem = emailUsuarioSessaoNuvem;
                        try{
                            Consumer<ComponentesMaquina> insercaoBancoAzure = (ComponentesMaquina c) -> {
                                String sql = "INSERT INTO Componentes (SistemaOperacional, ModeloProcessador, MacAddress, MemoriaTotal, MemoriaArmazenamento, EmailUsuario) VALUES (?, ?, ?, ?, ?, ?)";
                                conexao.update(sql, componentes.getSistemaOperacional(), componentes.getModeloProcessador(), componentes.getHostName(), componentes.getMemoriaTotal(), componentes.getMemoriaArmazenamento(), finalEmailUsuarioSessaoNuvem);
                            };
                            insercaoBancoAzure.accept(componentes);
                            System.out.println("Insert deu certo!");
                        }catch(Exception e){
                            System.out.println("Erro na inserção");
                        }
                    }



                    break;

                case 3:

                    System.out.println("-".repeat(30));
                    System.out.println("teste sql no container");
                    System.out.println("login");
                    email = leitorB.nextLine();
                    System.out.println("senha");
                    senha = leitorB.nextLine();

                    listaObjetoUsuario = conexaoLocal.query("select *" + " from Funcionario where email = ? and senha = ?",
                            new ObjetoUsuarioRowMapper(), email, senha);

                    if (listaObjetoUsuario.stream().anyMatch(usuario ->
                            usuario.getEmailUsuario().equalsIgnoreCase(email)
                                    && usuario.getSenhaUsuario().equals(senha))) {
                        emailUsuarioSessaoLocal = email;
                        System.out.println("Login deu certo!");
                    } else {
                        System.out.println("Deu errado");
                    }

                    break;
                case 4:

                    System.out.println("-".repeat(30));
                    System.out.println("login");
                    String emailInput = leitorB.nextLine();
                    System.out.println("senha");
                    String senhaInput = leitorB.nextLine();

                    listaObjetoUsuario = conexaoLocal.query("select *" + " from Funcionario where email = ? and senha = ?",
                            new ObjetoUsuarioRowMapper(), emailInput, senhaInput);

                    if (listaObjetoUsuario.stream().anyMatch(usuario ->
                            usuario.getEmailUsuario().equalsIgnoreCase(emailInput))) {
                        System.out.println("Usuário já cadastrado");
                    } else {

                        int colunasAfetadas = conexaoLocal.update("insert into Funcionario(email, senha) values (?, ?)", emailInput, senhaInput);
                        if (colunasAfetadas > 0) {
                            System.out.println("Deu certo, usuário cadastrado!");
                        } else {
                            System.out.println("Deu errado, erro interno!");
                        }
                    }
                    break;

                case 5:

                    System.out.println("-".repeat(30));

                    try {
                        criarTabela.run();
                        criarTabelaComponentes.run();
                        System.out.println("Tabelas criadas com sucesso!");
                    } catch (DataAccessException e) {
                        System.out.println("Deu errado, tabela ja existe");
                    }
                    break;
                case 6:

                    System.out.println("-".repeat(30));

                    try {
                        deletarTabelas.run();
                        System.out.println("tabela deletada com sucesso!");
                    } catch (DataAccessException e) {
                        System.out.println("Falha ao deletar tabela");
                    }
                    break;
                case 7:

                    System.out.println("-".repeat(30));

                    listaObjetoUsuario = conexaoLocal.query("select * from Funcionario;",
                            new ObjetoUsuarioRowMapper());
                    listaObjetoUsuario.forEach(System.out::println);
                    break;
                case 8:
                    if(emailUsuarioSessaoLocal.equals("")){
                        System.out.println("Deve fazer login primeiro");
                    }else{
                        System.out.println("-".repeat(30));
                        ComponentesMaquina componentesB = new ComponentesMaquina();
                        System.out.println("SistemaOperacional");
                        System.out.println(componentesB.getSistemaOperacional());
                        System.out.println("ModeloProcessador");
                        System.out.println(componentesB.getModeloProcessador());
                        System.out.println("MacAddress");
                        System.out.println(componentesB.getHostName());
                        System.out.println("MemoriaTotal");
                        System.out.println(componentesB.getMemoriaTotal());
                        System.out.println("MemoriaArmazenamento");
                        System.out.println(componentesB.getMemoriaArmazenamento());
                        try{
                            String finalEmailUsuarioSessaoLocal = emailUsuarioSessaoLocal;
                            Consumer<ComponentesMaquina> insercaoBancoLocal = (ComponentesMaquina c) -> {
                                String sql = "INSERT INTO Componentes (SistemaOperacional, ModeloProcessador, MacAddress, MemoriaTotal, MemoriaArmazenamento, EmailUsuario) VALUES (?, ?, ?, ?, ?, ?)";
                                conexaoLocal.update(sql, componentesB.getSistemaOperacional(), componentesB.getModeloProcessador(), componentesB.getHostName(), componentesB.getMemoriaTotal(), componentesB.getMemoriaArmazenamento(), finalEmailUsuarioSessaoLocal);
                            };


                            insercaoBancoLocal.accept(componentesB);
                            System.out.println("Insert deu certo!");

                        }catch (Exception e){
                            System.out.println("não foi possível inserir no banco");
                        }
                    }

                    break;
                case 9:
                    System.out.println("-".repeat(30));
                    System.out.println("Lista de Computadores e seus respectivos componentes Nuvem e Local");
                    System.out.println("-".repeat(30));
                    System.out.println("Lista computadores Local");
                    try{
                        listaComponentesUsuario = conexaoLocal.query("select * from Componentes", new ComponentesRowMapper());
                        listaComponentesUsuario.forEach(System.out::println);
                    }catch (Exception e){
                        System.out.println("Deu errado Local!");
                    }
                    System.out.println("-".repeat(30));
                    System.out.println("lista computadores Nuvem");
                    System.out.println("-".repeat(30));
                    try{
                        listaComponentesUsuario = conexao.query("select * from Componentes", new ComponentesRowMapper());
                        listaComponentesUsuario.forEach(System.out::println);
                    }catch (Exception e){
                        System.out.println("Deu errado Nuvem!");
                    }

                    break;


                default:
                    System.out.println("Até mais!");
            }
        } while (opcao != 0);
    }
}
