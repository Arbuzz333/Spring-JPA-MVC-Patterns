package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "hint_hint", schema = "public", catalog = "hints")
public class HintHintEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "channel_code", nullable = false, insertable = false, updatable = false)
    private Long channelCode;

    @Basic
    @Column(name = "code", nullable = false)
    private String code;

    @Basic
    @Column(name = "hint_text", nullable = true)
    private String hintText;

    @Basic
    @Column(name = "hint_type", nullable = true)
    private String hintType;

    @Basic
    @Column(name = "status", nullable = false)
    private String status;

    @Basic
    @Column(name = "pilot", nullable = false)
    private Boolean pilot;

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_code", referencedColumnName = "id")
    private HintChannelEntity refHintChannelEntity;

    public HintHintEntity() {
    }

    public String toString() {
        return
                "id = $id " +
                "channelCode = $channelCode " +
                "code = $code " +
                "hintText = $hintText " +
                "hintType = $hintType " +
                "status = $status " +
                "pilot = $pilot " +
                "modifiedDate = $modifiedDate " +
                ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getchannelCode() {
        return channelCode;
    }

    public void setchannelCode(Long channelCode) {
        this.channelCode = channelCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public String getHintType() {
        return hintType;
    }

    public void setHintType(String hintType) {
        this.hintType = hintType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPilot() {
        return pilot;
    }

    public void setPilot(Boolean pilot) {
        this.pilot = pilot;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public HintChannelEntity getrefHintChannelEntity() {
        return refHintChannelEntity;
    }

    public void setrefHintChannelEntity(HintChannelEntity refHintChannelEntity) {
        this.refHintChannelEntity = refHintChannelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintHintEntity)) return false;
        HintHintEntity that = (HintHintEntity) o;
        return getId().equals(that.getId()) &&
                getchannelCode().equals(that.getchannelCode()) &&
                getCode().equals(that.getCode()) &&
                Objects.equals(getHintText(), that.getHintText()) &&
                Objects.equals(getHintType(), that.getHintType()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getPilot(), that.getPilot()) &&
                getModifiedDate().equals(that.getModifiedDate()) &&
                Objects.equals(getrefHintChannelEntity(), that.getrefHintChannelEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getchannelCode(), getCode(), getHintText(), getHintType(), getStatus(),
                getPilot(), getModifiedDate(), getrefHintChannelEntity());
    }
}

