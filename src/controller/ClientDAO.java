/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Client;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author LENOVO
 */
public class ClientDAO extends DAO{
    public ArrayList<Client> searchClients(String key){
        var listClients = new ArrayList<Client>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tblClient WHERE fullName LIKE ?");
            ps.setString(1, "%"+key+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Client client = new Client();
                client.setID(rs.getInt("ID"));
                client.setFullName(rs.getString("fullName"));
                client.setIdCard(rs.getString("idCard"));
                client.setAddress(rs.getString("address"));
                client.setTel(rs.getString("tel"));
                client.setEmail(rs.getString("email"));
                client.setNote(rs.getString("note"));
                listClients.add(client);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return  listClients;
    }
    
    public boolean addClient(Client client){
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tblClient(fullName,idCard,address,tel,email,note) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getFullName());
            ps.setString(2, client.getIdCard());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getTel());
            ps.setString(5, client.getEmail());
            ps.setString(6, client.getNote());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                client.setID(rs.getInt(1));
            }
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return result;
    }
    
}
