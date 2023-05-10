/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto.sistemas.operacionais;

import java.util.List;
import java.util.Scanner;

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
        List<ObjetoUsuario> listaObjetoUsuario;
        Runnable criarTabela = () -> {
            conexaoLocal.execute("create table Funcionario(email varchar(45), senha varchar(45));");
        };
        Runnable deletarTabela = () -> {
            conexaoLocal.execute("drop table Funcionario;");
        };

        Scanner leitor = new Scanner(System.in);
        Integer opcao;
        do {
            System.out.println("-".repeat(30));
            System.out.println("1 - testar login nuvem");
            System.out.println("2 - teste login Local");
            System.out.println("3 - criar usuário mysql container");
            System.out.println("4 - criar tabela de usuários");
            System.out.println("5 - deletar tabela de usuários");
            System.out.println("6 - mostrar lista de usuários mysqlLocal");
            System.out.println("0 - sair");
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
                        System.out.println("Login deu certo!");
                    } else {
                        System.out.println("Deu errado");
                    }
                    break;
                case 2:

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
                        System.out.println("Login deu certo!");
                    } else {
                        System.out.println("Deu errado");
                    }

                    break;
                case 3:

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

                case 4:

                    System.out.println("-".repeat(30));

                    try {
                        criarTabela.run();
                        System.out.println("Tabela criada com sucesso!");
                    } catch (DataAccessException e) {
                        System.out.println("Deu errado, tabela ja existe");
                    }
                    break;
                case 5:

                    System.out.println("-".repeat(30));

                    try {
                        deletarTabela.run();
                        System.out.println("tabela deletada com sucesso!");
                    } catch (DataAccessException e) {
                        System.out.println("Falha ao deletar tabela");
                    }
                    break;
                case 6:

                    System.out.println("-".repeat(30));

                    listaObjetoUsuario = conexaoLocal.query("select * from Funcionario;",
                            new ObjetoUsuarioRowMapper());
                    listaObjetoUsuario.forEach(System.out::println);
                    break;

                default:
                    System.out.println("Até mais!");
            }
        } while (opcao != 0);
    }
}
