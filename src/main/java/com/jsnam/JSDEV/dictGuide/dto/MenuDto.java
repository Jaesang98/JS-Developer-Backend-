package com.jsnam.JSDEV.dictGuide.dto;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private String menuId;
    private String menuName;
    private Integer menuLevel;
    private String parentId;

    public static MenuDto from(Menu menu) {
        return new MenuDto(
                menu.getMenuId(),
                menu.getMenuName(),
                menu.getMenuLevel(),
                menu.getParentId()
        );
    }
}
