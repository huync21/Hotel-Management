/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Booking implements Serializable{
    private int ID;
    private Date bookday;
    private float totalAmount;
    private String note;
    private Client client;
    private float sellOff;
    private ArrayList<BookedRoom> bookedRooms;
    private User user;

    public Booking() {
    }

    public Booking(int ID, Date bookday, float totalAmount, String note, Client client, ArrayList<BookedRoom> bookedRooms, User user) {
        this.ID = ID;
        this.bookday = bookday;
        this.totalAmount = totalAmount;
        this.note = note;
        this.client = client;
        this.bookedRooms = bookedRooms;
        this.user = user;
    }

    public float getSellOff() {
        return sellOff;
    }

    public void setSellOff(float sellOff) {
        this.sellOff = sellOff;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getBookday() {
        return bookday;
    }

    public void setBookday(Date bookday) {
        this.bookday = bookday;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<BookedRoom> getBookedRooms() {
        if(bookedRooms == null) bookedRooms = new ArrayList<BookedRoom>();
        return bookedRooms;
    }

    public void setBookedRooms(ArrayList<BookedRoom> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    @Override
    public String toString() {
        return "Booking{" + "ID=" + ID + ", bookday=" + bookday + ", totalAmount=" + totalAmount + ", note=" + note + ", client=" + client + ", rooms=" + bookedRooms + ", user=" + user + '}';
    }
    
    
    
}
