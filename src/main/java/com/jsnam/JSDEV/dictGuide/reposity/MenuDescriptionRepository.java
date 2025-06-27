package com.jsnam.JSDEV.dictGuide.reposity;

import com.jsnam.JSDEV.dictGuide.entity.MenuDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuDescriptionRepository extends JpaRepository<MenuDescription, String> {
    @Query("""
      SELECT m FROM MenuDescription m
      WHERE m.menu.parentId = :parentId
      ORDER BY m.descriptionId
    """)
    List<MenuDescription> findByDescription(@Param("parentId") String parentId);

}


