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
public class ServiceDTO implements Serializable{
    private String serName, servDescription, serImage;
    private Float serPrice;
    private boolean serStatus;
    private int serID, serQuantity, typeRomeService;

    public ServiceDTO(String serName, String servDescription, String serImage, Float serPrice, int serID, int serQuantity, int typeRomeService) {
        this.serName = serName;
        this.servDescription = servDescription;
        this.serImage = serImage;
        this.serPrice = serPrice;
        this.serID = serID;
        this.serQuantity = serQuantity;
        this.typeRomeService = typeRomeService;
    }

    
    public ServiceDTO(String serName, String servDescription, String serImage, int typeRomeService, Float serPrice, boolean serStatus, int serQuantity) {
        this.serName = serName;
        this.servDescription = servDescription;
        this.serImage = serImage;
        this.typeRomeService = typeRomeService;
        this.serPrice = serPrice;
        this.serStatus = serStatus;
        this.serQuantity = serQuantity;
    }

    public ServiceDTO(String serName, String servDescription, String serImage, Float serPrice, int serQuantity, int typeRomeService) {
        this.serName = serName;
        this.servDescription = servDescription;
        this.serImage = serImage;
        this.serPrice = serPrice;
        this.serQuantity = serQuantity;
        this.typeRomeService = typeRomeService;
    }

    public int getSerID() {
        return serID;
    }

    public void setSerID(int serID) {
        this.serID = serID;
    }
    

    public int getSerQuantity() {
        return serQuantity;
    }

    public void setSerQuantity(int serQuantity) {
        this.serQuantity = serQuantity;
    }

    public ServiceDTO() {
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getServDescription() {
        return servDescription;
    }

    public void setServDescription(String servDescription) {
        this.servDescription = servDescription;
    }

    public String getSerImage() {
        return serImage;
    }

    public void setSerImage(String serImage) {
        this.serImage = serImage;
    }

    public int getTypeRomeService() {
        return typeRomeService;
    }

    public void setTypeRomeService(int typeRomeService) {
        this.typeRomeService = typeRomeService;
    }

    public Float getSerPrice() {
        return serPrice;
    }

    public void setSerPrice(Float serPrice) {
        this.serPrice = serPrice;
    }

    public boolean isSerStatus() {
        return serStatus;
    }

    public void setSerStatus(boolean serStatus) {
        this.serStatus = serStatus;
    }
    
    
}
