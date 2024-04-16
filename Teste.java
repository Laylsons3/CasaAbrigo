public class Teste {
  public String nome;
  private int salario;

  public static void main(String[] args) {
      System.out.println("teste1");

      teste(); // Chama o método teste direto

      Teste objetoTeste = new Teste(); // Criar uma instância da classe Teste
      objetoTeste.printa(); // Chamar o método printa() na instância criada
  }

  // Método estático - Pode ser chamado diretamente da classe, não utiliza o estado do objeto
  public static void teste() {
      System.out.println("teste2");
  }

  // Método de instância - Opera sobre o estado do objeto
  void printa() {
      System.out.println("Salário: " + salario);
  }
}
