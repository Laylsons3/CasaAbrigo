package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    public static final String CAMINHO_ARQUIVO = "dados.csv";
    private static final Font FONT_PADRAO = new Font("Arial", Font.PLAIN, 14);
    private JTabbedPane tabbedPane;
    private JTextField caixaLocal, caixaData, caixaNome, caixaIdade, caixaOcupacao, caixaTempoRua;
    private JComboBox<String> comboBoxSexo;
    private JScrollPane tabelaScrollPane;
    private DefaultTableModel tableModel;

    public Cadastro() {
        setTitle("Cadastro - Casa do Povo da Rua");
        setSize(810, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        JPanel cadastroPanel = createCadastroPanel();
        tabbedPane.addTab("Cadastro", cadastroPanel);
        getContentPane().add(tabbedPane);

        setVisible(true);
    }

    private JPanel createCadastroPanel() {
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(null);

        JLabel textLocal = new JLabel("Local: ");
        textLocal.setBounds(10, 10, 100, 30);
        textLocal.setFont(FONT_PADRAO);
        panelCadastro.add(textLocal);

        caixaLocal = new JTextField();
        caixaLocal.setBounds(70, 10, 300, 30);
        caixaLocal.setFont(FONT_PADRAO);
        caixaLocal.setText("Casa do Povo da Rua");
        panelCadastro.add(caixaLocal);

        JLabel textData = new JLabel("Data: ");
        textData.setBounds(10, 40, 100, 30);
        textData.setFont(FONT_PADRAO);
        panelCadastro.add(textData);

        caixaData = new JTextField();
        caixaData.setBounds(70, 40, 300, 30);
        caixaData.setFont(FONT_PADRAO);
        caixaData.setText("18/05/2024");
        panelCadastro.add(caixaData);

        JLabel textNome = new JLabel("Nome: ");
        textNome.setBounds(10, 100, 100, 30);
        textNome.setFont(FONT_PADRAO);
        panelCadastro.add(textNome);

        caixaNome = new JTextField();
        caixaNome.setBounds(10, 130, 300, 30);
        caixaNome.setFont(FONT_PADRAO);
        caixaNome.setText("Nome teste");
        panelCadastro.add(caixaNome);

        JLabel textSexo = new JLabel("Sexo: ");
        textSexo.setBounds(320, 100, 130, 30);
        textSexo.setFont(FONT_PADRAO);
        panelCadastro.add(textSexo);
        String[] opcoesSexo = {"Masculino", "Feminino", "Prefiro não dizer"};

        comboBoxSexo = new JComboBox<>(opcoesSexo);
        comboBoxSexo.setBounds(320, 130, 130, 30);
        comboBoxSexo.setFont(FONT_PADRAO);
        panelCadastro.add(comboBoxSexo);

        JLabel textIdade = new JLabel("Idade: ");
        textIdade.setBounds(460, 100, 100, 30);
        textIdade.setFont(FONT_PADRAO);
        panelCadastro.add(textIdade);

        caixaIdade = new JTextField();
        caixaIdade.setBounds(460, 130, 70, 30);
        caixaIdade.setFont(FONT_PADRAO);
        caixaIdade.setText(Integer.toString(25));
        panelCadastro.add(caixaIdade);

        JLabel textOcupacao = new JLabel("Ocupação: ");
        textOcupacao.setBounds(540, 100, 100, 30);
        textOcupacao.setFont(FONT_PADRAO);
        panelCadastro.add(textOcupacao);

        caixaOcupacao = new JTextField();
        caixaOcupacao.setBounds(540, 130, 120, 30);
        caixaOcupacao.setFont(FONT_PADRAO);
        caixaOcupacao.setText("OCUPAÇÃO");
        panelCadastro.add(caixaOcupacao);

        JLabel textTempoRua = new JLabel("Tempo de rua: ");
        textTempoRua.setBounds(670, 100, 100, 30);
        textTempoRua.setFont(FONT_PADRAO);
        panelCadastro.add(textTempoRua);

        caixaTempoRua = new JTextField();
        caixaTempoRua.setBounds(670, 130, 110, 30);
        caixaTempoRua.setFont(FONT_PADRAO);
        caixaTempoRua.setText(Integer.toString(4));
        panelCadastro.add(caixaTempoRua);

        // Botão Cadastrar
        JButton buttonCadastro = new JButton("Cadastrar");
        buttonCadastro.setBounds(670, 170, 110, 40);
        buttonCadastro.setFont(FONT_PADRAO);
        buttonCadastro.addActionListener(e -> cadastrarPessoa());
        panelCadastro.add(buttonCadastro);

        // Botão Limpar Campos
        JButton buttonLimpar = new JButton("Limpar");
        buttonLimpar.setBounds(550, 170, 110, 40);
        buttonLimpar.setFont(FONT_PADRAO);
        buttonLimpar.addActionListener(e -> limparCampos());
        panelCadastro.add(buttonLimpar);

        // Tabela
        tableModel = new DefaultTableModel(new String[]{"Local", "Data", "Nome", "Sexo", "Idade", "Ocupação", "Tempo de Rua"}, 0);
        JTable table = new JTable(tableModel);
        tabelaScrollPane = new JScrollPane(table);
        tabelaScrollPane.setBounds(10, 218, 770, 308);
        tabelaScrollPane.setVisible(true);
        panelCadastro.add(tabelaScrollPane, BorderLayout.CENTER);

        atualizarTabela(); // Carrega os dados iniciais

        return panelCadastro;
    }

    private void cadastrarPessoa() {
        String local = caixaLocal.getText();
        String data = caixaData.getText();
        String nome = caixaNome.getText();
        String sexo = (String) comboBoxSexo.getSelectedItem();
        int idade = Integer.parseInt(caixaIdade.getText());
        String ocupacao = caixaOcupacao.getText();
        int tempoDeRua = Integer.parseInt(caixaTempoRua.getText());

        Pessoa pessoa = new Pessoa();
        pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);

        try (PrintWriter writer = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            writer.println(pessoa.getPessoa());

            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            atualizarTabela(); // Atualiza a tabela com os novos dados
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        caixaLocal.setText("Casa do Povo da Rua");
        caixaData.setText("18/05/2024");
        caixaNome.setText("");
        caixaIdade.setText("");
        caixaOcupacao.setText("");
        caixaTempoRua.setText("");
        comboBoxSexo.setSelectedIndex(0);
        System.out.println("Campos limpos"); // Remover depois
    }

    public ArrayList<Pessoa> listarDados() {
        ArrayList<Pessoa> lista = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");

                String local = partes[0];
                String data = partes[1];
                String nome = partes[2];
                String sexo = partes[3];
                int idade = Integer.parseInt(partes[4]);
                String ocupacao = partes[5];
                int tempoDeRua = Integer.parseInt(partes[6]);

                Pessoa pessoa = new Pessoa();
                pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);

                lista.add(pessoa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa a tabela atual
        ArrayList<Pessoa> dados = listarDados();
        for (Pessoa pessoa : dados) {
            tableModel.addRow(pessoa.toArray());
        }
    }

    public static void main(String[] args) {
        new Cadastro();
    }
}
