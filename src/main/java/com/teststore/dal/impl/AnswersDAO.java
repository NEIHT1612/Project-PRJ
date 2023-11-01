/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teststore.dal.impl;

import com.teststore.dal.GenericDAO;
import com.teststore.entity.Answers;
import java.util.List;

/**
 *
 * @author PC
 */
public class AnswersDAO extends GenericDAO<Answers>{

    @Override
    public List<Answers> findAll() {
        return queryGenericDAO(Answers.class);
    }

    @Override
    public int insert(Answers t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        for (Answers answers : new AnswersDAO().findAll()) {
            System.out.println(answers);
        }
    }
}
