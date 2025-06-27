package com.jsnam.JSDEV.dictGuide.entity;

import com.jsnam.JSDEV.common.entity.Framework;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class MenuCode {

    @Id
    @Column(name = "code_id", nullable = false, length = 50)
    private String codeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id", nullable = false)
    private MenuDescription menuDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "framework_id", nullable = false)
    private Framework framework;

    @Lob
    @Column(name = "code", columnDefinition = "TEXT", nullable = false)
    private String code;

    @Lob
    @Column(name = "code_description  ", columnDefinition = "TEXT", nullable = false)
    private String codeDescription;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "delete_yn", nullable = false, length = 1)
    private String deleteYn;

}
