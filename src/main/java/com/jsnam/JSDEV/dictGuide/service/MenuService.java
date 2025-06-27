package com.jsnam.JSDEV.dictGuide.service;

import com.jsnam.JSDEV.dictGuide.dto.MenuDescriptionDto;
import com.jsnam.JSDEV.dictGuide.dto.MenuDto;
import com.jsnam.JSDEV.dictGuide.reposity.MenuDescriptionRepository;
import com.jsnam.JSDEV.dictGuide.reposity.MenuRepository;
import com.jsnam.JSDEV.dictionary.dto.DictListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuDescriptionRepository menuDescriptionRepository;

    // 메뉴 전부 가져옴
    public List<MenuDto> getMenuAll () {
        return menuRepository.findAll().stream()
                .map(entity -> new MenuDto(entity))
                .collect(Collectors.toList());
    }

    // 메뉴 하나만 가져옴
    public List<MenuDto> getMenu (String parentId) {
        return menuRepository.findByMenu(parentId).stream()
                .map(entity -> new MenuDto(entity))
                .collect(Collectors.toList());
    }

    // 메뉴 설명
    public List<MenuDescriptionDto> getMenuDescription (String parentId) {
        return menuDescriptionRepository.findByDescription(parentId).stream()
                .map(entity -> new MenuDescriptionDto(entity, entity.getMenu(), entity.getCodes()))
                .collect(Collectors.toList());
    }
}
