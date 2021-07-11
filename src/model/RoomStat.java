/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LENOVO
 */
public class RoomStat extends Room{
    private int fillDay;
    private float fillRate;
    private float revenue;

    public RoomStat() {
    }

    public RoomStat(int fillDay, float fillRate, float revenue, int ID, String name, String type, float price, String description) {
        super(ID, name, type, price, description);
        this.fillDay = fillDay;
        this.fillRate = fillRate;
        this.revenue = revenue;
    }

    public int getFillDay() {
        return fillDay;
    }

    public void setFillDay(int fillDay) {
        this.fillDay = fillDay;
    }

    public float getFillRate() {
        return fillRate;
    }

    public void setFillRate(float fillRate) {
        this.fillRate = fillRate;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    
    public void setID(int ID){
        super.setID(ID);
    }
    
    public void setName(String name){
        super.setName(name);
    }
    
    public void setType(String name){
        super.setType(name);
    }
    
    public void setDes(String des){
        super.setDescription(des);
    }
}
