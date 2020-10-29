package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;




@Entity
@Table(name = "hint_business_step", schema = "public", catalog = "hints")
public class HintBusinessStepEntity extends HintBaseEntity {

    @Basic
    @Column(name = "business_op_code", nullable = false, insertable = false, updatable = false)
    private Long businessOpCode;

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_op_code", referencedColumnName = "id")
    private HintBusinessOpEntity refHintBusinessOpEntity;

    public HintBusinessStepEntity() {
    }

    public Long getBusinessOpCode() {
        return businessOpCode;
    }

    public void setBusinessOpCode(Long businessOpCode) {
        this.businessOpCode = businessOpCode;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public HintBusinessOpEntity getRefHintBusinessOpEntity() {
        return refHintBusinessOpEntity;
    }

    public void setRefHintBusinessOpEntity(HintBusinessOpEntity refHintBusinessOpEntity) {
        this.refHintBusinessOpEntity = refHintBusinessOpEntity;
    }

    public String toString() {
        return
                "id =" + getId() +
                "code = " + getCode() +
                "title = " + getTitle() +
                "businessOpCode = " + businessOpCode +
                "modifiedDate = " + modifiedDate +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintBusinessStepEntity)) return false;
        HintBusinessStepEntity that = (HintBusinessStepEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 27;
    }
}

