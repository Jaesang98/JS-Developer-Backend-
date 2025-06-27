package com.jsnam.JSDEV.dictGuide.dto;

import com.jsnam.JSDEV.common.entity.Framework;
import com.jsnam.JSDEV.dictGuide.entity.MenuCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCodeDto {
    private String frameworkId;
    private String frameworkName;
    private String codeDescription;
    private String code;

    public MenuCodeDto(MenuCode menuCode) {
        Framework framework = menuCode.getFramework();
        this.frameworkId = framework.getFrameworkId();
        this.frameworkName = framework.getFrameworkName();
        this.codeDescription = menuCode.getCodeDescription();
        this.code = menuCode.getCode();
    }
}
