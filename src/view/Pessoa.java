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

    public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getTempoDeRua() {
		return tempoDeRua;
	}

	public void setTempoDeRua(int tempoDeRua) {
		this.tempoDeRua = tempoDeRua;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPessoa(){
        return local + "," + data + "," + nome+"," + sexo + "," + idade + "," + ocupacao + "," + tempoDeRua + "," + usuario;
    }

    public void setPessoaCadastro(String nome, String data) {
        this.nome = nome;
        this.data = data;
    }

    public Object[] toArray() {
        return new Object[]{nome, data};
    }

    public Vector<String> toArrayCompleto() {
        Vector<String> vetor = new Vector<>();
        vetor.add(nome);
        vetor.add(data);
        vetor.add(local);
        vetor.add(sexo);
        vetor.add(String.valueOf(idade));
        vetor.add(ocupacao);
        vetor.add(String.valueOf(tempoDeRua));
        vetor.add(usuario);
        return vetor;
    }
}
