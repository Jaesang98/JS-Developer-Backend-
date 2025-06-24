package com.jsnam.JSDEV.dictionary.repository;

import com.jsnam.JSDEV.dictionary.dto.DictListDto;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictListRepository extends JpaRepository<DictList, String> {
    @Query("""
    SELECT d
    FROM DictList d
    WHERE (d.dictTitle LIKE %:search% OR d.dictDescription LIKE %:search%)
      AND d.deleteYn = 'N'
    ORDER BY d.updated ASC
    """)
    List<DictList> findByList(@Param("search") String search);

    Optional<DictList> findById(String id);
//
//    Optional<DictList> findByDictTitle(String dictTitle);
//
//    Optional<DictList> findByDictTitleAndDeleteYn(String dictTitle, String deleteYn);
}


