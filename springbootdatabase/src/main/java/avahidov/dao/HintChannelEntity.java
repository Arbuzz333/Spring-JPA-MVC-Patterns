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

    @ManyToOne
    @JoinColumn(name = "business_op_id", referencedColumnName = "id")
    private HintBusinessOpEntity refHintBusinessOpEntities;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "refHintChannelEntity")
    private List<HintHintEntity> refHintHintEntities;

    public HintChannelEntity() {
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public HintBusinessOpEntity getRefHintBusinessOpEntities() {
        return refHintBusinessOpEntities;
    }

    public void setRefHintBusinessOpEntities(HintBusinessOpEntity refHintBusinessOpEntities) {
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
                        "modifiedDate = " + modifiedDate +
                        ")";
    }

}

