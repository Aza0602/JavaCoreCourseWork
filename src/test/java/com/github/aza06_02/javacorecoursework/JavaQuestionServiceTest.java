package com.github.aza06_02.javacorecoursework;


import com.github.aza06_02.javacorecoursework.exception.QuestionAlreadyAddedException;
import com.github.aza06_02.javacorecoursework.exception.QuestionNotFoundException;
import com.github.aza06_02.javacorecoursework.model.Question;
import com.github.aza06_02.javacorecoursework.service.QuestionService;
import com.github.aza06_02.javacorecoursework.service.impl.JavaQuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;


import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add(new Question("Q1", "A1"));
        questionService.add(new Question("Q2", "A2"));
        questionService.add(new Question("Q3", "A3"));
    }

    @AfterEach
    public void afterEach() {
        Collection<Question> questions = new ArrayList<>(questionService.getAll());
        questions.forEach(questionService::remove);
    }

    @Test
    public void add1Test() {
        int beforeCount = questionService.getAll().size();
        Question expected = new Question("Q4", "A4");

        Question actual = questionService.add("Q4", "A4");

        assertThat(actual).isEqualTo(expected)
                .isIn(questionService.getAll());

        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void add1NegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add("Q1", "A1"));
    }

    @Test
    public void add2Test() {
        int beforeCount = questionService.getAll().size();
        Question expected = new Question("Q4", "A4");

        Question actual = questionService.add("Q4", "A4");

        assertThat(actual).isEqualTo(expected)
                .isIn(questionService.getAll());

        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void add2NegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(new Question("Q1", "A1")));
    }

    @Test
    public void removeTest() {
        int beforeCount = questionService.getAll().size();
        Question expected = new Question("Q2", "A2");

        Question actual = questionService.remove(new Question("Q2", "A2"));

        assertThat(actual).isEqualTo(expected)
                .isNotIn(questionService.getAll());

        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q4", "A4")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q3", "A3")
                );

    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }

}
