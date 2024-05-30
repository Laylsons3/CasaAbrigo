// package view;

// import javax.swing.*;
// import java.awt.*;
// // import java.awt.event.ActionEvent;
// // import java.awt.event.ActionListener;
// import java.io.*;
// import java.util.ArrayList;

// public class Relatorio extends JFrame {

//     private static final Font FONT_PADRAO = new Font("Arial", Font.PLAIN, 14);

//     private JComboBox<String> formatoCombo;
//     private JLabel formatExport;
//     private JButton btnSalvar;

//     public JPanel relatorioPanel() {
//         JPanel panelRelatorio = new JPanel();
//         panelRelatorio.setLayout(null); // Adicione esta linha para permitir o uso de setBounds

//         formatExport = new JLabel("Formato: ");
//         formatExport.setBounds(10, 10, 130, 25);
//         formatExport.setFont(FONT_PADRAO);
//         String[] opcoesFormatos = { "CSV"};
//         panelRelatorio.add(formatExport);

//         formatoCombo = new JComboBox<>(opcoesFormatos);
//         formatoCombo.setBounds(70, 10, 200, 25);
//         formatoCombo.setFont(FONT_PADRAO);
//         formatoCombo.setBackground(new Color(255, 255, 255));
//         panelRelatorio.add(formatoCombo);

//         btnSalvar = new JButton("Salvar");
//         btnSalvar.setBounds(280, 10, 90, 25);
//         btnSalvar.setFont(FONT_PADRAO);
//         btnSalvar.addActionListener(e -> salvar());
//         panelRelatorio.add(btnSalvar);

//         return panelRelatorio;
//     }

//     private void salvar() {
//         JFileChooser fileChooser = new JFileChooser();
//         fileChooser.setDialogTitle("Salvar como");

//         // Define o modo de seleção para arquivos
//         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

//         int userSelection = fileChooser.showSaveDialog(this);

//         if (userSelection == JFileChooser.APPROVE_OPTION) {
//             String caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
//             String formato = (String) formatoCombo.getSelectedItem();

//             if ("Excel (.xlsx)".equals(formato)) {
//                 if (!caminhoArquivo.toLowerCase().endsWith(".xlsx")) {
//                     caminhoArquivo += ".xlsx";
//                 }
//                 exportarParaExcel(caminhoArquivo);

//             } else if ("CSV".equals(formato)) {
//                 if (!caminhoArquivo.toLowerCase().endsWith(".csv")) {
//                     caminhoArquivo += ".csv";
//                 }
//                 exportarParaCSV(caminhoArquivo);
//             }
//         }
//     }

//     private void exportarParaExcel(String caminhoArquivo) {
//         // Fazer conversão para excel
//     }

//     private void exportarParaCSV(String caminhoArquivo) {
//         ArrayList<Pessoa> dados = listarDados();

//         try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
//             writer.println("Local,Data,Nome,Sexo,Idade,Ocupação,Tempo de Rua,Responsavel");

//             for (Pessoa pessoa : dados) {
//                 writer.println(pessoa.getPessoa());
//             }

//             JOptionPane.showMessageDialog(this, "Arquivo CSV exportado com sucesso!");
//         } catch (IOException e) {
//             JOptionPane.showMessageDialog(this, "Erro ao exportar arquivo CSV: " + e.getMessage());
//         }
//     }

//     public ArrayList<Pessoa> listarDados() {
//         ArrayList<Pessoa> lista = new ArrayList<>();
//         try (BufferedReader leitor = new BufferedReader(new FileReader("dados.csv"))) {
//             String linha;
//             while ((linha = leitor.readLine()) != null) {

//                     String[] partes = linha.split(",");

//                     String local = partes[0];
//                     String data = partes[1];
//                     String nome = partes[2];
//                     String sexo = partes[3];
//                     int idade = Integer.parseInt(partes[4]);
//                     String ocupacao = partes[5];
//                     int tempoDeRua = Integer.parseInt(partes[6]);
//                     Pessoa pessoa = new Pessoa();
//                     pessoa.setPessoa(local, nome, sexo, ocupacao, data, idade, tempoDeRua);
//                     lista.add(0, pessoa);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return lista;
//     }
// }
