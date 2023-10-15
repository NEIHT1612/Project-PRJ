/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teststore.dal.impl;

import com.teststore.constant.Constant;
import com.teststore.dal.GenericDAO;
import com.teststore.entity.Quiz;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author PC
 */
public class QuizDAO extends GenericDAO<Quiz> {

    @Override
    public List<Quiz> findAll() {
        return queryGenericDAO(Quiz.class);
    }

    public static void main(String[] args) {
        for (Quiz quiz : new QuizDAO().getListByPage(1)) {
            System.out.println(quiz);
        }
    }

    public List<Quiz> getListQuizByTitle(String property, String keyword) {
        String sql = "select * from Quiz where " + property + " like ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("title", "%" + keyword + "%");
        return queryGenericDAO(Quiz.class, sql, parameterMap);
    }

    public List<Quiz> getListQuizByCategory(String property, String id) {
        String sql = "select * from Quiz where " + property + " = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("category_id", id);
        return queryGenericDAO(Quiz.class, sql, parameterMap);
    }

    public List<Quiz> getListByPage(int page) {
        String sql = "select * from Quiz\n"
                + "order by id\n"
                + "offset ? rows\n"
                + "fetch next ? rows only";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("offset", (page - 1) * Constant.RECORD_PER_PAGE);
        parameterMap.put("fetch next", Constant.RECORD_PER_PAGE);
        return queryGenericDAO(Quiz.class, sql, parameterMap);
    }

    public int findTotalRecord() {
        return findTotalRecordGenericDAO(Quiz.class);
    }

}
