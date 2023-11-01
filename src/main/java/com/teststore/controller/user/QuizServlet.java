/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.teststore.controller.user;

import com.teststore.dal.impl.AnswersDAO;
import com.teststore.dal.impl.QuestionsDAO;
import com.teststore.entity.Answers;
import com.teststore.entity.Questions;
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
public class QuizServlet extends HttpServlet {

    QuestionsDAO questionsDAO;
    AnswersDAO answersDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        questionsDAO = new QuestionsDAO();
        answersDAO = new AnswersDAO();

        int id = Integer.parseInt(request.getParameter("testId"));
        List<Questions> lQuestion = questionsDAO.findByQuiz(id);

        session.setAttribute("lQuestion", lQuestion);

        request.getRequestDispatcher("views/user/home-page/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int questionId = Integer.parseInt(request.getParameter("questionId"));
//        String yourchoice = request.getParameter("yourchoice");
//        
//        questionsDAO = new QuestionsDAO();
//        List<Questions> lQuestions = questionsDAO.getCorrectAnswer(questionId);
//        
//        int count1 = 0;
//        if(yourchoice.equalsIgnoreCase(lQuestions.get(0).getCorrect_answer())){
//            count1 +=20;
//        }
//        else count1 = 0;
//        
//        request.setAttribute("count1", count1);
//        response.sendRedirect("quiz");

        questionsDAO = new QuestionsDAO();

        // Lấy danh sách tất cả câu hỏi
        List<Questions> lQuestions = questionsDAO.findAll();  // Giả định bạn có phương thức này trong DAO

        int count1 = 0; // biến tích lũy điểm số

        // Lặp qua từng câu hỏi và kiểm tra đáp án
        for (Questions question : lQuestions) {
            String parameterName = "yourchoice" + question.getQuestion_id();
            String userChoice = request.getParameter(parameterName); // Lấy đáp án người dùng chọn cho câu hỏi đó

            if (userChoice != null && userChoice.equalsIgnoreCase(question.getCorrect_answer())) {
                count1 += 20; // Giả định mỗi câu đúng bạn cộng thêm 20 điểm
            }
        }

        request.setAttribute("count1", count1);
        request.getRequestDispatcher("views/user/home-page/test.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
