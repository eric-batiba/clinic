package com.btb.sante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class AbstractEntity extends AuditingEntityListener {
    @CreatedDate()
    @Column(name = "create_date",nullable = false,updatable = false)
    private LocalDateTime createDate;
    @LastModifiedDate()
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
