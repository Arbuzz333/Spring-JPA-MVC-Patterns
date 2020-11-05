package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;




@Entity
@Table(name = "hint_business_op", schema = "public", catalog = "hints")
public class HintBusinessOpEntity extends HintBaseEntity {

    @Basic
    @Column(name = "channel_code", nullable = false, insertable = false, updatable = false)
    private Long channelCode;

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_code", referencedColumnName = "id")
    private HintChannelEntity refHintChannelEntity;

    @OneToMany(mappedBy = "refHintBusinessOpEntity")
    private List<HintBusinessStepEntity> refHintBusinessStepEntities;

    @OneToMany(mappedBy = "refHintBusinessOpEntity")
    private List<HintUserEntity> refHintUserEntities;
    
    public HintBusinessOpEntity() {
    }

    public Long getchannelCode() {
        return channelCode;
    }

    public void setchannelCode(Long channelCode) {
        this.channelCode = channelCode;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public HintChannelEntity getRefHintChannelEntity() {
        return refHintChannelEntity;
    }

    public void setRefHintChannelEntity(HintChannelEntity refHintChannelEntity) {
        this.refHintChannelEntity = refHintChannelEntity;
    }

    public List<HintBusinessStepEntity> getRefHintBusinessStepEntities() {
        return refHintBusinessStepEntities;
    }

    public void setRefHintBusinessStepEntities(List<HintBusinessStepEntity> refHintBusinessStepEntities) {
        this.refHintBusinessStepEntities = refHintBusinessStepEntities;
    }

    public List<HintUserEntity> getRefHintUserEntities() {
        return refHintUserEntities;
    }

    public void setRefHintUserEntities(List<HintUserEntity> refHintUserEntities) {
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

