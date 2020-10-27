package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;




@Entity
@Table(name = "hint_business_step", schema = "public", catalog = "hints")
public class HintBusinessStepEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "code", nullable = false)
    private String code;

    @Basic
    @Column(name = "title", nullable = true)
    private String title;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                "id = $id " +
                "code = $code " +
                "title = $title " +
                "businessOpCode = $businessOpCode " +
                "modifiedDate = $modifiedDate " +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintBusinessStepEntity)) return false;
        HintBusinessStepEntity that = (HintBusinessStepEntity) o;
        return id.equals(that.id) &&
                code.equals(that.code) &&
                Objects.equals(title, that.title) &&
                Objects.equals(businessOpCode, that.businessOpCode) &&
                modifiedDate.equals(that.modifiedDate) &&
                Objects.equals(refHintBusinessOpEntity, that.refHintBusinessOpEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, title, businessOpCode, modifiedDate, refHintBusinessOpEntity);
    }
}

