package com.survey.damian.survey.repository;

import com.survey.damian.survey.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("SELECT s FROM Subcategory s JOIN s.category c WHERE c.id=:categoryId")
    List<Subcategory> findAllByCategoryId(Long categoryId);
}
