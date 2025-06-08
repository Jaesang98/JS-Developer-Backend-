package com.jsnam.JSDEV.dictGuide.dto;

import com.jsnam.JSDEV.common.entity.Framework;
import com.jsnam.JSDEV.dictGuide.entity.Menu;
import com.jsnam.JSDEV.dictGuide.entity.MenuDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GuideListDto {
    private String menuDesId;
    private String menuId;
    private String menuName;
    private String imageUrl;
    private String gifUrl;
    private String iframeUrl;
    private String frameworkId;
    private String codeDescription;
    private String menuDescription;

    public static GuideListDto from(MenuDescription entity) {
        return new GuideListDto(
                entity.getMenuDesId(),
                entity.getMenu().getMenuId(),
                entity.getMenu().getMenuName(),
                entity.getImageUrl(),
                entity.getGifUrl(),
                entity.getIframeUrl(),
                entity.getFramework().getFrameworkId(),
                entity.getCodeDescription(),
                entity.getMenuDescription()
        );
    }
}

