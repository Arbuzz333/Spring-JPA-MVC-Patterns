package avahidov.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;



@Entity
@Table(name = "hint_channel", schema = "public", catalog = "hints")
public class HintChannelEntity extends HintBaseEntity{

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @OneToMany(mappedBy = "refHintChannelEntity")
    private List<HintBusinessOpEntity> refHintBusinessOpEntities;

    @OneToMany(mappedBy = "refHintChannelEntity")
    private List<HintHintEntity> refHintHintEntities;

    public HintChannelEntity() {
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
        return  getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 7;
    }

    public String toString() {
        return
                "id =" + getId() +
                        "code = " + getCode() +
                        "title = " + getTitle() +
                        "modifiedDate = $modifiedDate " +
                        ")";
    }

}

