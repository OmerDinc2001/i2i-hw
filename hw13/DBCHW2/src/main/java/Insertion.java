import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.jdbc.*;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class Insertion {
    public static void main(String[] args)
    {

        Connection con=null;

        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Oracle JDBC driver loaded ok.");

            String url = "jdbc:oracle:thin:@//localhost:1521/XE";
            String user = "";
            String password = "";
            con=DriverManager.getConnection(url, user, password);
            System.out.println("Connect with @//localhost:1521/XE");

            Statement stmt = con.createStatement();

            /*String testQuery = "SELECT * FROM BOOK";

            ResultSet rows = stmt.executeQuery(testQuery);

            int count=0;
            while (rows.next()) {
                count+=1;
                String productNumber = rows.getString("name");
                String productName = rows.getString("isbn");
                String productPrice = rows.getString("create_date");
                System.out.println("Row #:"+count);
                System.out.println("name#: "+productNumber);
                System.out.println("isbn: "+productName);
                System.out.println("date: "+productPrice);
            }*/

            RandomStringGenerator generatorStr = new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS) // Filters to only include letters
                    .get();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            for(int i=0; i<100; i++) {
                Date date = new Date();
                String addRows = "INSERT INTO BOOK " +
                        "VALUES (" +
                        (i + 1) + ", '" +
                        generatorStr.generate(1,128) + "', '" +
                        generatorStr.generate(1, 32) + "', TO_DATE('" +
                        dateFormat.format(date) + "', 'YYYY/MM/DD HH:MI'))";
                stmt.executeUpdate(addRows);
            }

            con.close();

        }
        catch (Exception e)
        {
            System.err.println("Exception:"+e.getMessage());
        }


    }

}
