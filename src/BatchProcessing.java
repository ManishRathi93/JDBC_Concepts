import  java.sql.*;
import java.util.Scanner;
public class BatchProcessing {
    private static final String url = "jdbc:mysql://localhost:3306/connect";
    private static final String userName = "root";
    private static final String password = "password";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            //connection
            Connection connection = DriverManager.getConnection(url, userName, password);
            Scanner sc = new Scanner(System.in);
            String query = ("INSERT INTO students (name,age,marks) VALUES(?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            while (true){
                System.out.println("Enter Name : ");
                String name = sc.next();
                System.out.println("Enter age : ");
                int age = sc.nextInt();
                System.out.println("Enter marks : ");
                double marks = sc.nextDouble();
                System.out.println("Want to enter more rows (Y/N) : ");
                String choice = sc.next();
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                preparedStatement.addBatch();
                if(choice.equalsIgnoreCase("N")){
                    break;
                }
            }
            int[] rowAffected = preparedStatement.executeBatch();
            for(int i = 0;i<rowAffected.length;i++){
                if(rowAffected[i] == 0){
                    System.out.println("Query " + i + " is not executed");
                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
