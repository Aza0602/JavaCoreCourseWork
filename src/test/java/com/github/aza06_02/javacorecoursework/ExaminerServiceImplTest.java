package com.github.aza06_02.javacorecoursework;


import com.github.aza06_02.javacorecoursework.exception.MoreQuestionsAsked;
import com.github.aza06_02.javacorecoursework.model.Question;
import com.github.aza06_02.javacorecoursework.service.QuestionService;
import com.github.aza06_02.javacorecoursework.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Set<Question> questions = Set.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
    );

    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Q1", "A1"),
                new Question("Q4", "A4"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5"),
                new Question("Q5", "A5"),
                new Question("Q4", "A4"),
                new Question("Q1", "A1"),
                new Question("Q5", "A5"),
                new Question("Q2", "A2")
        );

        assertThat(examinerService.getQuestions(4))
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q4", "A4"),
                        new Question("Q5", "A5")
                );
    }

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(MoreQuestionsAsked.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));

        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(MoreQuestionsAsked.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
    }
}
