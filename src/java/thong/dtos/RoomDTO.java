/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thong.dtos;

import java.io.Serializable;

/**
 *
 * @author The Thong
 */
public class RoomDTO implements Serializable{
    private String roomName, roomDetail, roomImage;
    private int roomID, roomQuantity, typeID;
    private boolean roomStatus;
    private float roomPrice;

    public RoomDTO() {
    }

    public RoomDTO(String roomName, String roomDetail, String roomImage, int roomQuantity, int typeID, boolean roomStatus, float roomPrice) {
        this.roomName = roomName;
        this.roomDetail = roomDetail;
        this.roomImage = roomImage;
        this.roomQuantity = roomQuantity;
        this.typeID = typeID;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
    }

    public RoomDTO(String roomName, String roomDetail, String roomImage, int roomQuantity, int typeID, float roomPrice) {
        this.roomName = roomName;
        this.roomDetail = roomDetail;
        this.roomImage = roomImage;
        this.roomQuantity = roomQuantity;
        this.typeID = typeID;
        this.roomPrice = roomPrice;
    }
    
    
    

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(String roomDetail) {
        this.roomDetail = roomDetail;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public boolean isRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }
    
    
}
