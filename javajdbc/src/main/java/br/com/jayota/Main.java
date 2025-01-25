package br.com.jayota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static String nome;
    static String email;
    static String senha;
        public static void main(String[] args) {
            var url = "jdbc:mysql://localhost:3306/jdbc_teste";
            Scanner scanner = new Scanner(System.in);
            try (Connection connection = DriverManager.getConnection(url, "root", "")) {
                System.out.println("Banco conectado!");
                
                int opcao;
                do {
                    exibirMenu();
                    opcao = Integer.parseInt(scanner.nextLine());
                    switch (opcao) {
                        case 0 -> salvarCliente();
                        case 1 -> buscarTodosClientes();
                        case 2 -> buscarClientePorId();
                        case 3 -> atualizarProdutoPorId();
                        case 4 -> excluirCliente();
                        case 5 -> System.exit(0);
                        default -> System.out.println("Opção inválida!");
                                    }
                    } while (opcao != 5);
                } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        }



        private static void exibirMenu() {
            System.out.println("\n### Menu de Operações ###");
            System.out.println("0. Salvar novo cliente");
            System.out.println("1. Buscar todos clientes");
            System.out.println("2. Buscar cliente por ID");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Excluir cliente");
            System.out.println("5. Sair do programa");
            System.out.print("Escolha uma opção: ");
        }   
        
        
        public static void buscarTodosClientes() throws SQLException {
            var url = "jdbc:mysql://localhost:3306/jdbc_teste";
            try (Connection connection = DriverManager.getConnection(url, "root", "")) {
                System.out.println("Banco conectado!");

                var sql = "SELECT * FROM cliente";
                try(Statement stmt = connection.createStatement()){
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id_cliente"));
                        System.out.println("Nome: " + rs.getString("nome"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Senha: " + rs.getString("senha"));
                        System.out.println("----------------------");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        public static void salvarCliente() throws SQLException {
            var url = "jdbc:mysql://localhost:3306/jdbc_teste";
            try (Connection connection = DriverManager.getConnection(url, "root", "")) {
                System.out.println("Banco conectado!");
                System.out.println();
                
                Scanner scanner = new Scanner(System.in);
                System.out.println("Coloque o nome: ");
                String nome = scanner.nextLine();
                System.out.println("Coloque o email: ");
                String email = scanner.nextLine();
                System.out.println("Coloque a senha: ");
                String senha = scanner.nextLine();
                var sql = "INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)";

                try(PreparedStatement stmt = connection.prepareStatement(sql)){
                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setString(3, senha);
                    stmt.executeUpdate();
                }
                System.out.println("Cliente inserido com sucesso!");
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


                
        public static void atualizarProdutoPorId() throws SQLException {
            var url = "jdbc:mysql://localhost:3306/jdbc_teste";
            try(Connection connection = DriverManager.getConnection(url, "root", "")){
                System.out.println("Banco conectado!");
                System.out.println();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Coloque o ID do cliente: ");
                String id_cliente = scanner.nextLine();
                System.out.println("Coloque o nome: ");
                String nome = scanner.nextLine();
                System.out.println("Coloque o email: ");
                String email = scanner.nextLine();
                System.out.println("Coloque a senha: ");
                String senha = scanner.nextLine();
                var sql = "UPDATE cliente SET nome = ?, email = ?, senha = ? WHERE id_cliente = " + id_cliente;
                try(PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setString(3, senha);
                    stmt.executeUpdate();
                }
                System.out.println("Cliente atualizado com sucesso!");
                scanner.close();
            } catch (SQLException e) {
                System.out.println("Erro ao conectar no Banco de Dados: " + e.getMessage());
            }
        }



        public static void buscarClientePorId() throws SQLException{
            var url = "jdbc:mysql://localhost:3306/jdbc_teste";
            try (Connection connection = DriverManager.getConnection(url, "root", "")) {
                System.out.println("Banco conectado!");
                System.out.println();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Coloque o ID do cliente: ");
                String id_cliente = scanner.nextLine();
                var sql = "SELECT * FROM cliente WHERE id_cliente = " + id_cliente;
                try(Statement stmt = connection.createStatement()){
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id_cliente"));
                        System.out.println("Nome: " + rs.getString("nome"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Senha: " + rs.getString("senha"));
                        System.out.println("----------------------");
                    }
                }
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        public static void excluirCliente() throws SQLException{
            var url = "jdbc:mysql://localhost:3306/jdbc_teste";
            try (Connection connection = DriverManager.getConnection(url, "root", "")) {
                System.out.println("Banco conectado!");
                System.out.println();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Coloque o ID do cliente: ");
                String id_cliente = scanner.nextLine();
                var sql = "DELETE FROM cliente WHERE id_cliente = " + id_cliente;
                try(Statement stmt = connection.createStatement()){
                    stmt.executeUpdate(sql);
                }
                System.out.println("Cliente deletado com sucesso!");
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}