/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Room;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class RoomDAO extends DAO{

    public RoomDAO() {
    }
    
    public ArrayList<Room> searchRoom(String key){
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tblRoom WHERE name LIKE ?");
            ps.setString(1,"%"+key+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Room room = new Room(rs.getInt("ID"),rs.getString("name"),rs.getString("type"),rs.getFloat("price"),rs.getString("description"));
                rooms.add(room);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rooms;
    }
    
    public boolean updateRoom(Room r){
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE tblRoom SET name = ?, type=?, price=?, description=? WHERE ID = ?");
            ps.setString(1, r.getName());
            ps.setString(2, r.getType());
            ps.setFloat(3, r.getPrice());
            ps.setString(4, r.getDescription());
            ps.setInt(5, r.getID());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean insertRoom(Room r){
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tblRoom(name,type,price,description) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, r.getName());
            ps.setString(2, r.getType());
            ps.setFloat(3, r.getPrice());
            ps.setString(4, r.getDescription());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Room> searchFreeRoom(Date inputCheckInDate,Date inputCheckOutDate){
        ArrayList<Room> listRoom = new ArrayList<Room>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tblRoom WHERE ID NOT IN (SELECT DISTINCT roomID FROM tblBookedRoom WHERE checkin < ? AND checkout > ?) ");
            
            ps.setString(1, sdf.format(inputCheckOutDate));
            ps.setString(2, sdf.format(inputCheckInDate));
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Room r = new Room();
                r.setID(rs.getInt("ID"));
                r.setName(rs.getString("name"));
                r.setType(rs.getString("type"));
                r.setPrice(rs.getFloat("price"));
                r.setDescription(rs.getString("description"));
                listRoom.add(r);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listRoom;
    }
}
