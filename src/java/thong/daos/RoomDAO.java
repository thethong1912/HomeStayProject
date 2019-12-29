/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thong.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import thong.connections.MyConnection;
import thong.dtos.RoomDTO;

/**
 *
 * @author The Thong
 */
public class RoomDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RoomDAO() {
    }

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean serviceInsert(RoomDTO dto) throws Exception {
        boolean insert = true;
        try {
            String sql = "Insert into tbl_Room(RoomtName, RoomDetail, RoomQuantity, RoomPrice, RoomImage, RoomStatus ,TypeID) "
                    + "Values(?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getRoomName());
            preStm.setString(2, dto.getRoomDetail());
            preStm.setInt(3, dto.getRoomQuantity());
            preStm.setFloat(4, dto.getRoomPrice());
            preStm.setString(5, dto.getRoomImage());
            if (dto.getRoomQuantity() > 0) {
                preStm.setBoolean(6, true);
            } else {
                preStm.setBoolean(6, false);
            }

            preStm.setInt(7, dto.getTypeID());

            insert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return insert;
    }

    public List<RoomDTO> findByLikeName(String search) throws Exception {
        List<RoomDTO> result = null;
        RoomDTO dto = null;
        String roomName, roomDetail, roomImage;
        int roomQuantity, typeID;
        float roomPrice;
        try {
            String sql = "Select RoomtName, RoomDetail, RoomQuantity, RoomPrice, RoomImage, TypeID From tbl_Room "
                    + "Where RoomtName LIKE ? and RoomStatus = 1 ";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                roomName = rs.getString("RoomtName");
                roomDetail = rs.getString("RoomDetail");
                roomImage = rs.getString("RoomImage");
                typeID = rs.getInt("TypeID");
                roomQuantity = rs.getInt("RoomQuantity");
                roomPrice = rs.getFloat("RoomPrice");

                dto = new RoomDTO(roomName, roomDetail, roomImage, roomQuantity, typeID, roomPrice);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tbl_Room SET RoomStatus = ? Where RoomtName = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "0");
            preStm.setString(2, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateService(RoomDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tbl_Room SET RoomDetail = ?, RoomPrice = ?, RoomQuantity = ?, RoomImage = ?, TypeID = ?"
                    + " Where RoomtName = ? ";

            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setString(1, dto.getRoomDetail());
            preStm.setFloat(2, dto.getRoomPrice());
            preStm.setInt(3, dto.getRoomQuantity());
            preStm.setString(4, dto.getRoomImage());
            preStm.setInt(5, dto.getTypeID());
            preStm.setString(6, dto.getRoomName());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public RoomDTO findByPrimaryKey(String id) throws Exception {
        RoomDTO dto = null;
        String roomDetail, roomImage;
        int roomQuantity, TypeID;
        float roomPrice = 0;
        try {
            String sql = "Select RoomDetail, RoomPrice, RoomQuantity, RoomImage, TypeID "
                    + "From tbl_Room Where RoomtName = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                roomDetail = rs.getString("RoomDetail");
                roomImage = rs.getString("RoomImage");
                TypeID = rs.getInt("TypeID");
                roomQuantity = rs.getInt("RoomQuantity");
                roomPrice = rs.getFloat("RoomPrice");
                dto = new RoomDTO(id, roomDetail, roomImage, roomQuantity, TypeID, roomPrice);
                dto.setTypeID(TypeID);

            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
