package com.jsnam.JSDEV.dictGuide.dto;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private String menuId;
    private String menuName;
    private Integer menuLevel;
    private String parentId;
    private LocalDateTime updated;

    public MenuDto(Menu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.menuLevel = menu.getMenuLevel();
        this.parentId = menu.getParentId();
        this.updated = menu.getUpdated();
    }
}
