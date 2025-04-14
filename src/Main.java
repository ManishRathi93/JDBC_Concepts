import java.sql.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/connect";
    private static final String useName = "root";
    private static final String password = "password";

    public static void main(String[] args) {

        //loading driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        // Establishing connection
        try{
            Connection connection = DriverManager.getConnection(url,useName,password);
            //creating statement
            Statement statement = connection.createStatement();

            //inserting data into table
//            String query = String.format("INSERT INTO students (name,age,marks) VALUES ('%s',%o,%f)", "Rahul",22,65.2);

            //updating row
//            String query = String.format("UPDATE students SET marks = %f WHERE name = '%s'",99.2,"Rahul");

            //deletion
            String query = ("DELETE FROM students WHERE id = 2");

            int rowsAffected = statement.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Data Deleted successfully");
            }
            else {
                System.out.println("Data not Deleted");
            }

            //getting data from database
            String query1 = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()){
                int roll = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double marks = resultSet.getDouble("marks");

                System.out.println(roll + ", "+name + ", "+age+", "+marks);

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}