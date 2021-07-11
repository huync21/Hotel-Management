/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class BookedRoom implements Serializable{
    private int ID;
    private Date checkIn;
    private Date checkOut;
    private float price;
    private float amount;
    private boolean isCheckedIn;
    private float sellOff;

    public float getSellOff() {
        return sellOff;
    }

    public void setSellOff(float sellOff) {
        this.sellOff = sellOff;
    }
    private String note;
    private Room room;

    public BookedRoom() {
    }

    public BookedRoom(int ID, Date checkIn, Date checkOut, float price, float amount, boolean isCheckedIn, String note, Room room) {
        this.ID = ID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.amount = amount;
        this.isCheckedIn = isCheckedIn;
        this.note = note;
        this.room = room;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isIsCheckedIn() {
        return isCheckedIn;
    }

    public void setIsCheckedIn(boolean isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "BookedRoom{" + "ID=" + ID + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", price=" + price + ", amount=" + amount + ", isCheckedIn=" + isCheckedIn + ", note=" + note + ", room=" + room + '}';
    }
    
    
}
