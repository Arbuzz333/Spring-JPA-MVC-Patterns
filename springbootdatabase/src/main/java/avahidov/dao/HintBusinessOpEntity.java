package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;




@Entity
@Table(name = "hint_business_op", schema = "public", catalog = "hints")
public class HintBusinessOpEntity extends HintBaseEntity {

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "business_op_id")
    private List<HintChannelEntity> refHintChannelEntity;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "refHintBusinessOpEntity")
    private List<HintBusinessStepEntity> refHintBusinessStepEntities;

    @OneToOne(mappedBy = "refHintBusinessOpEntity")
    private HintUserEntity refHintUserEntities;
    
    public HintBusinessOpEntity() {
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<HintChannelEntity> getRefHintChannelEntity() {
        return refHintChannelEntity;
    }

    public void setRefHintChannelEntity(List<HintChannelEntity> refHintChannelEntity) {
        this.refHintChannelEntity = refHintChannelEntity;
    }

    public List<HintBusinessStepEntity> getRefHintBusinessStepEntities() {
        return refHintBusinessStepEntities;
    }

    public void setRefHintBusinessStepEntities(List<HintBusinessStepEntity> refHintBusinessStepEntities) {
        this.refHintBusinessStepEntities = refHintBusinessStepEntities;
    }

    public HintUserEntity getRefHintUserEntities() {
        return refHintUserEntities;
    }

    public void setRefHintUserEntities(HintUserEntity refHintUserEntities) {
        this.refHintUserEntities = refHintUserEntities;
    }

    public String toString() {
        return
                "id =" + getId() +
                "code = " + getCode() +
                "title = " + getTitle() +
                "channelCode = $channelCode " +
                "modifiedDate = $modifiedDate " +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintBusinessOpEntity)) return false;
        HintBusinessOpEntity that = (HintBusinessOpEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 25;
    }
}

