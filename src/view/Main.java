package view;

import java.awt.EventQueue;

public class Main {

  public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
          /**
           * Alterar para a tela que vai chamar 
           */
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
