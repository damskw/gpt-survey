package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.OpenAiRequest;
import com.survey.damian.survey.controller.dto.SurveyAnalysis;
import com.survey.damian.survey.controller.dto.SurveyDto;
import com.survey.damian.survey.entity.Answer;
import com.survey.damian.survey.entity.Question;
import com.survey.damian.survey.entity.Subcategory;
import com.survey.damian.survey.entity.Survey;
import com.survey.damian.survey.exception.OpenAiServiceException;
import com.survey.damian.survey.exception.SubcategoryNotFoundException;
import com.survey.damian.survey.exception.SurveyNotFoundException;
import com.survey.damian.survey.mapper.QuestionMapper;
import com.survey.damian.survey.mapper.SurveyMapper;
import com.survey.damian.survey.repository.SubcategoryRepository;
import com.survey.damian.survey.repository.SurveyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SurveyService {

    private final GPTService gptService;
    private final SurveyRepository surveyRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final SurveyMapper surveyMapper;
    private final QuestionMapper questionMapper;


    public SurveyDto createNewSurvey(SurveyDto dto) {
        var survey = saveSurvey(dto);
        return surveyMapper.mapToDto(survey);
    }

    public SurveyAnalysis analyseSurvey(Long id) {
        var survey = getSurvey(id);
        var prompt = generateSurveyAnalysisPrompt(survey);
        var responseContent = sendSurveyAnalysisRequest(prompt);
        return createSurveyAnalysis(survey, responseContent);
    }

    public List<SurveyDto> getAllSurveys() {
        return surveyRepository.findAll().stream()
                .map(surveyMapper::mapToDto)
                .toList();
    }

    public SurveyDto getSingleSurvey(Long id) {
        return surveyRepository.findById(id)
                .map(surveyMapper::mapToDto)
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }

    public Survey saveSurvey(SurveyDto dto) {
        var survey = surveyMapper.mapToEntity(dto);
        var subcategories = mapSubcategories(dto);
        var questions = generateQuestions(subcategories);
        questions.forEach(q -> q.setSurvey(survey));
        survey.setQuestions(questions);
        survey.setSubcategories(subcategories);
        return surveyRepository.save(survey);
    }

    private Survey getSurvey(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(surveyId));
    }

    private List<Question> generateQuestions(List<Subcategory> subcategories) {
        var categoriesPrompt = generateSubcategories(subcategories);
        var promptMessages = generateQuestionPrompt(categoriesPrompt);
        var request = new OpenAiRequest("gpt-3.5-turbo", promptMessages, subcategories.size());
        var response = gptService.sendRequest(request).block();
        if (Objects.isNull(response)) {
            throw new OpenAiServiceException("Failed to generate questions from GPT service.");
        }
        return questionMapper.mapFromOpenAiResponse(response);
    }

    private List<Subcategory> mapSubcategories(SurveyDto dto) {
        return dto.subcategories().stream()
                .map(s -> subcategoryRepository.findById(s.id())
                        .orElseThrow(() -> new SubcategoryNotFoundException(s.id())))
                .toList();
    }

    private List<OpenAiRequest.Message> generateSurveyAnalysisPrompt(Survey survey) {
        var questions = extractQuestions(survey);
        var answers = extractAnswers(survey);
        var promptMessage = generateAnalysisPrompt(questions, answers);
        return List.of(new OpenAiRequest.Message("system", promptMessage));
    }

    private String extractQuestions(Survey survey) {
        return survey.getQuestions().stream()
                .map(Question::getText)
                .collect(Collectors.joining(", "));
    }

    private String extractAnswers(Survey survey) {
        return survey.getQuestions().stream()
                .flatMap(q -> q.getAnswers().stream()
                        .map(Answer::getText))
                .collect(Collectors.joining(", "));
    }

    private String generateAnalysisPrompt(String questions, String answers) {
        return "Analise this survey for me with following questions: " + questions
                + " and following answers: " + answers;
    }

    private List<OpenAiRequest.Message> generateQuestionPrompt(String categoriesPrompt) {
        return List.of(new OpenAiRequest.Message("system", "Generate a question related to categories: " + categoriesPrompt));
    }

    private String generateSubcategories(List<Subcategory> subcategories) {
        return subcategories.stream()
                .map(Subcategory::getName)
                .collect(Collectors.joining(", "));
    }

    private String sendSurveyAnalysisRequest(List<OpenAiRequest.Message> prompt) {
        var request = new OpenAiRequest("gpt-3.5-turbo", prompt, 1);
        var response = gptService.sendRequest(request).block();
        if (Objects.isNull(response)) {
            throw new OpenAiServiceException("Error occurred while processing survey analysis.");
        }
        return response.choices().get(0).message().content();
    }

    private SurveyAnalysis createSurveyAnalysis(Survey survey, String analysisResult) {
        return new SurveyAnalysis(survey.getName(), analysisResult);
    }
}

