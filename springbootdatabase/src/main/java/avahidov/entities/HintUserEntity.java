package avahidov.entities;

import javax.persistence.*;
import java.sql.Date;




@Entity
@Table(name = "hint_user", schema = "public", catalog = "hints")
public class HintUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName="seq_hint", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "\"user\"", nullable = false)
    private String user;

    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Basic
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false, mappedBy = "refHintUserEntities")
    private HintBusinessOpEntity refHintBusinessOpEntity;

    public HintUserEntity() {
    }

    public String toString() {
        return
                "id = " + id +
                        "user = " + user +
                        "createDate = " + createDate +
                        "modifiedDate = " + modifiedDate +
                        ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintUserEntity)) return false;
        HintUserEntity that = (HintUserEntity) o;
        return  getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}

