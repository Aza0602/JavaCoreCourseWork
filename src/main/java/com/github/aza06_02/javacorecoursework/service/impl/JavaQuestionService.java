package com.github.aza06_02.javacorecoursework.service.impl;


import com.github.aza06_02.javacorecoursework.model.Question;
import com.github.aza06_02.javacorecoursework.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyAddedException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return questions.stream()
                .skip(questions.isEmpty() ? 0 : random.nextInt(questions.size()))
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }
}
