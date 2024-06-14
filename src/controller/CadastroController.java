package controller;

import model.Pessoa;
import java.util.List;
import java.util.ArrayList;

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

    public List<Pessoa> listarDadosFiltrados(String pesquisa, String filtro, String data) {
        // Implementar lógica de filtragem de dados
        return new ArrayList<>();
    }

    public List<Pessoa> listarDados() {
        // Implementar lógica para listar todos os dados
        return new ArrayList<>();
    }

    public void salvarDadosNoBanco(String local, String data, String nome, String sexo, int idade, String ocupacao, String tempoDeRua) {
        // Implementar lógica para salvar dados no banco
    }
}
