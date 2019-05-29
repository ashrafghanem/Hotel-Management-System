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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ASSIGNS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assigns.findAll", query = "SELECT a FROM Assigns a")
    , @NamedQuery(name = "Assigns.findByUsername", query = "SELECT a FROM Assigns a WHERE a.username = :username")
    , @NamedQuery(name = "Assigns.findByPassword", query = "SELECT a FROM Assigns a WHERE a.password = :password")
    , @NamedQuery(name = "Assigns.findBySecurityQuestion", query = "SELECT a FROM Assigns a WHERE a.securityQuestion = :securityQuestion")
    , @NamedQuery(name = "Assigns.findByAnswer", query = "SELECT a FROM Assigns a WHERE a.answer = :answer")})
public class Assigns implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "SECURITY_QUESTION")
    private String securityQuestion;
    @Basic(optional = false)
    @Column(name = "ANSWER")
    private String answer;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO")
    @ManyToOne(optional = false)
    private Employee empNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private List<Booking> bookingList;

    public Assigns() {
    }

    public Assigns(String username) {
        this.username = username;
    }

    public Assigns(String username, String password, String securityQuestion, String answer) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.answer = answer;
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

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Employee getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Employee empNo) {
        this.empNo = empNo;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assigns)) {
            return false;
        }
        Assigns other = (Assigns) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseRelations.Assigns[ username=" + username + " ]";
    }
    
}
