/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ds.rmids;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class UsersRole {
    
    private String username;
    private boolean manager, serviceTechnician, inspector, powerUser, user;
    
    public UsersRole(String username, boolean manager, boolean serviceTechnician, boolean inspector, boolean powerUser, boolean user)
    {
        this.username = username;
        this.manager = manager;
        this.serviceTechnician = serviceTechnician;
        this.inspector = inspector;
        this.powerUser = powerUser;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isServiceTechnician() {
        return serviceTechnician;
    }

    public void setServiceTechnician(boolean serviceTechnician) {
        this.serviceTechnician = serviceTechnician;
    }

    public boolean isInspector() {
        return inspector;
    }

    public void setInspector(boolean inspector) {
        this.inspector = inspector;
    }

    public boolean isPowerUser() {
        return powerUser;
    }

    public void setPowerUser(boolean powerUser) {
        this.powerUser = powerUser;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
    
    
    
}
