/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teststore.dal.impl;

import com.teststore.dal.GenericDAO;
import com.teststore.entity.Category;
import java.util.List;

/**
 *
 * @author PC
 */
public class CategoryDAO extends GenericDAO<Category>{

    @Override
    public List<Category> findAll() {
        return queryGenericDAO(Category.class);
    }
    
    public static void main(String[] args) {
        for (Category category : new CategoryDAO().findAll()) {
            System.out.println(category);
        }
    }

    @Override
    public int insert(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
