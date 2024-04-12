import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Screen extends JFrame{
  public Screen() {
    setTitle("Cadastro - Casa do Povo da Rua");
    setVisible(true);
    setSize(1200,800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);

    JButton buttonCadastro = new JButton("Cadastro");
    buttonCadastro.setBounds(10, 10, 180, 55);
    Font fontButtonCadastro = new Font("arial", Font.BOLD, 24);
    buttonCadastro.setFont(fontButtonCadastro);
    buttonCadastro.setForeground(new Color(0,0,0));
    buttonCadastro.setBackground(new Color(200,200,200));
    add(buttonCadastro);
    
    JButton buttonRelatorio = new JButton("Relatorio");
    buttonRelatorio.setBounds(200, 10, 180, 55);
    Font fontButtonRelatorio = new Font("arial", Font.BOLD, 24);
    buttonRelatorio.setFont(fontButtonRelatorio);
    buttonRelatorio.setForeground(new Color(0,0,0));
    buttonRelatorio.setBackground(new Color(200,200,200));
    add(buttonRelatorio);

    buttonCadastro.addActionListener(this::eventoButtonCadastro);
    buttonRelatorio.addActionListener(this::eventoButtonRelatorio);
  }

  private void eventoButtonCadastro(ActionEvent actionEvent) {
    JOptionPane.showMessageDialog(null, "Clicou em cadastro");
  }

  private void eventoButtonRelatorio(ActionEvent actionEvent) {
    JOptionPane.showMessageDialog(null, "Clicou em relatório");
  }

}