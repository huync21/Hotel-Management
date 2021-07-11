/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Statement;
import java.sql.SQLException;
import model.Booking;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.BookedRoom;
import model.Client;

/**
 *
 * @author LENOVO
 */
public class BookingDAO extends DAO {

    public boolean addBooking(Booking b) {
        boolean result = false;
        String addBookingSql = "INSERT INTO tblBooking(userID,clientID,bookingDate) VALUES(?,?,?) ";
        String addBookedRoomSql = "INSERT INTO tblBookedRoom(bookingID,roomID,checkin,checkout,price,isCheckedIn) VAlUES(?,?,?,?,?,?)";
        String checkBookedRoomSql = "SELECT * FROM tblBookedRoom WHERE roomID = ? AND checkin < ? AND checkout > ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(addBookingSql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, b.getUser().getID());
            ps.setInt(2, b.getClient().getID());
            ps.setString(3, sdf.format(b.getBookday()));

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                b.setID(generatedKeys.getInt(1));
                System.out.println(generatedKeys.getInt(1));
                for (BookedRoom br : b.getBookedRooms()) {
                    //check xem la trong thoi gian do da co phong do chua
                    ps = con.prepareStatement(checkBookedRoomSql);
                    ps.setInt(1, br.getRoom().getID());
                    ps.setString(2, sdf.format(br.getCheckOut()));
                    ps.setString(3, sdf.format(br.getCheckIn()));

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {// da co phong day trong thoi gian day

                        try {
                            con.rollback();
                            con.setAutoCommit(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return result;
                    }

                    //them booked room
                    ps = con.prepareStatement(addBookedRoomSql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, b.getID());
                    ps.setInt(2, br.getRoom().getID());
                    ps.setString(3, sdf.format(br.getCheckIn()));
                    ps.setString(4, sdf.format(br.getCheckOut()));
                    ps.setFloat(5, br.getPrice());
                    ps.setInt(6, 0);
                    ps.executeUpdate();
                }
                con.commit();
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<Booking> getBookingOfRoom(int idroom, Date startDate, Date endDate) {
        ArrayList<Booking> result = new ArrayList<Booking>();
        String sql = "SELECT a.ID as idbookedroom, \n"
                + "(CASE WHEN a.checkin > ? THEN a.checkin ELSE ? END) as checkin, (CASE WHEN a.checkout < ? THEN a.checkout ELSE ? END) as checkout, \n"
                + "a.price, a.sellOff as roomsaleoff, b.id as idbooking, b.sellOff as bookingsaleoff,  c.ID as idclient, c.fullName, c.address, c.idCard, c.tel  \n"
                + "FROM tblBookedRoom a, tblBooking b, tblClient c \n"
                + "WHERE a.roomID = ? AND a.isCheckedIn = 1  AND a.checkout > ? AND a.checkin < ? AND b.ID = a.bookingID AND c.ID = b.clientID";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(startDate));
            ps.setString(2, sdf.format(startDate));
            ps.setString(3, sdf.format(endDate));
            ps.setString(4, sdf.format(endDate));
            ps.setInt(5, idroom);
            ps.setString(6, sdf.format(startDate));
            ps.setString(7, sdf.format(endDate));
            ResultSet rs = ps.executeQuery();

            //a == null ? b : (b == null ? a : (a.before(b) ? a : b));
            while (rs.next()) {
                Booking b = new Booking();
                b.setID(rs.getInt("idbooking"));
                b.setSellOff(rs.getFloat("bookingsaleoff"));
                //client
                Client c = new Client();
                c.setID(rs.getInt("idclient"));
                c.setFullName(rs.getString("fullName"));
                c.setAddress(rs.getString("address"));
                c.setIdCard(rs.getString("idCard"));
                b.setClient(c);
                //booked room
                BookedRoom br = new BookedRoom();
                br.setID(rs.getInt("idbookedroom"));
                br.setSellOff(rs.getFloat("roomsaleoff"));
                br.setPrice(rs.getFloat("price"));
                br.setCheckIn(rs.getDate("checkin"));
                br.setCheckOut(rs.getDate("checkout"));
                b.getBookedRooms().add(br);
                result.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
