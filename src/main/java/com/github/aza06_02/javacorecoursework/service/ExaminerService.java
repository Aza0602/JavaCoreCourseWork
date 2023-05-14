package com.github.aza06_02.javacorecoursework.service;


import com.github.aza06_02.javacorecoursework.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
