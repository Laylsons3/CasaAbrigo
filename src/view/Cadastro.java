 package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
    public static final String caminhoArquivo = "dados.csv";
    private JTabbedPane tabbedPane;

	public Cadastro() {

		setTitle("Cadastro - Casa do Povo da Rua");
        setSize(810, 600);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

        setTitle("Cadastro - Casa do Povo da Rua");
        setSize(810, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel cadastroPanel = createCadastroPanel();
        // JPanel relatorioPanel = createRelatorioPanel();

        tabbedPane.addTab("Cadastro", cadastroPanel);
        // tabbedPane.addTab("Relatórios", relatorioPanel);

        getContentPane().add(tabbedPane);

        setVisible(true);

	}
    

	private JPanel createCadastroPanel() {

        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(null);

        Font fontPadrao = new Font("arial", Font.PLAIN, 14);

        JLabel textLocal = new JLabel("Local: ");
        textLocal.setBounds(10,10,100,30);
        textLocal.setFont(fontPadrao);
        panelCadastro.add(textLocal);
        JTextField caixaLocal = new JTextField();

        caixaLocal.setBounds(70,10,300,30);
        caixaLocal.setFont(fontPadrao);
        caixaLocal.setText("Casa do Povo da Rua");
        panelCadastro.add(caixaLocal);

        JLabel textData = new JLabel("Data: ");
        textData.setBounds(10,40,100,30);
        textData.setFont(fontPadrao);
        panelCadastro.add(textData);

        JTextField caixaData = new JTextField();
        caixaData.setBounds(70,40,300,30);
        caixaData.setFont(fontPadrao);
        panelCadastro.add(caixaData);

        JLabel textNome = new JLabel("Nome: ");
        textNome.setBounds(10,100,100,30);
        textNome.setFont(fontPadrao);
        panelCadastro.add(textNome);

        JTextField caixaNome = new JTextField();
        caixaNome.setBounds(10,130,300,30);
        caixaNome.setFont(fontPadrao);
        panelCadastro.add(caixaNome);

        JLabel textSexo = new JLabel("Sexo: ");
        textSexo.setBounds(320,100,130,30);
        textSexo.setFont(fontPadrao);
        panelCadastro.add(textSexo);
        String[] opcoesSexo = {"Masculino", "Feminino", "Prefiro não dizer"};

        JComboBox<String> comboBoxSexo = new JComboBox<>(opcoesSexo);
        comboBoxSexo.setBounds(320,130,130,30);
        comboBoxSexo.setFont(fontPadrao);
        panelCadastro.add(comboBoxSexo);

        JLabel textIdade = new JLabel("Idade: ");
        textIdade.setBounds(460,100,100,30);
        textIdade.setFont(fontPadrao);
        panelCadastro.add(textIdade);
        JTextField caixaIdade = new JTextField();
        caixaIdade.setBounds(460,130,70,30);
        caixaIdade.setFont(fontPadrao);
        panelCadastro.add(caixaIdade);

        JLabel textOcupacao = new JLabel("Ocupação: ");
        textOcupacao.setBounds(540,100,100,30);
        textOcupacao.setFont(fontPadrao);
        panelCadastro.add(textOcupacao);

        JTextField caixaOcupacao = new JTextField();
        caixaOcupacao.setBounds(540,130,120,30);
        caixaOcupacao.setFont(fontPadrao);
        panelCadastro.add(caixaOcupacao);

        JLabel textTempoRua = new JLabel("Tempo de rua: ");
        textTempoRua.setBounds(670,100,100,30);
        textTempoRua.setFont(fontPadrao);
        panelCadastro.add(textTempoRua);
        JTextField caixaTempoRua = new JTextField();
        caixaTempoRua.setBounds(670,130,110,30);
        caixaTempoRua.setFont(fontPadrao);
        panelCadastro.add(caixaTempoRua);

        JButton buttonCadastro = new JButton("Cadastrar");
        buttonCadastro.setBounds(670, 170, 110, 40);
        buttonCadastro.setFont(fontPadrao);

        //BOTÃO
        panelCadastro.add(buttonCadastro);
        
        buttonCadastro.addActionListener(e -> {

            String local = caixaLocal.getText();
            String data = caixaData.getText();
            String nome = caixaNome.getText();
            String sexo = (String) comboBoxSexo.getSelectedItem();
            int idade = Integer.parseInt(caixaIdade.getText());
            String ocupacao = caixaOcupacao.getText();
            int tempoDeRua = Integer.parseInt(caixaTempoRua.getText());

            Pessoa pessoa = new Pessoa();
            pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);

            try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo, true))) {
                writer.println(pessoa.getPessoa());

                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                caixaNome.setText("");
                caixaIdade.setText("");
                caixaOcupacao.setText("");
                caixaTempoRua.setText("");

            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + ioException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ioException.printStackTrace();
            }

            dispose();
            new Cadastro();

        });

        // AQUI
        JScrollPane scrollPane = new JScrollPane(createTable());
        scrollPane.setBounds(10, 218, 770, 308);
		scrollPane.setVisible(true);
		
        panelCadastro.add(scrollPane, BorderLayout.CENTER);
        
        return panelCadastro;
    }
	
    // PUXAR DADOS DO CSV
    public static ArrayList<Pessoa> ListarDados() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
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
            leitor.close();
            
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lista;
    }

	public JTable createTable() {
		String[] colunas = {"Local", "Data", "Nome", "Sexo", "Idade", "Ocupação", "Tempo de Rua", "Usuário"};
		
		ArrayList<Pessoa> dados = ListarDados();

		DefaultTableModel model = new DefaultTableModel(colunas, 0);

		for (Pessoa pessoa : dados) {
			model.addRow(pessoa.toArray());
		}

		JTable table = new JTable(model);
		
		table.setPreferredScrollableViewportSize(new Dimension(700, 400));
		table.setFillsViewportHeight(true);
		table.setVisible(true);
		
		return table;
	}
	    
	 
	/*
	private JPanel createRelatorioPanel() {
		JPanel panelRelatorio = new JPanel();
		panelRelatorio.setLayout(new BorderLayout());
		
		Font fontPadrao = new Font("arial", Font.BOLD, 14);
		
		JLabel labelRelatorio = new JLabel("Relatórios");
		labelRelatorio.setBounds(10, 10, 200, 30);
		labelRelatorio.setFont(fontPadrao);
		panelRelatorio.add(labelRelatorio, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(createTable());
		panelRelatorio.add(scrollPane, BorderLayout.CENTER);
		
		return panelRelatorio;
	}
	*/

    public static void main(String[] args) {
        new Cadastro();
    }
	

}