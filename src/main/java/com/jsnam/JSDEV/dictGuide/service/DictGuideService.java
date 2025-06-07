package com.jsnam.JSDEV.dictGuide.service;

import com.jsnam.JSDEV.dictGuide.dto.MenuDto;
import com.jsnam.JSDEV.dictGuide.dto.MenuNode;
import com.jsnam.JSDEV.dictGuide.reposity.DictGuideRepository;
import com.jsnam.JSDEV.dictList.dto.DictListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DictGuideService {
    private final DictGuideRepository dictGuideRepository;

    public List<MenuDto> getMenu () {
        return dictGuideRepository.findByDeleteYn("N").stream().map(MenuDto::from).toList();
    }

    public List<MenuNode> buildMenuTree(List<MenuDto> flatList) {
        Map<String, MenuNode> nodeMap = new HashMap<>();
        List<MenuNode> rootList = new ArrayList<>();

        for (MenuDto dto : flatList) {
            nodeMap.put(dto.getMenuId(), new MenuNode(dto));
        }

        for (MenuNode node : nodeMap.values()) {
            if ("0".equals(node.getParentId())) {
                rootList.add(node); // 최상위
            } else {
                MenuNode parent = nodeMap.get(node.getParentId());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }

        return rootList;
    }
}
