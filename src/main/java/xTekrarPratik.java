import java.sql.*;

public class xTekrarPratik {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection= JdbcUtils.connectToDataBase("localhost","techproed","postgres","1234");
        Statement statement=JdbcUtils.createStatement();

        String sql1="CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC,y NUMERIC) --or replace bir kere daha cagirimca hata vermesin diye yaziyoruz\n" +
                "\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "\n" +
                "$$";
        statement.execute(sql1) ; //--> fnction calistr

         CallableStatement cst=connection.prepareCall("{? = call toplamaF(?, ?)}");
         cst.registerOutParameter(1,Types.NUMERIC);
         cst.setInt(2,6);
         cst.setInt(3,3);
         cst.execute();
        System.out.println(cst.getBigDecimal(1));




    }
}
