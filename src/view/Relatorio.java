package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Relatorio extends JFrame {

    public static final String CAMINHO_ARQUIVO = "dados.csv";

    private JScrollPane tabelaScrollPane;
    private DefaultTableModel tableModel;

    public JPanel createRelatorioPanel() {
        JPanel panelRelatorio = new JPanel();
        panelRelatorio.setLayout(null);
        
        // Tabela
        tableModel = new DefaultTableModel(new String[]{
            "Data",
            "Nome",
        }, 0);
        JTable table = new JTable(tableModel);
        tabelaScrollPane = new JScrollPane(table);
        tabelaScrollPane.setBounds(10, 63, 770, 463);
        tabelaScrollPane.setVisible(true);

        atualizarTabelaRelatorio(); // Carrega os dados iniciais

        return panelRelatorio;
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

    private void atualizarTabelaRelatorio() {
        tableModel.setRowCount(0); // Limpa a tabela atual
        ArrayList<Pessoa> dados = listarDados();
        for (Pessoa pessoa : dados) {
            tableModel.addRow(pessoa.toArray());
        }
    }
}
