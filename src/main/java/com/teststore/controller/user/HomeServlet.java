/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.teststore.controller.user;

import com.teststore.constant.Constant;
import com.teststore.dal.impl.CategoryDAO;
import com.teststore.dal.impl.QuizDAO;
import com.teststore.entity.Category;
import com.teststore.entity.PageControl;
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
    PageControl pageControl;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        categoryDAO = new CategoryDAO();
        quizDAO = new QuizDAO();
        pageControl = new PageControl();

        List<Category> lCategory = categoryDAO.findAll();
        List<Quiz> lQuiz = pagination(request, pageControl);

        session.setAttribute("lCategory", lCategory);
        session.setAttribute("lQuiz", lQuiz);
        request.setAttribute("pageControl", pageControl);

        request.getRequestDispatcher("views/user/home-page/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
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

    private List<Quiz> pagination(HttpServletRequest request, PageControl pageControl) {
        String pageRaw = request.getParameter("page");

        quizDAO = new QuizDAO();

        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }

        List<Quiz> lQuiz = null;
        int totalRecord = 0;
        String action = request.getParameter("action") == null ? "defaultFindAll" : request.getParameter("action");
        switch (action) {
            case "search":
                String keyword = request.getParameter("keyword");
                totalRecord = quizDAO.findTotalRecordByKeyWord(keyword);
                lQuiz = quizDAO.findByKeyWordAndPage(keyword, page);
                pageControl.setUrlPattern("home?action=search&keyword=" + keyword);
                break;
            case "category":
                String categoryId = request.getParameter("categoryId");
                totalRecord = quizDAO.findTotalRecordByCategory(categoryId);
                lQuiz = quizDAO.findByCategoryAndPage(categoryId, page);
                pageControl.setUrlPattern("home?action=category&categoryId=" + categoryId);
                break;
            default:
                totalRecord = quizDAO.findTotalRecord();
                lQuiz = quizDAO.getListByPage(page);
                pageControl.setUrlPattern("home?");

        }

        int totalPage = (totalRecord % Constant.RECORD_PER_PAGE) == 0 ? (totalRecord / Constant.RECORD_PER_PAGE) : ((totalRecord / Constant.RECORD_PER_PAGE) + 1);

        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);

        return lQuiz;
    }

}
