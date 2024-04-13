import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;

public class Screen extends JFrame{

    private JTabbedPane tabbedPane;

    public Screen() {
        setTitle("Cadastro - Casa do Povo da Rua");
        setSize(880, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tabbedPane = new JTabbedPane();

        JPanel cadastroPanel = createCadastroPanel();
        JPanel relatorioPanel = createRelatorioPanel();

        tabbedPane.addTab("Cadastro", cadastroPanel);
        tabbedPane.addTab("Relatórios", relatorioPanel);

        add(tabbedPane);

        setVisible(true);
    }

    private JPanel createCadastroPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        Font fontButton = new Font("arial", Font.BOLD, 24);

        JLabel textNome = new JLabel("Nome: ");
        textNome.setBounds(10,70,100,55);
        textNome.setFont(fontButton);
        panel.add(textNome);

        JTextField text = new JTextField();
        text.setBounds(100,70,100,55);
        text.setFont(fontButton);
        panel.add(text);

        JButton buttonCadastro = new JButton("Cadastrar");
        buttonCadastro.setBounds(10, 200, 180, 55);
        buttonCadastro.setFont(fontButton);
        buttonCadastro.addActionListener(e -> JOptionPane.showMessageDialog(null, "Clicou em cadastro"));
        panel.add(buttonCadastro);

        return panel;
    }

    private JPanel createRelatorioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        Font fontButton = new Font("arial", Font.BOLD, 24);

        JLabel labelRelatorio = new JLabel("Relatórios");
        labelRelatorio.setBounds(10, 10, 200, 30);
        labelRelatorio.setFont(fontButton);
        panel.add(labelRelatorio);

        return panel;
    }

    public static void main(String[] args) {
        new Screen();
    }
}
