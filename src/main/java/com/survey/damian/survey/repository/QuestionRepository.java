package com.survey.damian.survey.repository;

import com.survey.damian.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q JOIN q.survey s WHERE s.id=:surveyId")
    List<Question> findAllBySurveyId(Long surveyId);
}
