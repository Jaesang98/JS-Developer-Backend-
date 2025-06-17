//package com.jsnam.JSDEV.dictGuide.service;
//
//import com.jsnam.JSDEV.dictGuide.dto.GuideCodeDto;
//import com.jsnam.JSDEV.dictGuide.dto.GuideMenuListDto;
//import com.jsnam.JSDEV.dictGuide.dto.GuideMenuDto;
//import com.jsnam.JSDEV.dictGuide.dto.GuideMenuNode;
//import com.jsnam.JSDEV.dictGuide.entity.Menu;
//import com.jsnam.JSDEV.dictGuide.entity.MenuDescription;
//import com.jsnam.JSDEV.dictGuide.reposity.MenuRepository;
//import com.jsnam.JSDEV.dictGuide.reposity.MenuDescriptionRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MenuService {
//    private final MenuRepository dictGuideRepository;
//    private final MenuDescriptionRepository menuDescriptionRepository;
//
//    // 메뉴 전부 가져옴
//    public List<GuideMenuDto> getMenu () {
//        return dictGuideRepository.findByDeleteYnOrderByMenuId("N").stream().map(GuideMenuDto::from).toList();
//    }
//
//    public List<GuideMenuNode> buildMenuTree(List<GuideMenuDto> flatList) {
//        Map<String, GuideMenuNode> nodeMap = new HashMap<>();
//        List<GuideMenuNode> rootList = new ArrayList<>();
//
//        for (GuideMenuDto dto : flatList) {
//            nodeMap.put(dto.getMenuId(), new GuideMenuNode(dto));
//        }
//
//        for (GuideMenuNode node : nodeMap.values()) {
//            if ("0".equals(node.getParentId())) {
//                rootList.add(node); // 최상위
//            } else {
//                GuideMenuNode parent = nodeMap.get(node.getParentId());
//                if (parent != null) {
//                    parent.getChildren().add(node);
//                }
//            }
//        }
//
//        for (GuideMenuNode node : nodeMap.values()) {
//            node.getChildren().sort(Comparator.comparing(GuideMenuNode::getMenuId));
//        }
//
//        rootList.sort(Comparator.comparing(GuideMenuNode::getMenuId));
//
//        return rootList;
//    }
//
//    public List<GuideMenuDto> getMenuByParentId(String parentId) {
//        List<Menu> menuList;
//
//        if (parentId == null || parentId.isBlank()) {
//            // 1뎁스: parentId = "0"
//            menuList = dictGuideRepository.findByParentIdAndDeleteYnOrderByMenuId("0", "N");
//        } else {
//            // 하위 뎁스 (2, 3 등)
//            menuList = dictGuideRepository.findByParentIdAndDeleteYnOrderByMenuId(parentId, "N");
//        }
//
//        return menuList.stream().map(GuideMenuDto::from).toList();
//    }
//
//    public List<GuideMenuListDto> getDescriptions(String parentId) {
//        List<MenuDescription> list = menuDescriptionRepository.findByParentIdOrderByMenuDesId(parentId);
//
//        return list.stream().map(desc -> {
//            List<GuideCodeDto> codes = Optional.ofNullable(desc.getCodes())
//                    .orElse(Collections.emptyList())
//                    .stream()
//                    .map(code -> new GuideCodeDto(
//                            code.getFramework().getFrameworkId(),
//                            code.getFramework().getFrameworkName(),
//                            code.getCodeDescription(),
//                            code.getCode()
//                    ))
//                    .collect(Collectors.toList());
//            return new GuideMenuListDto(
//                    desc.getMenuDesId(),
//                    desc.getMenu().getMenuId(),
//                    desc.getMenu().getMenuName(),
//                    desc.getImageUrl(),
//                    desc.getGifUrl(),
//                    desc.getIframeUrl(),
//                    codes,
//                    desc.getMenuDescription()
//            );
//        }).toList();
//    }
//
//}
