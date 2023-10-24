<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : dataExample
    Created on : Oct 18, 2023, 12:48:11 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Data Table Example
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Image</th>
                        <th>Questions</th> 
                        <th>Action</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Title</th>
                        <th>Image</th>
                        <th>Questions</th>   
                        <th>Action</th>

                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach var="quiz" items="${lQuiz}">
                        <tr>
                            <!--Title-->
                            <td>${quiz.title}</td>
                            <!--Image-->
                            <td>
                                <img width="50px" height="70px" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." class="card-img-top">
                            </td>
                            <!--Questions-->
                            <td>${quiz.questions}</td>
                            <td>
                            <!--Edit-->
                                <i class="fa fa-edit fa-2x" 
                                   style="color: #469408" 
                                   data-toggle="modal" 
                                   data-target="#editQuizModal"
                                   onclick="editQuizModal(
                                           ${quiz.id},
                                           `${quiz.title}`,
                                           ${quiz.questions},
                                           `${quiz.image}`,
                                           ${quiz.category_id})">
                                       
                                </i>
                                &nbsp;&nbsp;&nbsp;
                                <!--Delete-->
                                <i class="fa fa-trash fa-2x" 
                                   style="color: #e70808" 
                                   data-toggle="modal" 
                                   data-target="#delete-modal" 
                                   onclick="deleteQuizModal(${quiz.id})">
                                </i>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
</div>
