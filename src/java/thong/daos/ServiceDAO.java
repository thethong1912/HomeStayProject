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
import thong.dtos.AccountDTO;
import thong.dtos.ServiceDTO;

/**
 *
 * @author The Thong
 */
public class ServiceDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ServiceDAO() {
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

   

    public boolean serviceInsert(ServiceDTO dto) throws Exception {
        boolean insert = true;
        try {
            String sql = "Insert into ServiceHomeStay(SerName, SerPrice, SerDescription,SerQuantity, SerStatus, SerImage, TypeRoomService) "
                    + "Values(?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getSerName());
            preStm.setFloat(2, dto.getSerPrice());
            preStm.setString(3, dto.getServDescription());
            preStm.setInt(4, dto.getSerQuantity());
            if(dto.getSerQuantity() > 0){
                 preStm.setBoolean(5, true);
            } else {
                preStm.setBoolean(5, false);
            }
            preStm.setString(6, dto.getSerImage());
            preStm.setInt(7, dto.getTypeRomeService());

            insert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return insert;
    }

    public List<ServiceDTO> findByLikeName(String search) throws Exception {
        List<ServiceDTO> result = null;
        ServiceDTO dto = null;
        String serName, serDescription, serImage;
        int serQuantity, TypeRoomService;
        float serPrice;
        try {
            String sql = "Select SerName, SerDescription, SerPrice, SerQuantity, SerImage, TypeRoomService From ServiceHomeStay "
                    + "Where SerName LIKE ? and SerStatus = 1";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                serName = rs.getString("SerName");
                serDescription = rs.getString("SerDescription");
                serImage = rs.getString("SerImage");
                TypeRoomService = rs.getInt("TypeRoomService");
                serQuantity = rs.getInt("SerQuantity");
                serPrice = rs.getFloat("SerPrice");

                dto = new ServiceDTO(serName, serDescription, serImage, serPrice, serQuantity, TypeRoomService);
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
            String sql = "Update ServiceHomeStay SET SerStatus = ? Where SerName = ?";
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

    public boolean updateService(ServiceDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update ServiceHomeStay SET SerDescription = ?, SerPrice = ?, SerQuantity = ?, SerImage = ?, TypeRoomService = ?"
                    + " Where SerName = ? ";

            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            
            preStm.setString(1, dto.getServDescription());
            preStm.setFloat(2, dto.getSerPrice());
            preStm.setInt(3, dto.getSerQuantity());
            preStm.setString(4, dto.getSerImage());     
            preStm.setInt(5, dto.getTypeRomeService());
            preStm.setString(6, dto.getSerName());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public ServiceDTO findByPrimaryKey(String id) throws Exception {
        ServiceDTO dto = null;
        String serDescription, serImage;
        int serQuantity, TypeRoomService;
        float serPrice = 0;
        try {
            String sql = "Select SerDescription, SerPrice, SerQuantity, SerImage, TypeRoomService "
                    + "From ServiceHomeStay Where SerName = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                 serDescription = rs.getString("SerDescription");
                serImage = rs.getString("SerImage");
                TypeRoomService = rs.getInt("TypeRoomService");
                serQuantity = rs.getInt("SerQuantity");
                serPrice = rs.getFloat("SerPrice");
                dto = new ServiceDTO(id, serDescription, serImage, serPrice, serQuantity, TypeRoomService);
                dto.setTypeRomeService(TypeRoomService);

            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
