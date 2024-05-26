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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Font FONT_PADRAO = new Font("Arial", Font.PLAIN, 14);
    public static final String CAMINHO_ARQUIVO = "dados.csv";

    private JTabbedPane tabbedPane;
    private JTextField caixaLocal, caixaData, caixaNome, caixaIdade, caixaOcupacao, caixaTempoRua;
    private JTextField caixaPesquisa;
    private JComboBox<String> comboBoxSexo;
    private JScrollPane tabelaScrollPane;
    private DefaultTableModel tableModelCadastro, tableModelRelatorio;

    public String pesquisa;

    public Cadastro() {
        setTitle("Cadastro - Casa do Povo da Rua");
        setSize(810, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        JPanel cadastroPanel = createCadastroPanel();
        JPanel relatorioPanel = createRelatorioPanel();
        tabbedPane.addTab("Cadastro", cadastroPanel);
        tabbedPane.addTab("Relatorio", relatorioPanel);
        getContentPane().add(tabbedPane);

        setVisible(true);
    }

    private JPanel createRelatorioPanel() {
        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(null);

        // Tabela
        tableModelRelatorio = new DefaultTableModel(new String[]{
            "Data",
            "Quantidade",
            "Local",
            "Responsável",
        }, 0);
        JTable table = new JTable(tableModelRelatorio);
        tabelaScrollPane = new JScrollPane(table);
        tabelaScrollPane.setBounds(10, 59, 770, 467);
        tabelaScrollPane.setVisible(true);
        panelRelatorio.add(tabelaScrollPane, BorderLayout.CENTER);
        
        JLabel pesquisaLabel = new JLabel("Pesquisar:");
        pesquisaLabel.setBounds(10, 10, 76, 13);
        panelRelatorio.add(pesquisaLabel);
        
        caixaPesquisa = new JTextField();
        caixaPesquisa.setBounds(85, 7, 170, 19);
        panelRelatorio.add(caixaPesquisa);
        
        JButton buttonPesquisa = new JButton("Buscar");
        buttonPesquisa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pesquisar();
        	}
        });
        buttonPesquisa.setBounds(269, 6, 85, 21);
        panelRelatorio.add(buttonPesquisa);
        
        JButton btnLimparPesquisa = new JButton("Limpar");
        btnLimparPesquisa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		atualizarTabelaRelatorio();
                caixaPesquisa.setText("");
        	}
        });
        btnLimparPesquisa.setBounds(368, 6, 85, 21);
        panelRelatorio.add(btnLimparPesquisa);

        atualizarTabelaRelatorio(); // Carrega os dados iniciais

        return panelRelatorio;
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
        caixaData.setForeground(new Color(128, 128, 128));
        caixaData.setText("dd/mm/aaaa");
        caixaData.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		caixaData.setText("");
                caixaData.setForeground(new Color(0, 0, 0));
        	}
        });
        caixaData.setBounds(70, 40, 300, 30);
        caixaData.setFont(FONT_PADRAO);
        panelCadastro.add(caixaData);
        
        JLabel textNome = new JLabel("Nome: ");
        textNome.setBounds(10, 100, 100, 30);
        textNome.setFont(FONT_PADRAO);
        panelCadastro.add(textNome);

        caixaNome = new JTextField();
        caixaNome.setBounds(10, 130, 300, 30);
        caixaNome.setFont(FONT_PADRAO);
        panelCadastro.add(caixaNome);
        
        JLabel textSexo = new JLabel("Sexo: ");
        textSexo.setBounds(320, 100, 130, 30);
        textSexo.setFont(FONT_PADRAO);
        String[] opcoesSexo = {"Masculino", "Feminino", "Prefiro não dizer"};
        panelCadastro.add(textSexo);
        
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
        panelCadastro.add(caixaIdade);
        
        JLabel textOcupacao = new JLabel("Ocupação: ");
        textOcupacao.setBounds(540, 100, 100, 30);
        textOcupacao.setFont(FONT_PADRAO);
        panelCadastro.add(textOcupacao);
        
        caixaOcupacao = new JTextField();
        caixaOcupacao.setBounds(540, 130, 120, 30);
        caixaOcupacao.setFont(FONT_PADRAO);
        panelCadastro.add(caixaOcupacao);
        
        JLabel textTempoRua = new JLabel("Tempo de rua: ");
        textTempoRua.setBounds(670, 100, 100, 30);
        textTempoRua.setFont(FONT_PADRAO);
        panelCadastro.add(textTempoRua);

        caixaTempoRua = new JTextField();
        caixaTempoRua.setBounds(670, 130, 110, 30);
        caixaTempoRua.setFont(FONT_PADRAO);
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
        tableModelCadastro = new DefaultTableModel(new String[]{
            "Nome",
            "Data",
        }, 0);
        JTable table = new JTable(tableModelCadastro);
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
        String nome = caixaNome.getText().toUpperCase();
        String sexo = (String) comboBoxSexo.getSelectedItem();
        String ocupacao = caixaOcupacao.getText();
        String idadeStr = caixaIdade.getText();
        String tempoDeRuaStr = caixaTempoRua.getText();
        
        // Verifica se os campos estão vazios
        if ( nome.isEmpty() || ocupacao.isEmpty() || local.isEmpty() || local.isEmpty() || data.isEmpty() || idadeStr.isEmpty() || tempoDeRuaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            
            int idade = Integer.parseInt(caixaIdade.getText());
            int tempoDeRua = Integer.parseInt(caixaTempoRua.getText());
            
            // Escreve no csv
            try (PrintWriter writer = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
                Pessoa pessoa = new Pessoa();
            
                // Converte os dados para o formato do objeto
                pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);
            
                // Escreve no arquivo de texto em seguida faz uma quebra de linha
                writer.println(pessoa.getPessoa());
                // Faz com que os dados armazenados no buffer sejam enviados imediatamente ao destino
                writer.flush();
            
                atualizarTabela(); // Atualiza a tabela com os novos dados
                atualizarTabelaRelatorio();

                limparCampos(); // Limpa os campos após cadastro
                
                } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }
    }
    
    private void limparCampos() {
        caixaNome.setText("");
        caixaIdade.setText("");
        caixaOcupacao.setText("");
        caixaTempoRua.setText("");
    }

    // Ler no csv
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

    public void pesquisar() {
        pesquisa = caixaPesquisa.getText().toUpperCase();
        atualizarTabelaFiltrada();
    }

    private void atualizarTabelaFiltrada() {
        tableModelRelatorio.setRowCount(0); // Limpa a tabela atual
        ArrayList<Pessoa> dados = listarDadosFiltrados();
        for (Pessoa pessoa : dados) {
            tableModelRelatorio.addRow(pessoa.toArrayCompleto());
        }
    }

    public ArrayList<Pessoa> listarDadosFiltrados() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[2].toUpperCase();
                if (nome.contains(pesquisa)) {
                    // System.out.println("Pesquisa confere");
                
                    String local = partes[0];
                    String data = partes[1];
                    String sexo = partes[3];
                    int idade = Integer.parseInt(partes[4]);
                    String ocupacao = partes[5];
                    int tempoDeRua = Integer.parseInt(partes[6]);
        
                    Pessoa pessoa = new Pessoa();
    
                    pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);
                    lista.add(pessoa);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Pessoa> listarDadosRelatorio() {
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
        tableModelCadastro.setRowCount(0); // Limpa a tabela atual antes de atualizar
        ArrayList<Pessoa> dados = listarDados();
        for (Pessoa pessoa : dados) {
            tableModelCadastro.addRow(pessoa.toArray());
        }
    }

    private void atualizarTabelaRelatorio() {
        tableModelRelatorio.setRowCount(0); // Limpa a tabela atual antes de atualizar
        ArrayList<Pessoa> dados = listarDadosRelatorio();
        for (Pessoa pessoa : dados) {
            tableModelRelatorio.addRow(pessoa.toArrayCompleto());
        }
    }

    public static void main(String[] args) {
        new Cadastro();
    }
}