/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseRelations;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "DEPENDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dependent.findAll", query = "SELECT d FROM Dependent d")
    , @NamedQuery(name = "Dependent.findByEmpNo", query = "SELECT d FROM Dependent d WHERE d.dependentPK.empNo = :empNo")
    , @NamedQuery(name = "Dependent.findByDepName", query = "SELECT d FROM Dependent d WHERE d.dependentPK.depName = :depName")
    , @NamedQuery(name = "Dependent.findByDepBd", query = "SELECT d FROM Dependent d WHERE d.depBd = :depBd")
    , @NamedQuery(name = "Dependent.findByDepGender", query = "SELECT d FROM Dependent d WHERE d.depGender = :depGender")
    , @NamedQuery(name = "Dependent.findByDepRelationship", query = "SELECT d FROM Dependent d WHERE d.depRelationship = :depRelationship")})
public class Dependent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DependentPK dependentPK;
    @Column(name = "DEP_BD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depBd;
    @Column(name = "DEP_GENDER")
    private Character depGender;
    @Column(name = "DEP_RELATIONSHIP")
    private String depRelationship;
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    public Dependent() {
    }

    public Dependent(DependentPK dependentPK) {
        this.dependentPK = dependentPK;
    }

    public Dependent(int empNo, String depName) {
        this.dependentPK = new DependentPK(empNo, depName);
    }

    public DependentPK getDependentPK() {
        return dependentPK;
    }

    public void setDependentPK(DependentPK dependentPK) {
        this.dependentPK = dependentPK;
    }

    public Date getDepBd() {
        return depBd;
    }

    public void setDepBd(Date depBd) {
        this.depBd = depBd;
    }

    public Character getDepGender() {
        return depGender;
    }

    public void setDepGender(Character depGender) {
        this.depGender = depGender;
    }

    public String getDepRelationship() {
        return depRelationship;
    }

    public void setDepRelationship(String depRelationship) {
        this.depRelationship = depRelationship;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dependentPK != null ? dependentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependent)) {
            return false;
        }
        Dependent other = (Dependent) object;
        if ((this.dependentPK == null && other.dependentPK != null) || (this.dependentPK != null && !this.dependentPK.equals(other.dependentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseRelations.Dependent[ dependentPK=" + dependentPK + " ]";
    }
    
}
