package com.jsnam.JSDEV.dictGuide.reposity;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import com.jsnam.JSDEV.dictGuide.entity.MenuDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuideListRepository extends JpaRepository<MenuDescription, String> {
    @Query("""
      SELECT m FROM MenuDescription m
      WHERE m.menu.parentId = :parentId
      ORDER BY m.menuDesId
    """)
    List<MenuDescription> findByParentIdOrderByMenuDesId(@Param("parentId") String parentId);

}


