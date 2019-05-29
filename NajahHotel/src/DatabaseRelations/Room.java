/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseRelations;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ashraf
 */
@Entity
@Table(name = "ROOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
    , @NamedQuery(name = "Room.findByRoomNo", query = "SELECT r FROM Room r WHERE r.roomNo = :roomNo")
    , @NamedQuery(name = "Room.findByPurposeOfVisit", query = "SELECT r FROM Room r WHERE r.purposeOfVisit = :purposeOfVisit")
    , @NamedQuery(name = "Room.findByCheckInDate", query = "SELECT r FROM Room r WHERE r.checkInDate = :checkInDate")
    , @NamedQuery(name = "Room.findByPhoneNo", query = "SELECT r FROM Room r WHERE r.phoneNo = :phoneNo")
    , @NamedQuery(name = "Room.findByAvailability", query = "SELECT r FROM Room r WHERE r.availability = :availability")
    , @NamedQuery(name = "Room.findByVisitPeriod", query = "SELECT r FROM Room r WHERE r.visitPeriod = :visitPeriod")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ROOM_NO")
    private Short roomNo;
    @Column(name = "PURPOSE_OF_VISIT")
    private String purposeOfVisit;
    @Column(name = "CHECK_IN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;
    @Basic(optional = false)
    @Column(name = "PHONE_NO")
    private long phoneNo;
    @Basic(optional = false)
    @Column(name = "AVAILABILITY")
    private Character availability;
    @Column(name = "VISIT_PERIOD")
    private Short visitPeriod;
    @JoinColumn(name = "BOOKING_NO", referencedColumnName = "BOOKING_NO")
    @ManyToOne
    private Booking bookingNo;
    @JoinColumn(name = "ROOM_TYPE", referencedColumnName = "TYPE_CODE")
    @ManyToOne(optional = false)
    private RoomType roomType;

    public Room() {
    }

    public Room(Short roomNo) {
        this.roomNo = roomNo;
    }

    public Room(Short roomNo, long phoneNo, Character availability) {
        this.roomNo = roomNo;
        this.phoneNo = phoneNo;
        this.availability = availability;
    }

    public Short getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Short roomNo) {
        this.roomNo = roomNo;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Character getAvailability() {
        return availability;
    }

    public void setAvailability(Character availability) {
        this.availability = availability;
    }

    public Short getVisitPeriod() {
        return visitPeriod;
    }

    public void setVisitPeriod(Short visitPeriod) {
        this.visitPeriod = visitPeriod;
    }

    public Booking getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(Booking bookingNo) {
        this.bookingNo = bookingNo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomNo != null ? roomNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomNo == null && other.roomNo != null) || (this.roomNo != null && !this.roomNo.equals(other.roomNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseRelations.Room[ roomNo=" + roomNo + " ]";
    }
    
}
