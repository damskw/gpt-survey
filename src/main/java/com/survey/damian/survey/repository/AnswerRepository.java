package com.survey.damian.survey.repository;

import com.survey.damian.survey.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
