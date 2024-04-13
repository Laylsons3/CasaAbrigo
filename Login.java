import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
// import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Login extends JFrame {

    private static final String USUARIO_PADRAO = "admin";
    private static final String SENHA_PADRAO = "admin";

    public Login() {
        setTitle("Login - Casa do Povo da Rua");
        setSize(300, 190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        Font caixaDeTexto = new Font("arial", Font.BOLD, 14);

        // Caixa Usuário
        JLabel textNome = new JLabel("Usuário: ");
        textNome.setBounds(10, 20, 100, 30);
        textNome.setFont(caixaDeTexto);
        add(textNome);

        JTextField text = new JTextField();
        text.setBounds(70, 20, 200, 30);
        text.setFont(caixaDeTexto);
        add(text);

        // Caixa Senha
        JLabel textPassword = new JLabel("Senha: ");
        textPassword.setBounds(10, 60, 100, 30);
        textPassword.setFont(caixaDeTexto);
        add(textPassword);

        JPasswordField password = new JPasswordField();
        password.setBounds(70, 60, 200, 30);
        password.setFont(caixaDeTexto);
        add(password);

        // Botões
        Font fontButton = new Font("arial", Font.PLAIN, 14);

        JButton buttonEntrar = new JButton("Entrar");
        buttonEntrar.setBounds(10, 100, 120, 30);
        buttonEntrar.setFont(fontButton);
        buttonEntrar.setForeground(new Color(0, 0, 0));
        buttonEntrar.setBackground(new Color(200, 200, 200));
        add(buttonEntrar);
        buttonEntrar.addActionListener(e -> eventButtonEntrar(text.getText(), password.getPassword()));

        JButton buttonSair = new JButton("Sair");
        buttonSair.setBounds(150, 100, 120, 30);
        buttonSair.setFont(fontButton);
        buttonSair.setForeground(new Color(0, 0, 0));
        buttonSair.setBackground(new Color(200, 200, 200));
        add(buttonSair);
        buttonSair.addActionListener(e -> eventButtonSair());

        setVisible(true);
    }

    private void eventButtonEntrar(String usuario, char[] senha) {
        String senhaString = new String(senha);
        if (usuario.equals(USUARIO_PADRAO) && senhaString.equals(SENHA_PADRAO)) {
            JOptionPane.showMessageDialog(null, "Bem vindo " + USUARIO_PADRAO);
            
            new Screen(); // Redireciona para tela inicial
            dispose(); // Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
        }
    }

    private void eventButtonSair() {
        dispose(); // Fecha a tela de login qundo clica em sair
    }

    public static void main(String[] args) {
        new Login();
    }
}
