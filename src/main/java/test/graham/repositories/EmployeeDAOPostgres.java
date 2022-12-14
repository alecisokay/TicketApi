package test.graham.repositories;

import test.graham.entities.Employee;
import test.graham.exceptions.UserTakenException;
import test.graham.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        // try with resources. This will create our connection and end the connection when the try block is over
        // or if something fails, it will end after the catch
        try(Connection connection = ConnectionFactory.getConnection()){
            // Here is the unfun thing about JDBC, you have to write SQL statements in Java
            // I recommend putting in comments the SQL command you are trying to execute
            //INSERT INTO employee VALUES (DEFAULT, 'fname', 'lname', 'email', 'passwd', isAdmin);
            String sql = "insert into employee values(default, ?, ? , ?, ?, ?)";
            // The only thing in the sql String that isnt "just a string" are the question marks
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // We use Return generated Keys, to get back the primary key of our newly created book
            //Parameters START at 1, they are not indexed at 0
            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPasswd());
            preparedStatement.setInt(5, employee.getIsAdmin());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();//this returns the id that was created
            resultSet.next();//you need to move the cursor to the first valid record, or you will get a null response
            int generatedKey = resultSet.getInt("id");
            employee.setId(generatedKey);


            System.out.println(employee);
            return employee;
        }
        catch (SQLException e){

            e.printStackTrace();
            throw new UserTakenException("this si the usertakenex in DAOpostgres");

        }
//        return null;
    }



    @Override
    public Employee getEmployeeByEmailPassword(Employee employees) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employee where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            // The class PreparedStatement has a method called prepareStatement (no d) that takes in a string
            ps.setString(1, employees.getEmail());

            //System.out.println(email);
            //System.out.println(passwd);
            ResultSet rs = ps.executeQuery();
            rs.next();

            // if passwd == employee.getPasswd{};
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));
            employee.setEmail(rs.getString("email"));
            employee.setPasswd(rs.getString("passwd"));
            employee.setIsAdmin(rs.getInt("isAdmin"));

            System.out.println(employee);
            return employee;

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("something went wrong checking the email");
            return null;
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        try(Connection connection = ConnectionFactory.getConnection()){
            //String sql = "select * from books where id = ?";
            String sql = "select * from employee where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            // The class PreparedStatement has a method called prepareStatement (no d) that takes in a string
            ps.setString(1, email);

            System.out.println(email);
            ResultSet rs = ps.executeQuery();
            rs.next();

            // if passwd == employee.getPasswd{};
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));
            employee.setEmail(rs.getString("email"));
            employee.setPasswd(rs.getString("passwd"));
            employee.setIsAdmin(rs.getInt("isAdmin"));


            return employee;

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("something went wrong checking the email");
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        //return null;
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFname(rs.getString("fname"));
                employee.setLname(rs.getString("lname"));
                employee.setEmail(rs.getString("email"));
                employee.setIsAdmin(rs.getInt("isAdmin"));
                employeeList.add(employee);
            }
            return employeeList;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
