package view;
import java.util.Vector;

public class Pessoa {
    private String local, nome, sexo, ocupacao;
    private String data;
    private int idade, tempoDeRua;
    private String usuario = "admin";
    
    public void setPessoa(String local, String nome, String sexo, String ocupacao, String data, int idade, int tempoDeRua) {
        this.local = local;
        this.nome = nome;
        this.sexo = sexo;
        this.ocupacao = ocupacao;
        this.data = data;
        this.idade = idade;
        this.tempoDeRua = tempoDeRua;
    }

    public String getPessoa(){
        return local + "," + data + "," + nome+"," + sexo + "," + idade + "," + ocupacao + "," + tempoDeRua + "," + usuario;
    }

    public Vector<String> toArray() {
        Vector<String> vetor = new Vector<>();
        vetor.add(local);
        vetor.add(data);
        vetor.add(nome);
        vetor.add(sexo);
        vetor.add(String.valueOf(idade));
        vetor.add(ocupacao);
        vetor.add(String.valueOf(tempoDeRua));
        vetor.add(usuario);
        return vetor;
    }
}
