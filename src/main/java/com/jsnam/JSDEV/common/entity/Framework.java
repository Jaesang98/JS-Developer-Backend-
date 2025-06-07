package com.jsnam.JSDEV.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Framework {

    @Id
    @Column(name = "framework_id", nullable = false, length = 50)
    private String frameworkId;

    @Column(name = "framework_name", nullable = false, length = 50)
    private String frameworkName;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "deleteYn", nullable = false, length = 1)
    private String deleteYn;

}
