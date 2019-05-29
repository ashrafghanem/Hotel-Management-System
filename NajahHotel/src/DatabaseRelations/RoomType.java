/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseRelations;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ashraf
 */
@Entity
@Table(name = "ROOM_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomType.findAll", query = "SELECT r FROM RoomType r")
    , @NamedQuery(name = "RoomType.findByTypeCode", query = "SELECT r FROM RoomType r WHERE r.typeCode = :typeCode")
    , @NamedQuery(name = "RoomType.findByDescription", query = "SELECT r FROM RoomType r WHERE r.description = :description")
    , @NamedQuery(name = "RoomType.findByPrice", query = "SELECT r FROM RoomType r WHERE r.price = :price")
    , @NamedQuery(name = "RoomType.findByFloor", query = "SELECT r FROM RoomType r WHERE r.floor = :floor")})
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TYPE_CODE")
    private String typeCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private int price;
    @Basic(optional = false)
    @Column(name = "FLOOR")
    private short floor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomType")
    private List<Room> roomList;

    public RoomType() {
    }

    public RoomType(String typeCode) {
        this.typeCode = typeCode;
    }

    public RoomType(String typeCode, int price, short floor) {
        this.typeCode = typeCode;
        this.price = price;
        this.floor = floor;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public short getFloor() {
        return floor;
    }

    public void setFloor(short floor) {
        this.floor = floor;
    }

    @XmlTransient
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeCode != null ? typeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomType)) {
            return false;
        }
        RoomType other = (RoomType) object;
        if ((this.typeCode == null && other.typeCode != null) || (this.typeCode != null && !this.typeCode.equals(other.typeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseRelations.RoomType[ typeCode=" + typeCode + " ]";
    }
    
}
