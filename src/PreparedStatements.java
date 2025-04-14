import java.sql.*;

public class PreparedStatements {
    private static final String url = "jdbc:mysql://localhost:3306/connect";
    private static final String userName = "root";
    private static final String password = "password";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        //connection
        try {
            Connection connection = DriverManager.getConnection(url,userName,password);

//            String insert = "INSERT INTO students(name,age,marks) VALUES (?,?,?)";
//            String update = "UPDATE students SET name = ?  WHERE id = ? ";
//            String delete = "DELETE FROM students WHERE id = ?";
            String get = "SELECT * FROM students";
            PreparedStatement preparedStatement = connection.prepareStatement(get);
//            preparedStatement.setString(1,"Abhay");
//            preparedStatement.setInt(1,3);
//            preparedStatement.setDouble(3,98.3);
//            int rowAffected = preparedStatement.executeUpdate();
//            if(rowAffected > 0){
//                System.out.println("Data inserted successfully");
//            }
//            else{
//                System.out.println("Data not inserted");
//            }
            ResultSet resultSet = preparedStatement.executeQuery(get);
            while(resultSet.next()){
                System.out.println("name : "+resultSet.getString("name"));
                System.out.println("age : "+resultSet.getInt("age"));
                System.out.println("marks : "+resultSet.getDouble("marks"));
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
