package com.jsnam.JSDEV.dictGuide.reposity;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String> {
    @Query("""
    SELECT m
    FROM Menu m
    WHERE m.parentId = :parentId AND m.deleteYn = 'N'
    """)
    List<Menu> findByMenu(@Param("parentId") String parentId);
}