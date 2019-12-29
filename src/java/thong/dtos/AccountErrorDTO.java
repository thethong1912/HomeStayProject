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
public class AccountErrorDTO implements Serializable {

    private String errorUsername, errorPassword, errorConfirm, errorAddress, errorRole;
    private boolean errorIsDelete;

    public String getErrorConfirm() {
        return errorConfirm;
    }

    public void setErrorConfirm(String errorConfirm) {
        this.errorConfirm = errorConfirm;
    }

    public AccountErrorDTO() {
    }

    public String getErrorUsername() {
        return errorUsername;
    }

    public void setErrorUsername(String errorUsername) {
        this.errorUsername = errorUsername;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

    public String getErrorAdress() {
        return errorAddress;
    }

    public void setErrorAdress(String errorAddress) {
        this.errorAddress = errorAddress;
    }

    public String getErrorRole() {
        return errorRole;
    }

    public void setErrorRole(String errorRole) {
        this.errorRole = errorRole;
    }

    public boolean isErrorIsDelete() {
        return errorIsDelete;
    }

    public void setErrorIsDelete(boolean errorIsDelete) {
        this.errorIsDelete = errorIsDelete;
    }
}
