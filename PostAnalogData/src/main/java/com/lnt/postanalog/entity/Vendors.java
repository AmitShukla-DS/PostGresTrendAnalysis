package com.lnt.postanalog.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "lntds_vendors")
public class Vendors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_seq")
    private Integer vendorSeq;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "description")
    private String description;

    @Column(name = "vendor_address")
    private String vendorAddress;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_timestamp")
    private Timestamp updateTimestamp;

    @JsonIgnore
    @OneToMany(mappedBy = "vendors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssetTypes> assetTypes;

    @JsonIgnore
    @OneToMany(mappedBy = "vendors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assets> assets;

    public Integer getVendorSeq() {
        return vendorSeq;
    }

    public void setVendorSeq(Integer vendorSeq) {
        this.vendorSeq = vendorSeq;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public List<AssetTypes> getAssetTypes() {
        return assetTypes;
    }

    public void setAssetTypes(List<AssetTypes> assetTypes) {
        this.assetTypes = assetTypes;
    }

    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }
}


