package org.xmgdtc.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "rfc4122-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "rfc4122-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "deleted")
    private Boolean deleted;

    @Version()
    @Column(name = "version")
    private Date version;


    public void init() {
        this.deleted = false;
    }

}