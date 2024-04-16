import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TesteCSV {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                criarGUI();
            }
        });
    }

    private static void criarGUI() {
        JFrame frame = new JFrame("Cadastro CSV");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800)); // Definindo o tamanho da janela

        JPanel panel = new JPanel(new GridLayout(9, 5, 2, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adicionando espaçamento interno

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();
        JLabel labelIdade = new JLabel("Idade:");
        JTextField campoIdade = new JTextField();
        JLabel labelProfissao = new JLabel("Profissão:");
        JTextField campoProfissao = new JTextField();
        JButton botaoSalvar = new JButton("Salvar");

        // Estilizando os componentes
        botaoSalvar.setBackground(Color.lightGray);
        botaoSalvar.setForeground(Color.WHITE);
        botaoSalvar.setFocusPainted(false); // Removendo o contorno de foco

        panel.add(labelNome);
        panel.add(campoNome);
        panel.add(labelIdade);
        panel.add(campoIdade);
        panel.add(labelProfissao);
        panel.add(campoProfissao);
        panel.add(new JLabel()); // Espaçamento
        panel.add(botaoSalvar);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centralizar na tela
        frame.setVisible(true);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String idade = campoIdade.getText();
                String profissao = campoProfissao.getText();
                salvarNoCSV(nome, idade, profissao);
            }
        });
    }

    private static void salvarNoCSV(String nome, String idade, String profissao) {
        String arquivoCSV = "dados.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivoCSV, true))) {
            writer.println(nome + "," + idade + "," + profissao);
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
