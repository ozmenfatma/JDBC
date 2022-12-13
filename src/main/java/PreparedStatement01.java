import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       /*
       PreparedStatement interface, birden çok kez çalıştırılabilen önceden derlenmiş bir SQL kodunu temsil eder.
      Paremetrelendirilmiş SQL sorguları(query) ile çalışır. Bur sorguyu 0 veya daha fazla parametre ile kullanabiliriz.
     */

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "1234");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1. Adim  : PreparedStatement query 'sini olustur
        String sql1="update companies set number_of_employees= ?  WHERE company=?";

        //2. Adim : PreparedStatement objesi olustur
        PreparedStatement pst1=  con.prepareStatement(sql1);

        //3.Adim : setInt (), setString (),.... methodlarini kullanarak Soru isaretleri yerine deger ata.
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4.Adim : Query calistir
       int guncellenenSatirSayisi= pst1.executeUpdate();
       System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //resultset alabilmek icin executequery

        String sql2="SELECT *FROM companies";
        ResultSet resultSet01= st.executeQuery(sql2);

        while(resultSet01.next()){
            System.out.println(resultSet01.getInt(1)+" "+ resultSet01.getString(2)+" "+ resultSet01.getInt(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");


        int guncellenenSatirSayisi02= pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi02);


        ResultSet resultSet02= st.executeQuery(sql2);

        while(resultSet02.next()){
            System.out.println(resultSet02.getInt(1)+" "+ resultSet02.getString(2)+" "+ resultSet02.getInt(3));
        }
  // RESULTSET KAPALI MESELESI POINTER SONA GELDI NEXT DIYEMEYIZ

        con.close();
        resultSet01.close();
        resultSet02.close();
        pst1.close();

    }
}
