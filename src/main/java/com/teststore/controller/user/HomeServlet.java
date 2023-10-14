/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.teststore.controller.user;

import com.teststore.dal.impl.CategoryDAO;
import com.teststore.dal.impl.QuizDAO;
import com.teststore.entity.Category;
import com.teststore.entity.Quiz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class HomeServlet extends HttpServlet {

    CategoryDAO categoryDAO;
    QuizDAO quizDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        categoryDAO = new CategoryDAO();
        quizDAO = new QuizDAO();

        HttpSession session = request.getSession();

        List<Category> lCategory = categoryDAO.findAll();
        List<Quiz> lQuiz = quizDAO.findAll();

        session.setAttribute("lCategory", lCategory);
        session.setAttribute("lQuiz", lQuiz);

        request.getRequestDispatcher("views/user/home-page/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "home";
        String action = request.getParameter("action");
        switch (action) {
            case "search":
                searchByTitle(request, response);
                url = "views/user/home-page/homePage.jsp";
                break;
            case "category":
                searchByCategory(request, response);
                url = "views/user/home-page/homePage.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void searchByTitle(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");

        quizDAO = new QuizDAO();
        List<Quiz> list = quizDAO.getListQuizByTitle("title", keyword);

        HttpSession session = request.getSession();
        session.setAttribute("lQuiz", list);
    }

    private void searchByCategory(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        quizDAO = new QuizDAO();
        List<Quiz> list;
        if (Integer.parseInt(id) != 1) {
            list = quizDAO.getListQuizByCategory("category_id", id);
        } else {
            list = quizDAO.findAll();
        }

        HttpSession session = request.getSession();
        session.setAttribute("lQuiz", list);

    }

}
