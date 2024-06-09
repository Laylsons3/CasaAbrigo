package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	
	private static final String USUARIO_PADRAO = "admin";
	private static final String SENHA_PADRAO = "admin";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField password;

	public Login() {
		setTitle("Login - Casa do Povo da Rua");
		setBounds(100, 100, 815, 532);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panelEsquerdo = new JPanel();
		panelEsquerdo.setBackground(new Color(95, 158, 160));
		panelEsquerdo.setBounds(0, 0, 400, 500);
		contentPane.add(panelEsquerdo);
		panelEsquerdo.setLayout(null);
		
		JPanel caixaImagemETitulo = new JPanel();
		caixaImagemETitulo.setBackground(new Color(95, 158, 160));
		caixaImagemETitulo.setBounds(89, 147, 213, 217);
		panelEsquerdo.add(caixaImagemETitulo);
				
		JLabel labelImage = new JLabel("");
		caixaImagemETitulo.add(labelImage);
		
		JLabel labelTitulo = new JLabel("CASA DO POVO DA RUA");
		labelTitulo.setFont(new Font("Poppins", Font.BOLD, 16));
		caixaImagemETitulo.add(labelTitulo);
		
		JPanel panelDireito = new JPanel();
		panelDireito.setBackground(new Color(255, 255, 255));
		panelDireito.setBounds(400, 0, 400, 500);
		contentPane.add(panelDireito);
		panelDireito.setLayout(null);
		
		JLabel tituloLogin = new JLabel("LOGIN");
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setFont(new Font("Poppins", Font.BOLD, 20));
		tituloLogin.setBounds(10, 107, 380, 30);
		panelDireito.add(tituloLogin);
		
		JPanel caixaLogin = new JPanel();
		caixaLogin.setBackground(new Color(255, 255, 255));
		caixaLogin.setBounds(51, 147, 314, 205);
		panelDireito.add(caixaLogin);
		caixaLogin.setLayout(null);
		
		JLabel labelUsuario = new JLabel("Usuário");
		labelUsuario.setFont(new Font("Poppins", Font.PLAIN, 16));
		labelUsuario.setBounds(10, 26, 78, 34);
		caixaLogin.add(labelUsuario);
		
		user = new JTextField();
		user.setFont(new Font("Poppins", Font.PLAIN, 16));
		user.setBounds(98, 26, 200, 34);
		caixaLogin.add(user);
		user.setColumns(10);
		
		JLabel labelPassword = new JLabel("Senha");
		labelPassword.setFont(new Font("Poppins", Font.PLAIN, 16));
		labelPassword.setBounds(10, 70, 78, 34);
		caixaLogin.add(labelPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password.setBounds(98, 70, 200, 34);
		caixaLogin.add(password);
		
		JButton entrar = new JButton("Entrar");
		
		entrar.addActionListener(e -> testarUsuarioESenha(user.getText(), password.getPassword()));
		
		entrar.setBackground(new Color(95, 158, 160));
		entrar.setFont(new Font("Poppins", Font.PLAIN, 16));
		entrar.setBounds(213, 114, 85, 34);
		caixaLogin.add(entrar);

	}
	
	private void testarUsuarioESenha(String user, char[] password) {
    String passwordString = new String(password);
    
    if (user.isEmpty() || passwordString.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Digite o usuário e/ou senha");
    } else {
        if (user.equals(USUARIO_PADRAO) && passwordString.equals(SENHA_PADRAO)) {
            JOptionPane.showMessageDialog(null, "Bem vindo " + user);
						Cadastro frameCadastro = new Cadastro();
						frameCadastro.setVisible(true);
						dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
        }
    }
	}
	
	public static void main(String[] args) {
		new Login();
	}

}
