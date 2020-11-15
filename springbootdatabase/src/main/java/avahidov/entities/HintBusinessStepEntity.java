package avahidov.entities;

import javax.persistence.*;
import java.sql.Date;




@Entity
@Table(name = "hint_business_step", schema = "public", catalog = "hints")
public class HintBusinessStepEntity extends HintBaseEntity {

    @Basic
    @Column(name = "business_op_id", insertable = false, updatable = false)
    private Long businessOpId;

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_op_id", referencedColumnName = "id")
    private HintBusinessOpEntity refHintBusinessOpEntity;

    public HintBusinessStepEntity() {
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

    public Long getBusinessOpId() {
        return businessOpId;
    }

    public void setBusinessOpId(Long businessOpId) {
        this.businessOpId = businessOpId;
    }
}

