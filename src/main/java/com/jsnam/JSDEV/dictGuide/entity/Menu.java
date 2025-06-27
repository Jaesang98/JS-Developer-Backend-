package com.jsnam.JSDEV.dictGuide.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Menu {

    @Id
    @Column(name = "menu_id", nullable = false, length = 50)
    private String menuId;

    @Column(name = "menu_name", nullable = false, length = 50)
    private String menuName;

    @Column(name = "menu_level", nullable = false, length = 50)
    private Integer menuLevel;

    @Column(name = "parent_id", nullable = false, length = 50)
    private String parentId;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "delete_yn", nullable = false, length = 1)
    private String deleteYn;

}
