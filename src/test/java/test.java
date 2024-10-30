import br.com.fiap.fintech.factory.ConnectionFactory;
import java.sql.SQLException;
import java.sql.Connection;

public class test {
    public static void main(String[] args) {

        try {
            Connection conexao = ConnectionFactory.getConnection();
            System.out.println("Conexão realizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }
}
