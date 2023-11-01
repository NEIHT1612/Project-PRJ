<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section id="product">
    <div class="row">
        <div class="col-md-2 mb-sm-5 ">
            <c:forEach var="category" items="${lCategory}"> 
                <li class="list-group-item">
                    <a href="home?action=category&categoryId=${category.id}">${category.name}</a>
                </li>
            </c:forEach>
        </div>

        <div class="col-md-10 product-details">
            <form action="quiz" method="post">
                <div class="container" style="display: flex; flex-direction: column; align-items: flex-start; justify-content: flex-end; min-height: 100vh;">
                    <div class="row">
                        <table>
                            <tbody>
                                <c:forEach var="question" items="${lQuestion}" varStatus="loop">
                                    <tr>
                                        <td>Question ${loop.index + 1}: ${question.question_title}</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="hidden" name="questionId" value="${question.question_id}"/>
                                            <ul>
                                                <input type="radio" name="yourchoice${question.question_id}" value="${question.choice1}" /> ${question.choice1}
                                                <br/>
                                                <input type="radio" name="yourchoice${question.question_id}" value="${question.choice2}" /> ${question.choice2}
                                                <br/>
                                                <input type="radio" name="yourchoice${question.question_id}" value="${question.choice3}" /> ${question.choice3}
                                                <br/>
                                                <input type="radio" name="yourchoice${question.question_id}" value="${question.choice4}" /> ${question.choice4}
                                                <br/>
                                                <br/>
                                            </ul>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <input type="submit" value="SUBMIT" style="margin: 0 auto; display: block;" />
                <div class="container h1-container" style="display: flex; justify-content: center; align-items: center; height: 20vh;">
                    <h1>You have scored ${count1}/100</h1>
                </div>
            </form>
        </div>
    </div>
</section>
