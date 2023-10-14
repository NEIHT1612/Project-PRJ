<%-- 
    Document   : navigationBar
    Created on : Oct 11, 2023, 10:27:13 PM
    Author     : PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section id="navigation-bar">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light ">
                <a href="${pageContext.request.contextPath}/home" class="navbar-brand">QUIZ MATH</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
                    aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item"> <a href="#" class="nav-link">Home</a> </li>
                        <li class="nav-item"><a href="#" class="nav-link">About</a></li>
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
                                data-toggle="dropdown" aria-expanded="false">Download</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="#">All Products</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#">New Arrivals</a></li>

                            </ul>
                        </li>
                    </ul>

                    <nav class="navbar navbar-light bg-light pl-sm-0">
                        <form class="form-inline" action="home?action=search" method="post">
                            <input class="form-control mr-sm-2" 
                                   type="search" 
                                   placeholder="Search" 
                                   aria-label="Search"
                                   name="keyword">
                            <button class="btn btn-outline-success my-2 my-sm-0 ml-sm-0" type="submit">Search</button>
                        </form>
                    </nav>
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="fa-solid fa-cart-shopping"></i>Cart
                        <span class="badge bg-dark text-white mr-auto rounded-pill">0</span>
                    </button>

                    <button class="btn btn-outline-primary ml-2">Login</button>
                </div>

            </nav>
        </div>
    </section>
