package com.jsnam.JSDEV.DictList.repository;

import com.jsnam.JSDEV.DictList.entity.DictList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictListRepository extends JpaRepository<DictList, String> {
    @Query("SELECT d FROM DictList d WHERE d.dictTitle LIKE %:dictTitle% AND d.deleteYn = 'N'")
    List<DictList> findByList(@Param("dictTitle") String dictTitle);

    Optional<DictList> findByDictId(String dictId);

    Optional<DictList> findByDictTitle(String dictTitle);
}


