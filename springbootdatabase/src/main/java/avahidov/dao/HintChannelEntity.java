package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "hint_channel", schema = "public", catalog = "hints")
public class HintChannelEntity {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Basic
    @Column(name = "code", nullable = false)
    private String code;

    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @OneToMany(mappedBy = "refHintChannelEntity")
    private List<HintBusinessOpEntity> refHintBusinessOpEntities;

    @OneToMany(mappedBy = "refHintChannelEntity")
    private List<HintHintEntity> refHintHintEntities;

    public HintChannelEntity() {
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<HintBusinessOpEntity> getRefHintBusinessOpEntities() {
        return refHintBusinessOpEntities;
    }

    public void setRefHintBusinessOpEntities(List<HintBusinessOpEntity> refHintBusinessOpEntities) {
        this.refHintBusinessOpEntities = refHintBusinessOpEntities;
    }

    public List<HintHintEntity> getRefHintHintEntities() {
        return refHintHintEntities;
    }

    public void setRefHintHintEntities(List<HintHintEntity> refHintHintEntities) {
        this.refHintHintEntities = refHintHintEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintChannelEntity)) return false;
        HintChannelEntity that = (HintChannelEntity) o;
        return getId().equals(that.getId()) &&
                getCode().equals(that.getCode()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                getModifiedDate().equals(that.getModifiedDate()) &&
                Objects.equals(getRefHintBusinessOpEntities(), that.getRefHintBusinessOpEntities()) &&
                Objects.equals(getRefHintHintEntities(), that.getRefHintHintEntities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getTitle(), getModifiedDate(), getRefHintBusinessOpEntities(), getRefHintHintEntities());
    }

    public String toString() {
        return
                "id = $id " +
                        "code = $code " +
                        "title = $title " +
                        "modifiedDate = $modifiedDate " +
                        ")";
    }

}

