package test.graham.repositories;

import test.graham.entities.Employee;
import test.graham.util.ConnectionFactory;

import java.sql.*;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        // try with resources. This will create our connection and end the connection when the try block is over
        // or if something fails, it will end after the catch
        try(Connection connection = ConnectionFactory.getConnection()){
            // Here is the unfun thing about JDBC, you have to write SQL statements in Java
            // I recommend putting in comments the SQL command you are trying to execute
            //INSERT INTO books VALUES (DEFAULT, 'Great Gatsby', 'F. Scott Fitts Jerald', 0);
            String sql = "insert into employee values(default, ?, ? , ?, ?)";
            // The only thing in the sql String that isnt "just a string" are the question marks
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // We use Return generated Keys, to get back the primary key of our newly created book
            //Parameters START at 1, they are not indexed at 0
            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setInt(4, employee.getIsAdmin());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();//this returns the id that was created
            resultSet.next();//you need to move the cursor to the first valid record, or you will get a null response
            int generatedKey = resultSet.getInt("id");
            employee.setId(generatedKey);
            return employee;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
