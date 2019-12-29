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

/**
 *
 * @author The Thong
 */
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccountDAO() {
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

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";

        try {
            String sql = "Select UserRole From tbl_Account  Where Username = ? AND UserPassword = ? AND isDelete = 0";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("UserRole");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public boolean userInsert(AccountDTO dto) throws Exception {
        boolean insert = true;
        try {
            String sql = "Insert into tbl_Account(Username,UserPassword,UserAddress,UserRole,isDelete) Values(?,?,?,'User',0)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            System.out.println();

            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getAddress());

            insert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return insert;
    }

    public List<AccountDTO> findByLikeName(String search) throws Exception {
        List<AccountDTO> result = null;
        AccountDTO dto = null;
        String username, address, role, password;
        try {
            String sql = "Select Username, UserPassword, UserAddress, UserRole From tbl_Account Where Username LIKE ? and isDelete = 0";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("Username");
                password = rs.getString("UserPassword");
                address = rs.getString("UserAddress");
                role = rs.getString("UserRole");

                dto = new AccountDTO(username, password, address, role);
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
            String sql = "Update tbl_Account SET isDelete = ? Where Username = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "1");
            preStm.setString(2, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean UpdateUser(AccountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tbl_Account SET UserPassword = ?,UserAddress = ?,UserRole = ? Where Username = ? ";

            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPassword());

            preStm.setString(2, dto.getAddress());

            preStm.setString(3, dto.getRole());
            preStm.setString(4, dto.getUsername());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public AccountDTO findByPrimaryKey(String id) throws Exception {
        AccountDTO dto = null;
        String useradress = null;
        String userrole = null;
        String password = null;
        try {
            String sql = "Select Username, UserPassword, UserAddress, UserRole From tbl_Account Where Username = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                password = rs.getString("UserPassword");
                useradress = rs.getString("UserAddress");
                userrole = rs.getString("UserRole");
                dto = new AccountDTO(id, password,useradress);
                dto.setRole(userrole);

            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
