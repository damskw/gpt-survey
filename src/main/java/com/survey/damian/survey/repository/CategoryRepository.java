package com.survey.damian.survey.repository;

import com.survey.damian.survey.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
