/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teststore.dal.impl;

import com.teststore.dal.GenericDAO;
import com.teststore.entity.Questions;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author PC
 */
public class QuestionsDAO extends GenericDAO<Questions> {

    @Override
    public List<Questions> findAll() {
        return queryGenericDAO(Questions.class);
    }

    @Override
    public int insert(Questions t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        for (Questions questions : new QuestionsDAO().findByQuiz(1)) {
            System.out.println(questions);
        }
    }

    public List<Questions> findByQuiz(int id) {
        String sql = "select * from Questions\n"
                + "where quiz_id = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("quiz_id", id);
        return queryGenericDAO(Questions.class, sql, parameterMap);
    }

    public List<Questions> getCorrectAnswer(int questionId) {
        String sql = "select *\n"
                + "from Questions\n"
                + "where question_id = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("questionId", questionId);
        return queryGenericDAO(Questions.class, sql, parameterMap);
    }

}
