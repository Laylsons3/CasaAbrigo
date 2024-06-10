package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Font FONT_PADRAO = new Font("Arial", Font.PLAIN, 14);
    
    public static String LOCAL = "Casa do Povo da Rua";

    private JTabbedPane tabbedPane;
    private JTextField  caixaData, caixaNome, caixaIdade, caixaOcupacao, caixaTempoRua, caixaPesquisa, caixaDataPesquisa;
    private JComboBox<String> comboBoxSexo, comboBoxFiltro;
    private JScrollPane tabelaScrollPane;
    private DefaultTableModel  tableModelRelatorio;
    private DefaultTableModel tableModelCadastro;

    private JComboBox<String> formatoCombo;
    private JLabel formatExport;
    private JButton btnSalvar;
    
    public String pesquisa;

    public Cadastro() {

        CriarTabela.criarNovaTabela();
        
        setTitle("Cadastro - " + LOCAL);
        setSize(810, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        JPanel pesquisaPanel = createPesquisaPanel();
        JPanel cadastroPanel = createCadastroPanel();
        JPanel exportarPanel = createExportarPanel();

        tabbedPane.addTab("Cadastro", cadastroPanel);
        tabbedPane.addTab("Pesquisa", pesquisaPanel);
        tabbedPane.addTab("Exportar", exportarPanel);

        getContentPane().add(tabbedPane);

        // tabbedPane.setSelectedIndex(1); // Lembrar de tirar

        setVisible(true);
    }

    private JPanel createPesquisaPanel() {
        JPanel panelPesquisa = new JPanel();
        panelPesquisa.setLayout(null);

        tableModelRelatorio = new DefaultTableModel(new String[]{
            "Nome",
            "Data",
            "Tempo de Rua",
            "Sexo",
        }, 0);

        JTable table = new JTable(tableModelRelatorio);
        tabelaScrollPane = new JScrollPane(table);
        tabelaScrollPane.setBounds(10, 42, 770, 488);
        tabelaScrollPane.setVisible(true);
        panelPesquisa.add(tabelaScrollPane, BorderLayout.CENTER);
        
        JLabel pesquisaLabel = new JLabel("Pesquisar:");
        pesquisaLabel.setBounds(10, 10, 76, 13);
        panelPesquisa.add(pesquisaLabel);
        
        JLabel textTipoPesquisa = new JLabel("Filtrar: ");
        textTipoPesquisa.setBounds(260, 7, 130, 21);
        panelPesquisa.add(textTipoPesquisa);
        
        caixaPesquisa = new JTextField("");
        caixaPesquisa.setBounds(85, 7, 170, 21);
        panelPesquisa.add(caixaPesquisa);

        String[] opcoesFiltro = {"Todos", "Masculino", "Feminino", "Prefiro não dizer"};
        comboBoxFiltro = new JComboBox<>(opcoesFiltro);
        comboBoxFiltro.setBounds(305, 7, 140, 21);
        comboBoxFiltro.setFont(FONT_PADRAO);
        comboBoxFiltro.setBackground(new Color(255,255,255));
        panelPesquisa.add(comboBoxFiltro);

        JLabel textDataPesquisa = new JLabel("Data: ");
        textDataPesquisa.setBounds(450, 7, 100, 21);
        panelPesquisa.add(textDataPesquisa);

        caixaDataPesquisa = new JTextField("");
        caixaDataPesquisa.setBounds(490, 7, 100, 21);
        caixaDataPesquisa.setFont(FONT_PADRAO);
        panelPesquisa.add(caixaDataPesquisa);
        caixaDataPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = caixaDataPesquisa.getText();
                text = text.replaceAll("[^0-9/]", "");
                if (text.length() == 2 && !text.contains("/")) {
                    text = text + "/";
                } else if (text.length() == 5 && text.indexOf('/', 3) == -1) {
                    text = text.substring(0, 3) + text.substring(3) + "/";
                }
                if (text.length() > 10) {
                    text = text.substring(0, 10);
                }
                caixaDataPesquisa.setText(text);
            }
        });

        JButton buttonPesquisa = new JButton("Buscar");
        buttonPesquisa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pesquisar(); }
        });
        buttonPesquisa.setBounds(600, 7, 85, 21);
        panelPesquisa.add(buttonPesquisa);
        
        JButton btnLimparPesquisa = new JButton("Limpar");
        btnLimparPesquisa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarTabelaRelatorio();
                caixaPesquisa.setText("");
                caixaDataPesquisa.setText("");
                comboBoxFiltro.setSelectedIndex(0);
            }
        });
        btnLimparPesquisa.setBounds(690, 7, 85, 21);
        panelPesquisa.add(btnLimparPesquisa);


        atualizarTabelaRelatorio();

        return panelPesquisa;
    }

    private JPanel createCadastroPanel() {
        JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(null);

        JLabel textLocal = new JLabel("Local: ");
        textLocal.setBounds(10, 10, 100, 30);
        textLocal.setFont(FONT_PADRAO);
        panelCadastro.add(textLocal);

        JLabel labelLocal = new JLabel(LOCAL);
        labelLocal.setBounds(70, 10, 300, 30);
        labelLocal.setFont(FONT_PADRAO);
        panelCadastro.add(labelLocal);
        
        JLabel textData = new JLabel("Data*: ");
        textData.setBounds(10, 40, 100, 30);
        textData.setFont(FONT_PADRAO);
        panelCadastro.add(textData);

        caixaData = new JTextField("");
        caixaData.setBounds(70, 40, 300, 30);
        caixaData.setFont(FONT_PADRAO);
        panelCadastro.add(caixaData);

        caixaData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = caixaData.getText();
                text = text.replaceAll("[^0-9/]", "");
                if (text.length() == 2 && !text.contains("/")) {
                    text = text + "/";
                } else if (text.length() == 5 && text.indexOf('/', 3) == -1) {
                    text = text.substring(0, 3) + text.substring(3) + "/";
                }
                if (text.length() > 10) {
                    text = text.substring(0, 10);
                }
                caixaData.setText(text);
            }
        });
        
        JLabel textNome = new JLabel("Nome*: ");
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

        JLabel textIdade = new JLabel("Idade*: ");
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
        caixaTempoRua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cadastrarPessoa();
                }
            }
        });
        
        JButton buttonCadastro = new JButton("Cadastrar");
        buttonCadastro.setBounds(670, 170, 110, 30);
        buttonCadastro.setFont(FONT_PADRAO);
        buttonCadastro.addActionListener(e -> cadastrarPessoa());
        panelCadastro.add(buttonCadastro);

        JButton buttonLimpar = new JButton("Limpar");
        buttonLimpar.setBounds(550, 170, 110, 30);
        buttonLimpar.setFont(FONT_PADRAO);
        buttonLimpar.addActionListener(e -> limparCampos());
        panelCadastro.add(buttonLimpar);

        tableModelCadastro = new DefaultTableModel(new String[]{
            "Nome", "Data",
        }, 0);

        JTable table = new JTable(tableModelCadastro);
        tabelaScrollPane = new JScrollPane(table);
        tabelaScrollPane.setBounds(10, 218, 770, 308);
        tabelaScrollPane.setVisible(true);
        panelCadastro.add(tabelaScrollPane, BorderLayout.CENTER);

        JButton btnLimparData = new JButton("Limpar data");
        btnLimparData.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		caixaData.setText("");
                caixaData.requestFocus();
        	}
        });
        btnLimparData.setFont(FONT_PADRAO);
        btnLimparData.setBounds(380, 40, 110, 30);
        panelCadastro.add(btnLimparData);
        atualizarTabela();
        return panelCadastro;
    }

    public JPanel createExportarPanel() {
        JPanel panelExportar = new JPanel();
        panelExportar.setLayout(null);

        formatExport = new JLabel("Formato: ");
        formatExport.setBounds(10, 10, 130, 25);
        formatExport.setFont(FONT_PADRAO);
        String[] opcoesFormatos = {"Excel (.xlsx)", "CSV"};
        panelExportar.add(formatExport);

        formatoCombo = new JComboBox<>(opcoesFormatos);
        formatoCombo.setBounds(70, 10, 200, 25);
        formatoCombo.setFont(FONT_PADRAO);
        formatoCombo.setBackground(new Color(255, 255, 255));
        panelExportar.add(formatoCombo);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(280, 10, 90, 25);
        btnSalvar.setFont(FONT_PADRAO);
        btnSalvar.addActionListener(e -> salvar());
        panelExportar.add(btnSalvar);

        return panelExportar;
    }
    
    private void cadastrarPessoa() {
        String local = LOCAL;
        String data = caixaData.getText();
        String nome = caixaNome.getText().toUpperCase();
        String sexo = (String) comboBoxSexo.getSelectedItem();
        String ocupacao = caixaOcupacao.getText();
        String idadeStr = caixaIdade.getText();
        String tempoDeRuaStr = caixaTempoRua.getText();
    
        if (nome.isEmpty() || local.isEmpty() || data.isEmpty() || idadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos com * são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int idade = Integer.parseInt(idadeStr);
            try {
                Pessoa pessoa = new Pessoa();
                pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRuaStr);
                InserirDados.inserir(local, data, nome, sexo, idade, ocupacao, tempoDeRuaStr);
                atualizarTabela();
                atualizarTabelaRelatorio();
                limparCampos();
                caixaNome.requestFocus();
            } catch (Exception e) {
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

    public ArrayList<Pessoa> listarDados() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        
        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String local = rs.getString("local");
                String data = rs.getString("data");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                int idade = rs.getInt("idade");
                String ocupacao = rs.getString("ocupacao");
                String tempoDeRua = rs.getString("tempoderua");
    
                Pessoa pessoa = new Pessoa();
                pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);
                lista.add(0, pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void pesquisar() {
        pesquisa = caixaPesquisa.getText().toUpperCase();
        atualizarTabelaFiltrada();
    }

    private void atualizarTabelaFiltrada() {
        tableModelRelatorio.setRowCount(0);
        ArrayList<Pessoa> dados = listarDadosFiltrados();
        for (int i = 0; i < dados.size(); i++) {
            Pessoa pessoa = dados.get(i);
            tableModelRelatorio.addRow(pessoa.toArray());
        }
    }

    public ArrayList<Pessoa> listarDadosFiltrados() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = null;
        if (caixaDataPesquisa.getText().length() == 0) {
            if (comboBoxFiltro.getSelectedItem().equals("Todos")) {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? ORDER BY nome ASC";
            } else {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? AND sexo = '"+ comboBoxFiltro.getSelectedItem() + "' ORDER BY nome ASC";
            }
        } else if (caixaDataPesquisa.getText().length() < 10) {
            JOptionPane.showMessageDialog(this, "Digite a data corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            if (comboBoxFiltro.getSelectedItem().equals("Todos")) {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? AND data = '" + caixaDataPesquisa.getText() +  "' ORDER BY nome ASC";
            } else {
                sql = "SELECT * FROM pessoas WHERE UPPER(nome) LIKE ? AND sexo = '"+ comboBoxFiltro.getSelectedItem() + "' AND data = '" + caixaDataPesquisa.getText() +"' ORDER BY nome ASC";
            }
        }
    
        try (Connection conn = ConexaoBanco.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + pesquisa.toUpperCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String local = rs.getString("local");
                String data = rs.getString("data");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                int idade = rs.getInt("idade");
                String ocupacao = rs.getString("ocupacao");
                String tempoDeRua = rs.getString("tempoderua");
    
                Pessoa pessoa = new Pessoa();
                pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Pessoa> listarDadosRelatorio() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT local, data, nome, sexo, idade, ocupacao, tempoDeRua FROM pessoas";
    
        try (Connection conn = ConexaoBanco.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String local = rs.getString("local");
                    String data = rs.getString("data");
                    String nome = rs.getString("nome");
                    String sexo = rs.getString("sexo");
                    int idade = rs.getInt("idade");
                    String ocupacao = rs.getString("ocupacao");
                    String tempoDeRua = rs.getString("tempoDeRua");
                    Pessoa pessoa = new Pessoa();
                    pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);
                    lista.add(0, pessoa);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    

    private void atualizarTabela() {
        tableModelCadastro.setRowCount(0);
        ArrayList<Pessoa> dados = listarDados();
        for (int i = 0; i < dados.size() && i < 17; i++) {
            Pessoa pessoa = dados.get(i);
            tableModelCadastro.addRow(pessoa.toArray());
        }
    }
    

    private void atualizarTabelaRelatorio() {
        tableModelRelatorio.setRowCount(0);
        ArrayList<Pessoa> dados = listarDadosRelatorio();
        for (Pessoa pessoa : dados) {
            tableModelRelatorio.addRow(pessoa.toArray());
        }
    }

    private void salvar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar como");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
            String formato = (String) formatoCombo.getSelectedItem();

            if ("Excel (.xlsx)".equals(formato)) {
                if (!caminhoArquivo.toLowerCase().endsWith(".xlsx")) {
                    caminhoArquivo += ".xlsx";
                }
                exportarParaExcel(caminhoArquivo);

            } else if ("CSV".equals(formato)) {
                if (!caminhoArquivo.toLowerCase().endsWith(".csv")) {
                    caminhoArquivo += ".csv";
                }
                exportarParaCSV(caminhoArquivo);
            }
        }
    }

 private void exportarParaExcel(String caminhoArquivo) {
        ArrayList<Pessoa> dados = listarDados();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Relatório");

            Row headerRow = sheet.createRow(0);
            String[] colunas = {"Local", "Data", "Nome", "Sexo", "Idade", "Ocupação", "Tempo de Rua"};
            for (int i = 0; i < colunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(colunas[i]);
            }

            int rowNum = 1;
            for (Pessoa pessoa : dados) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(pessoa.getLocal());
                row.createCell(1).setCellValue(pessoa.getData());
                row.createCell(2).setCellValue(pessoa.getNome());
                row.createCell(3).setCellValue(pessoa.getSexo());
                row.createCell(4).setCellValue(pessoa.getIdade());
                row.createCell(5).setCellValue(pessoa.getOcupacao());
                row.createCell(6).setCellValue(pessoa.getTempoDeRua());
            }

            try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Arquivo Excel exportado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar arquivo Excel: " + e.getMessage());
        }
    }

    private void exportarParaCSV(String caminhoArquivo) {
        ArrayList<Pessoa> dados = listarDados(); // Lê os dados do banco de dados
    
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            writer.println("Local,Data,Nome,Sexo,Idade,Ocupação,Tempo de Rua");
    
            for (Pessoa pessoa : dados) {
                writer.println(pessoa.getPessoa());
            }
    
            JOptionPane.showMessageDialog(this, "Arquivo CSV exportado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar arquivo CSV: " + e.getMessage());
        }
    }
    
    
    public class ConexaoBanco {
        private static final String URL = "jdbc:sqlite:db-abrigo.db";

        public static Connection conectar() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }

    }

    public class CriarTabela {
        public static void criarNovaTabela() {
            String sql = "CREATE TABLE IF NOT EXISTS pessoas ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "local TEXT NOT NULL,"
                        + "data TEXT NOT NULL,"
                        + "nome TEXT NOT NULL,"
                        + "sexo TEXT NOT NULL,"
                        + "idade INTEGER NOT NULL,"
                        + "ocupacao TEXT,"
                        + "tempoDeRua TEXT NOT NULL"
                        + ");";
    
            try (Connection conn = ConexaoBanco.conectar();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public class InserirDados {
        public static void inserir(String local, String data, String nome, String sexo, int idade, String ocupacao, String tempoderua) {
            String sql = "INSERT INTO pessoas (local, data, nome, sexo, idade, ocupacao, tempoderua) VALUES(?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = ConexaoBanco.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, local);
                pstmt.setString(2, data);
                pstmt.setString(3, nome);
                pstmt.setString(4, sexo);
                pstmt.setInt(5, idade);
                pstmt.setString(6, ocupacao);
                pstmt.setString(7, tempoderua);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public class ConsultaCompleta {
        public static void consulta() {
            String sql = "SELECT * FROM pessoas";
    
            try (Connection conn = ConexaoBanco.conectar();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
    
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("local") + "\t" +
                        rs.getString("data") + "\t" +
                        rs.getString("nome") + "\t" +
                        rs.getString("sexo") + "\t" +
                        rs.getInt("idade") + "\t" +
                        rs.getString("ocupacao") + "\t" +
                        rs.getInt("tempoderua")
                    );
                }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    


}