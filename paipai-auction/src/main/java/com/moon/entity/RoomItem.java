package com.moon.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "room_item")
public class RoomItem implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "owner_account")
    private String ownerAccount;

    private BigDecimal currentprice;

    private Date datetime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return room_id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * @return owner_id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return owner_account
     */
    public String getOwnerAccount() {
        return ownerAccount;
    }

    /**
     * @param ownerAccount
     */
    public void setOwnerAccount(String ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    /**
     * @return currentprice
     */
    public BigDecimal getCurrentprice() {
        return currentprice;
    }

    /**
     * @param currentprice
     */
    public void setCurrentprice(BigDecimal currentprice) {
        this.currentprice = currentprice;
    }

    /**
     * @return datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}