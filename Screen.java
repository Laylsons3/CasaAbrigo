import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame{

    private JTabbedPane tabbedPane;

    public Screen() {
        setTitle("Cadastro - Casa do Povo da Rua");
        setSize(810, 600);
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

        Font fontPadrao = new Font("arial", Font.PLAIN, 14);

        //Dados do local
        JLabel textLocal = new JLabel("Local: ");
        textLocal.setBounds(10,10,100,30);
        textLocal.setFont(fontPadrao);
        panel.add(textLocal);
        JTextField caixaLocal = new JTextField();
        caixaLocal.setBounds(70,10,300,30);
        caixaLocal.setFont(fontPadrao);
        caixaLocal.setText("Casa do Povo da Rua");
        panel.add(caixaLocal);

        JLabel textData = new JLabel("Data: ");
        textData.setBounds(10,40,100,30);
        textData.setFont(fontPadrao);
        panel.add(textData);
        JTextField caixaData = new JTextField();
        caixaData.setBounds(70,40,300,30);
        caixaData.setFont(fontPadrao);
        panel.add(caixaData);

        //Dados da Pessoa
        JLabel textNome = new JLabel("Nome: ");
        textNome.setBounds(10,100,100,30);
        textNome.setFont(fontPadrao);
        panel.add(textNome);
        JTextField caixaNome = new JTextField();
        caixaNome.setBounds(10,130,300,30);
        caixaNome.setFont(fontPadrao);
        panel.add(caixaNome);

        JLabel textSexo = new JLabel("Sexo: ");
        textSexo.setBounds(320,100,130,30);
        textSexo.setFont(fontPadrao);
        panel.add(textSexo);
        String[] opcoesSexo = {"Masculino", "Feminino", "Prefiro não dizer"};
        JComboBox<String> comboBoxSexo = new JComboBox<>(opcoesSexo);
        comboBoxSexo.setBounds(320,130,130,30);
        comboBoxSexo.setFont(fontPadrao);
        panel.add(comboBoxSexo);

        JLabel textIdade = new JLabel("Idade: ");
        textIdade.setBounds(460,100,100,30);
        textIdade.setFont(fontPadrao);
        panel.add(textIdade);
        JTextField caixaIdade = new JTextField();
        caixaIdade.setBounds(460,130,70,30);
        caixaIdade.setFont(fontPadrao);
        panel.add(caixaIdade);

        JLabel textProfissao = new JLabel("Profissão: ");
        textProfissao.setBounds(540,100,100,30);
        textProfissao.setFont(fontPadrao);
        panel.add(textProfissao);
        JTextField caixaProfissao = new JTextField();
        caixaProfissao.setBounds(540,130,120,30);
        caixaProfissao.setFont(fontPadrao);
        panel.add(caixaProfissao);

        JLabel textTempoRua = new JLabel("Tempo de rua: ");
        textTempoRua.setBounds(670,100,100,30);
        textTempoRua.setFont(fontPadrao);
        panel.add(textTempoRua);
        JTextField caixaTempoRua = new JTextField();
        caixaTempoRua.setBounds(670,130,110,30);
        caixaTempoRua.setFont(fontPadrao);
        panel.add(caixaTempoRua);


        //Botão de cadastro
        JButton buttonCadastro = new JButton("Cadastrar");
        buttonCadastro.setBounds(670, 170, 110, 40);
        buttonCadastro.setFont(fontPadrao);
        buttonCadastro.addActionListener(e -> JOptionPane.showMessageDialog(null, "Clicou em cadastro"));
        panel.add(buttonCadastro);

        return panel;
    }

    private JPanel createRelatorioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        Font fontPadrao = new Font("arial", Font.BOLD, 24);

        JLabel labelRelatorio = new JLabel("Relatórios");
        labelRelatorio.setBounds(10, 10, 200, 30);
        labelRelatorio.setFont(fontPadrao);
        panel.add(labelRelatorio);

        return panel;
    }

    public static void main(String[] args) {
        new Screen();
    }
}
