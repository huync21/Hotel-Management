/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author LENOVO
 */
public class UserDAO extends DAO{

    public UserDAO() {
    }
    
    public boolean checkLogin(User user){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tblUser WHERE userName = ? AND password = ?");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user.setPosition(rs.getString("position"));
                user.setID(rs.getInt("ID"));
                user.setFullName(rs.getString("fullName"));
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
}
