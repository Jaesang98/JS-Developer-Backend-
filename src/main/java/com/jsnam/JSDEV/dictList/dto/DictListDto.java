//package com.jsnam.JSDEV.dictList.dto;
//
//import com.jsnam.JSDEV.dictList.entity.DictList;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@AllArgsConstructor
//public class DictListDto {
//    private String dictId;
//    private String userId;
//    private String userName;
//    private String dictTitle;
//    private String dictDescription;
//    private LocalDateTime updated;
//
//    public static DictListDto from(DictList entity) {
//        return new DictListDto(
//                entity.getDictId(),
//                entity.getMember().getUserId(),
//                entity.getMember().getUsername(),
//                entity.getDictTitle(),
//                entity.getDictDescription(),
//                entity.getUpdated()
//        );
//    }
//
//}