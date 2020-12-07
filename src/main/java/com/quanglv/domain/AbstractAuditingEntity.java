package com.quanglv.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode
public abstract class AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 6957172879983027097L;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Date createdDate = new Date();

    // @CreatedBy
    @Column(name = "created_user", nullable = false, updatable = false)
    private Long createdUser = 1L;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate = new Date() ;

    // @LastModifiedBy
    @Column(name = "updated_user", nullable = false)
    private Long updatedUser = 1L;
}
