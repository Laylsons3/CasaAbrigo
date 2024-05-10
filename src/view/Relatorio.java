package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.Pessoa;

public class Relatorio {
    public static final String caminhoArquivo = "dados.csv";
    
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

        return table;
    }
    
    public JPanel createRelatorioPanel() {
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
    
}
