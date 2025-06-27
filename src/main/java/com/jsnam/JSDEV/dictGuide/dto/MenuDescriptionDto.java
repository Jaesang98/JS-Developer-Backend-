package com.jsnam.JSDEV.dictGuide.dto;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import com.jsnam.JSDEV.dictGuide.entity.MenuCode;
import com.jsnam.JSDEV.dictGuide.entity.MenuDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDescriptionDto {
    private String descriptionId;
    private String menuId;
    private String menuName;
    private String imageUrl;
    private String gifUrl;
    private String iframeUrl;
    private List<MenuCodeDto> code;
    private String menuDescription;

    public MenuDescriptionDto(MenuDescription menuDescription, Menu menu, List<MenuCode> menuCodes) {
        this.descriptionId = menuDescription.getDescriptionId();
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.imageUrl = menuDescription.getImageUrl();
        this.gifUrl = menuDescription.getGifUrl();
        this.iframeUrl = menuDescription.getIframeUrl();
        this.code = menuCodes.stream()
                .map(MenuCodeDto::new)
                .collect(Collectors.toList());

        this.menuDescription = menuDescription.getMenuDescription();
    }
}

