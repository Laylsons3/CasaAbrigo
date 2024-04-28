import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

        JLabel textOcupacao = new JLabel("Ocupação: ");
        textOcupacao.setBounds(540,100,100,30);
        textOcupacao.setFont(fontPadrao);
        panel.add(textOcupacao);
        JTextField caixaOcupacao = new JTextField();
        caixaOcupacao.setBounds(540,130,120,30);
        caixaOcupacao.setFont(fontPadrao);
        panel.add(caixaOcupacao);

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
        buttonCadastro.addActionListener(e -> {

            String local = caixaLocal.getText();
            String data = caixaData.getText();
            String nome = caixaNome.getText();
            String sexo = (String) comboBoxSexo.getSelectedItem();
            String idade = caixaIdade.getText();
            String ocupacao = caixaOcupacao.getText();
            String tempoDeRua = caixaTempoRua.getText();
            
            String usuario = "admin";

            String arquivoCSV = "dados.csv";

            try (
                PrintWriter writer = 
                    new PrintWriter(
                    new FileWriter(arquivoCSV, true))
                    ) {
                writer.println(local + "," + data + "," + nome+"," + sexo + "," + idade + "," + ocupacao + "," + tempoDeRua + "," + usuario);

                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + ioException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(buttonCadastro);

        return panel;
    }

    private JPanel createRelatorioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        Font fontPadrao = new Font("arial", Font.BOLD, 14);

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
