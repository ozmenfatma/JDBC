import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        Java'da methodlar return type sahibi olsa da olmasa da method olarak adlandirilir
        SQL de data return ediyorsa "function" denir. return yapmiyorssa "procedure " olarak adlandirilir
        */



        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "1234");
        Statement st = con.createStatement();

        //CallableStatement ile function cagirmayi parametrelendirecegiz

        //1.Adim : Function kodunu yaz
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

        //2.Adim : Function calistir
        st.execute(sql1);  //executeQuery sorguyu calistirmak icin burda sorgu yapmiyoruz.

        //3.adim : Function cagir

        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");  //İlk parametre retun type

        //4.Adim : Return type icin registerOurParameter() methodunu , parametreler icin ise set().. method uygula
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        //5. Adim : execute() methodu ile CallableStatement 'i calistir.
        cst1.execute();

        //6.Adim : Sonucu cagirmak icin return data type tipine gore

        System.out.println(  cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        String sql2 ="CREATE OR REPLACE FUNCTION  konininHacmiF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        st.execute(sql2);

        CallableStatement cst2 = con.prepareCall("{? = call konininHacmiF(?, ?)}");
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);
        cst2.execute();
        System.out.printf("%.2f",  cst2.getBigDecimal(1));

        //System.out.println("koninin hacmi: " + String.format("%.2f",cst2.getBigDecimal(1)));
        // eğer printfsiz yapmak isterseniz de böyle

        con.close();

    }
}
