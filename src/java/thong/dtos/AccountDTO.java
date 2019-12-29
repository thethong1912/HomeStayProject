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
public class AccountDTO implements Serializable {
    private String username, password, address, role;
    private boolean isDelete;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String address) {
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public AccountDTO(String username, String address) {
        this.username = username;
        this.address = address;
    }
   
    
    
    public AccountDTO(String username, String password, String address, String role) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    
    
}
