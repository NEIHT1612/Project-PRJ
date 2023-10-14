<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : product
    Created on : Oct 11, 2023, 10:30:34 PM
    Author     : PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section id="product">
    <div class="row">
        <div class="col-md-2 mb-sm-5 ">
            <c:forEach var="category" items="${lCategory}"> 
                <li class="list-group-item btn btn-outline-light my-2 my-sm-0 ml-sm-0" onclick="submitForm(this)">
                    ${category.name}
                    <form action="home?action=category" method="POST">
                        <input type="hidden" name="id" value="${category.id}">
                    </form>
                </li>
            </c:forEach>
        </div>

        <!-- Product details -->
        <div class="col-md-10 product-details">
            <!-- First row -->
            <div class="row">

                <c:forEach var="quiz" items="${lQuiz}">

                    <!-- First product - first row -->
                    <div class="col-lg-4 mb-md-5 ">
                        <div class="card h-100">
                            <img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." class="card-img-top">
                            <div class="card-body">
                                <div class="text-center">
                                    <!--Quiz title-->
                                    <h5 class="card-title">${quiz.title}</h5>
                                    <!--Number Questions-->
                                    Questions: ${quiz.questions} 
                                </div>
                            </div>

                            <div class="card-footer  bg-transparent border-top-0">
                                <div class="text-center">
                                    <a href="#" class="btn btn-outline-dark">View option</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
</section>

<script>
    function submitForm(clickedLi) {
        // Tìm form cha của thẻ li được nhấp
        var form = clickedLi.querySelector('form');
        if (form) {
            // Submit form
            form.submit();
        }
    }
</script>