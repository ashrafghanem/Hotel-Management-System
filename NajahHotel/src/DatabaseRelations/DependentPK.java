/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseRelations;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ashraf
 */
@Embeddable
public class DependentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private int empNo;
    @Basic(optional = false)
    @Column(name = "DEP_NAME")
    private String depName;

    public DependentPK() {
    }

    public DependentPK(int empNo, String depName) {
        this.empNo = empNo;
        this.depName = depName;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empNo;
        hash += (depName != null ? depName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependentPK)) {
            return false;
        }
        DependentPK other = (DependentPK) object;
        if (this.empNo != other.empNo) {
            return false;
        }
        if ((this.depName == null && other.depName != null) || (this.depName != null && !this.depName.equals(other.depName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseRelations.DependentPK[ empNo=" + empNo + ", depName=" + depName + " ]";
    }
    
}
