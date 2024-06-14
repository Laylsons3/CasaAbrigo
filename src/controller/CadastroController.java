package controller;

import model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CadastroController {

    private List<Pessoa> pessoas;

    public CadastroController() {
        pessoas = new ArrayList<>();
    }

    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void salvarDadosNoBanco(String local, String data, String nome, String sexo, int idade, String ocupacao, String tempoDeRua) {
        String sql = "INSERT INTO pessoas (local, data, nome, sexo, idade, ocupacao, tempoderua) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, local);
            pstmt.setString(2, data);
            pstmt.setString(3, nome);
            pstmt.setString(4, sexo);
            pstmt.setInt(5, idade);
            pstmt.setString(6, ocupacao);
            pstmt.setString(7, tempoDeRua);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pessoa> listarDadosFiltrados(String pesquisa, String filtro, String data) {
        List<Pessoa> lista = new ArrayList<>();
        String sql = null;

        if (data.isEmpty()) {
            if (filtro.equals("Todos")) {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? ORDER BY nome ASC";
            } else {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? AND sexo = ? ORDER BY nome ASC";
            }
        } else {
            if (filtro.equals("Todos")) {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? AND data = ? ORDER BY nome ASC";
            } else {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? AND sexo = ? AND data = ? ORDER BY nome ASC";
            }
        }

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + pesquisa.toUpperCase() + "%");
            if (!filtro.equals("Todos")) {
                pstmt.setString(2, filtro);
                if (!data.isEmpty()) {
                    pstmt.setString(3, data);
                }
            } else if (!data.isEmpty()) {
                pstmt.setString(2, data);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setPessoa(rs.getString("local"), rs.getString("data"), rs.getString("nome"),
                        rs.getString("sexo"), rs.getString("ocupacao"), rs.getInt("idade"), rs.getString("tempoderua"));
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Pessoa> listarDados() {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";

        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setPessoa(rs.getString("local"), rs.getString("data"), rs.getString("nome"),
                        rs.getString("sexo"), rs.getString("ocupacao"), rs.getInt("idade"), rs.getString("tempoderua"));
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
