package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;




@Entity
@Table(name = "hint_business_op", schema = "public", catalog = "hints")
public class HintBusinessOpEntity {

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Basic
    @Column(name = "code", nullable = false)
    private String code;

    @Basic
    @Column(name = "title", nullable = true)
    private String title;

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
                "id = $id " +
                "code = $code " +
                "title = $title " +
                "channelCode = $channelCode " +
                "modifiedDate = $modifiedDate " +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintBusinessOpEntity)) return false;
        HintBusinessOpEntity that = (HintBusinessOpEntity) o;
        return id.equals(that.id) &&
                code.equals(that.code) &&
                Objects.equals(title, that.title) &&
                channelCode.equals(that.channelCode) &&
                modifiedDate.equals(that.modifiedDate) &&
                refHintChannelEntity.equals(that.refHintChannelEntity) &&
                Objects.equals(refHintBusinessStepEntities, that.refHintBusinessStepEntities) &&
                Objects.equals(refHintUserEntities, that.refHintUserEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, title, channelCode, modifiedDate, refHintChannelEntity,
                refHintBusinessStepEntities, refHintUserEntities);
    }
}

