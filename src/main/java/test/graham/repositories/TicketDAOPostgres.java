package test.graham.repositories;

import test.graham.entities.Employee;
import test.graham.entities.Ticket;
import test.graham.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOPostgres implements TicketDAO{
    @Override
    public Ticket createTicket(Ticket ticket) {
        // try with resources. This will create our connection and end the connection when the try block is over
        // or if something fails, it will end after the catch
        System.out.println(ticket);
        try(Connection connection = ConnectionFactory.getConnection()){
            // Here is the unfun thing about JDBC, you have to write SQL statements in Java
            // I recommend putting in comments the SQL command you are trying to execute
            //INSERT INTO tickets VALUES (DEFAULT, 'descriptin', 'createdBy', 'appvrovedBy', amount, status);
            String sql = "insert into tickets values(default, ?, ? , ?, ?, ?)";
            // The only thing in the sql String that isnt "just a string" are the question marks
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // We use Return generated Keys, to get back the primary key of our newly created book
            //Parameters START at 1, they are not indexed at 0
            preparedStatement.setString(1, ticket.getDescription());
            preparedStatement.setString(2, ticket.getCreatedBy());
            preparedStatement.setString(3, ticket.getApprovedBy());
            preparedStatement.setInt(4, ticket.getAmount());
            preparedStatement.setInt(5, ticket.getStatus());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();//this returns the id that was created
            resultSet.next();//you need to move the cursor to the first valid record, or you will get a null response
            int generatedKey = resultSet.getInt("id");
            ticket.setId(generatedKey);
            // check if ticket is doing something
            System.out.println(ticket);
            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        //return null;
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList();

            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setDescription(rs.getString("description"));
                ticket.setCreatedBy(rs.getString("createdBy"));
                ticket.setApprovedBy(rs.getString("approvedBy"));
                ticket.setAmount(rs.getInt("amount"));
                ticket.setStatus(rs.getInt("status"));
                ticketList.add(ticket);
            }
            return ticketList;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getTicketByEmail(String email) {
        System.out.println(email);
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets where createdBy = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);

            System.out.println(email);
            ResultSet rs = ps.executeQuery();
            //rs.next();

            List<Ticket> ticketList = new ArrayList();

            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setDescription(rs.getString("description"));
                ticket.setCreatedBy(rs.getString("createdBy"));
                ticket.setApprovedBy(rs.getString("approvedBy"));
                ticket.setAmount(rs.getInt("amount"));
                ticket.setStatus(rs.getInt("status"));
                ticketList.add(ticket);
            }
            return ticketList;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
