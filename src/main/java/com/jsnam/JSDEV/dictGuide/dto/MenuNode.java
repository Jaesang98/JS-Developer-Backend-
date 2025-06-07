package com.jsnam.JSDEV.dictGuide.dto;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuNode {
    private String menuId;
    private String menuName;
    private Integer menuLevel;
    private String parentId;
    private List<MenuNode> children = new ArrayList<>();

    public MenuNode(MenuDto dto) {
        this.menuId = dto.getMenuId();
        this.menuName = dto.getMenuName();
        this.menuLevel = dto.getMenuLevel();
        this.parentId = dto.getParentId();
    }
}

