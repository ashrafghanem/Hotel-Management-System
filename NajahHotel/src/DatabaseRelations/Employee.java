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
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByEmpNo", query = "SELECT e FROM Employee e WHERE e.empNo = :empNo")
    , @NamedQuery(name = "Employee.findBySalary", query = "SELECT e FROM Employee e WHERE e.salary = :salary")
    , @NamedQuery(name = "Employee.findBySocialState", query = "SELECT e FROM Employee e WHERE e.socialState = :socialState")
    , @NamedQuery(name = "Employee.findByPhoneNo", query = "SELECT e FROM Employee e WHERE e.phoneNo = :phoneNo")
    , @NamedQuery(name = "Employee.findByFamilyCount", query = "SELECT e FROM Employee e WHERE e.familyCount = :familyCount")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private Integer empNo;
    @Basic(optional = false)
    @Column(name = "SALARY")
    private int salary;
    @Basic(optional = false)
    @Column(name = "SOCIAL_STATE")
    private String socialState;
    @Column(name = "PHONE_NO")
    private Long phoneNo;
    @Column(name = "FAMILY_COUNT")
    private Short familyCount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Dependent> dependentList;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Person empId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empNo")
    private List<Assigns> assignsList;

    public Employee() {
    }

    public Employee(Integer empNo) {
        this.empNo = empNo;
    }

    public Employee(Integer empNo, int salary, String socialState) {
        this.empNo = empNo;
        this.salary = salary;
        this.socialState = socialState;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getSocialState() {
        return socialState;
    }

    public void setSocialState(String socialState) {
        this.socialState = socialState;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Short getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Short familyCount) {
        this.familyCount = familyCount;
    }

    @XmlTransient
    public List<Dependent> getDependentList() {
        return dependentList;
    }

    public void setDependentList(List<Dependent> dependentList) {
        this.dependentList = dependentList;
    }

    public Person getEmpId() {
        return empId;
    }

    public void setEmpId(Person empId) {
        this.empId = empId;
    }

    @XmlTransient
    public List<Assigns> getAssignsList() {
        return assignsList;
    }

    public void setAssignsList(List<Assigns> assignsList) {
        this.assignsList = assignsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseRelations.Employee[ empNo=" + empNo + " ]";
    }
    
}
