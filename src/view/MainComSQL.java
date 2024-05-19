package view;
import java.awt.EventQueue;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

public class MainComSQL {

    // private static final String URL = "jdbc:postgresql://localhost:5432/bdempresalaylson";
    // private static final String USER = "postgres";
    // private static final String PASSWORD = "sql1234";

  public static void main(String[] args) {

    //   Connection conn = null;
    //   PreparedStatement pstmt = null;

	  //   try {
    //       conn = DriverManager.getConnection(URL, USER, PASSWORD);

    //       String sql = "INSERT INTO produto VALUES (?, ?, ?, ?)";
    //       pstmt = conn.prepareStatement(sql);

    //       pstmt.setInt(1, 13); 
    //       pstmt.setString(2, "Pedro Vinicio");
    //       pstmt.setFloat(3, 99.99f); 
    //       pstmt.setDate(4, java.sql.Date.valueOf("2023-05-15"));

    //       int rowsAffected = pstmt.executeUpdate();
    //       System.out.println(rowsAffected + " linha(s) inserida(s).");

    //   } catch (SQLException e) {
    //       e.printStackTrace();
    //   } finally {
    //       // Fechar os recursos
    //       try {
    //           if (pstmt != null) {
    //               pstmt.close();
    //           }
    //           if (conn != null) {
    //               conn.close();
    //           }
    //       } catch (SQLException e) {
    //           e.printStackTrace();
    //       }
    //   }

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
