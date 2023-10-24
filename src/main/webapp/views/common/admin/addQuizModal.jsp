<%-- 
    Document   : addBookModal
    Created on : Oct 11, 2023, 7:46:06 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="addQuizModal" tabindex="-1" role="dialog" aria-labelledby="addQuizModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addQuizModalLabel">Add Quiz</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addQuizForm" action="dashboard?action=add" method="POST" enctype="multipart/form-data">
                    <!--Title-->
                    <div class="form-group">
                        <label for="title">Title:</label>
                        <input type="text" class="form-control" id="titleInput" name="title">
                        <div id="titleError" class="error"></div>
                    </div>
                    <!--Questions-->
                    <div class="form-group">
                        <label for="questions">Questions:</label>
                        <input type="text" class="form-control" id="questionsInput" name="questions">
                        <div id="questionsError" class="error"></div>
                    </div>
                    <!--Category-->
                    <div class="form-group">
                        <label for="category">Category: </label>
                        <div class="input-group">
                            <select class="custom-select" id="category" name="category">
                                <c:forEach items ="${lCategories}" var="category" begin="0" end="6">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button">Category</button>
                            </div>
                        </div>
                    </div>
                    <!--Image-->
                    <div class="form-group">
                        <label for="image">Image: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Upload</span>
                            </div>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="image" name="image" onchange="displayImage(this)">
                                <label class="custom-file-label" >Choose file</label>
                            </div>
                        </div>
                        <img id="previewImage" src="#" alt="Preview"
                             style="display: none; max-width: 300px; max-height: 300px;">

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="addQuizForm" onclick="validateForm()">Add</button>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm() {
        let title = $('#titleInput').val();
        let questions = $('#questionsInput').val();
        //xoá thông báo lỗi hiện tại
        $('.error').html('');
        if (title === '') {
            $('#titleError').html('Enter name quiz');
        }
        if (questions === '') {
            $('#questionsError').html('Enter number of questions');
        } else if (!$.isNumeric(questions) || parseInt(questions) < 0) {
            $('#questionsError').html('Enter number and > 0');
        }
        // Kiểm tra nếu không có lỗi thì submit form
        let error = '';
        $('.error').each(function () {
            error += $(this).html();
        });
        if (error === '') {
            $('#addProductForm').submit();
        } else {
            event.preventDefault();
        }
    }
    function displayImage(input) {
        var previewImage = document.getElementById("previewImage");
        var file = input.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        }
        reader.readAsDataURL(file);
    }
</script>
