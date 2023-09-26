package com.moon.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Room implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private BigDecimal startprice;

    private BigDecimal currentprice;

    private BigDecimal stepprice;

    private Date starttime;

    private Date endtime;

    @Column(name = "good_id")
    private Integer goodId;

    private String gname;

    private Integer status;

    @Column(name = "up_id")
    private Integer upId;

    @Column(name = "up_account")
    private String upAccount;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "owner_account")
    private String ownerAccount;

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
     * @return startprice
     */
    public BigDecimal getStartprice() {
        return startprice;
    }

    /**
     * @param startprice
     */
    public void setStartprice(BigDecimal startprice) {
        this.startprice = startprice;
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
     * @return stepprice
     */
    public BigDecimal getStepprice() {
        return stepprice;
    }

    /**
     * @param stepprice
     */
    public void setStepprice(BigDecimal stepprice) {
        this.stepprice = stepprice;
    }

    /**
     * @return starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * @return endtime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * @return good_id
     */
    public Integer getGoodId() {
        return goodId;
    }

    /**
     * @param goodId
     */
    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    /**
     * @return gname
     */
    public String getGname() {
        return gname;
    }

    /**
     * @param gname
     */
    public void setGname(String gname) {
        this.gname = gname;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return up_id
     */
    public Integer getUpId() {
        return upId;
    }

    /**
     * @param upId
     */
    public void setUpId(Integer upId) {
        this.upId = upId;
    }

    /**
     * @return up_account
     */
    public String getUpAccount() {
        return upAccount;
    }

    /**
     * @param upAccount
     */
    public void setUpAccount(String upAccount) {
        this.upAccount = upAccount;
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
}