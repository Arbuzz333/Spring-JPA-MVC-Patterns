package avahidov.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.UUID;


@Entity
@Table(name = "hint_hint", schema = "public", catalog = "hints")
public class HintTextEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "channel_id")
    private Long channelId;

    @Basic
    @Column(name = "hint_text")
    private String hintText;

    @Transient
    private final UUID unique = UUID.randomUUID();

    protected HintTextEntity() {
    }

    public String toString() {
        return
                "(id = " + id +
                " hintText = " + hintText +
                " channelId = " + channelId +
                ")";
    }

    public Long getId() {
        return id;
    }

    public String getHintText() {
        return hintText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HintTextEntity)) return false;
        HintTextEntity that = (HintTextEntity) o;
        if (getId() != null && that.getId() != null) return getId().equals(that.getId());
        return getUnique().equals(that.getUnique());
    }

    @Override
    public int hashCode() {
        return getUnique().hashCode();
    }

    public Long getChannelId() {
        return channelId;
    }

    public UUID getUnique() {
        return unique;
    }
}

