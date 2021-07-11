/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.RoomStat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author LENOVO
 */
public class RoomStatDAO extends DAO {

    public RoomStatDAO() {
    }

    public ArrayList<RoomStat> getRoomStats(Date startDate, Date endDate) {
        var listRoomStats = new ArrayList<RoomStat>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = con.prepareStatement("SELECT a.id, a.name, a.type, a.description, \n"
                    + "\n"
                    + "(SELECT SUM(DATEDIFF(day,CASE WHEN b.checkin > ? THEN b.checkin ELSE ? END,CASE WHEN b.checkout < ? THEN b.checkout ELSE ? END)) \n"
                    + "FROM tblBookedRoom b  \n"
                    + "WHERE b.roomID = a.ID AND b.checkout > ? AND b.checkin < ?  AND b.isCheckedIn = 1 \n"
                    + "GROUP BY b.roomID) as days,  \n"
                    + "\n"
                    + "(SELECT SUM(DATEDIFF(day,CASE WHEN b.checkin > ? THEN b.checkin ELSE ? END,CASE WHEN b.checkout < ? THEN b.checkout ELSE ? END)*b.price) \n"
                    + "FROM tblBookedRoom b  \n"
                    + "WHERE b.roomID = a.ID AND b.checkout > ? AND b.checkin < ?  AND b.isCheckedIn = 1 \n"
                    + "GROUP BY b.roomID) as income   \n"
                    + "\n"
                    + "FROM tblRoom a ORDER BY income DESC, days DESC");
            ps.setString(1, sdf.format(startDate));
            ps.setString(2, sdf.format(startDate));
            ps.setString(3, sdf.format(endDate));
            ps.setString(4, sdf.format(endDate));
            ps.setString(5, sdf.format(startDate));
            ps.setString(6, sdf.format(endDate));
            ps.setString(7, sdf.format(startDate));
            ps.setString(8, sdf.format(startDate));
            ps.setString(9, sdf.format(endDate));
            ps.setString(10, sdf.format(endDate));
            ps.setString(11, sdf.format(startDate));
            ps.setString(12, sdf.format(endDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RoomStat r = new RoomStat();
                r.setID(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setType(rs.getString("type"));
                r.setDes(rs.getString("description"));
                r.setFillDay(rs.getInt("days"));
                r.setRevenue(rs.getFloat("income"));
                listRoomStats.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listRoomStats;
    }
}
