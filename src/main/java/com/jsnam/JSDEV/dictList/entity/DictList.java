package com.jsnam.JSDEV.dictList.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class DictList {

    @Id
    @Column(name = "dict_id", nullable = false, length = 36)
    private String dictId;

    @PrePersist
    public void generateId() {
        if (this.dictId == null) {
            this.dictId = UUID.randomUUID().toString();
        }
    }


//    User Entity만들어지면 주석풀기
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "dict_title", nullable = false, length = 50)
    private String dictTitle;

    @Lob
    @Column(name = "dict_description", columnDefinition = "TEXT", nullable = false)
    private String dictDescription;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "deleteYn", nullable = false, length = 1)
    private String deleteYn;

}
