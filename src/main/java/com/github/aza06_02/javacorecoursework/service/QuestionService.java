package com.github.aza06_02.javacorecoursework.service;


import com.github.aza06_02.javacorecoursework.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Question add(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
