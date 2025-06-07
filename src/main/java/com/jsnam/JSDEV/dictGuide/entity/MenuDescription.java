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
@Table(name = "menu_description")
public class MenuDescription {

    @Id
    @Column(name = "menuDes_id", nullable = false, length = 50)
    private String menuDesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

    @Column(name = "gif_url", nullable = false, length = 255)
    private String gifUrl;

    @Column(name = "iframe_url", nullable = false, length = 255)
    private String iframeUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "framework_id", nullable = false)
    private Framework framework;

    @Lob
    @Column(name = "code_description", columnDefinition = "TEXT", nullable = false)
    private String codeDescription;

    @Lob
    @Column(name = "menu_description", columnDefinition = "TEXT", nullable = false)
    private String menuDescription;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "deleteYn", nullable = false, length = 1)
    private String deleteYn;

}
