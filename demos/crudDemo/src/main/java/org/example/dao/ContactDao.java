package org.example.dao;

import org.example.connexion.ConnectionUtil;
import org.example.model.Contact;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    public int addContact(Contact contact) {
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement("INSERT INTO `contact` (`name`, `number`) VALUES (?, ?)");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    public int update(Contact contact) {
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement("UPDATE `contact` SET `name`=?, `number`=? WHERE id=?");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            ps.setInt(3, contact.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    public int delete(int id) {
        connection = ConnectionUtil.getConnection();
        try {
            ps=connection.prepareStatement("DELETE FROM `contact` WHERE id=?");
            ps.setInt(1, id);
            int n=ps.executeUpdate();
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources();
        }
    }


    public Contact get(int id) {
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `contact` WHERE id = ?");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Contact(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("number"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    public void getAll(DefaultTableModel tableModel) {
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement("SELECT * FROM `contact`");
            resultSet = ps.executeQuery();

            // Add column names to the table model
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Number");

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("number")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }




    public Contact searchContact(int id){
        try {
            connection = ConnectionUtil.getConnection();
            ps=connection.prepareStatement("SELECT * FROM `contact` WHERE `id`=?");
            ps.setInt(1, id);

            ResultSet resultSet=ps.executeQuery();
            Contact contact=null;
            if(resultSet.next()){
                contact=new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setNumber(resultSet.getString("number"));
            }
            connection.close();
            return contact;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
