/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.teststore.controller.admin;

import com.teststore.dal.impl.CategoryDAO;
import com.teststore.dal.impl.QuizDAO;
import com.teststore.entity.Category;
import com.teststore.entity.Quiz;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author PC
 */
@MultipartConfig
public class DashboardServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDAO quizDAO = new QuizDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        
        List<Quiz> lQuizs = quizDAO.findAll();
        List<Category> lCategories = categoryDAO.findAll();
        
        HttpSession session = request.getSession();
        session.setAttribute("lQuiz", lQuizs);
        session.setAttribute("lCategories", lCategories);

        //chuyen qua dashboard.jsp
        request.getRequestDispatcher("../views/admin/dashboard/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");
        switch(action){
            case "add":
                addQuiz(request);
                break;
            case "delete":
                deleteQuiz(request);
                break;
            case "edit":
                editQuiz(request);
                break;
            default:
                throw new AssertionError();
        }
        response.sendRedirect("dashboard");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addQuiz(HttpServletRequest request) {
        QuizDAO quizDAO = new QuizDAO();
        
        String title = request.getParameter("title");
        int questions = Integer.parseInt(request.getParameter("questions"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String imagePath = null;
        try {
            Part part = request.getPart("image");
            
            //duong dan luu anh 
            String path = request.getServletContext().getRealPath("/images");
            File dir = new File(path);
            //ko co duong dan => tu tao ra
            if(!dir.exists()){
                dir.mkdirs();
            }
            
            File image = new File(dir, part.getSubmittedFileName());
            part.write(image.getAbsolutePath());
            imagePath = "/Project/images/"+image.getName();
        } catch (Exception e) {
            
        }
        
        Quiz quiz = Quiz.builder()
                .title(title)
                .questions(questions)
                .category_id(categoryId)
                .image(imagePath)
                .build();
        
        quizDAO.insert(quiz);
    }

    private void deleteQuiz(HttpServletRequest request) {
        QuizDAO quizDAO = new QuizDAO();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        quizDAO.deleteById(id);
    }

    private void editQuiz(HttpServletRequest request) {
        QuizDAO quizDAO = new QuizDAO();
        Quiz quiz = new Quiz();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int questions = Integer.parseInt(request.getParameter("questions"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        
        String imagePath = null;
        //get image
        try {

            Part part = request.getPart("image");
            if (part == null || part.getSize() <= 0) {
                String currentImage = request.getParameter("currentImage");
                quiz.setImage(currentImage);
            } else {
                try {
                    String path = request.getServletContext().getRealPath("/images");
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File image = new File(dir, part.getSubmittedFileName());
                    part.write(image.getAbsolutePath());
                    imagePath = "/BookStore-FA23/images/" + image.getName();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        quiz.setId(id);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quiz.setCategory_id(categoryId);
        quiz.setImage(imagePath);
        
        quizDAO.update(quiz);
    }

}
