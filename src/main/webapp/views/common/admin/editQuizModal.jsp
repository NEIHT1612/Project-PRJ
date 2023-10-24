<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="editQuizModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editQuizModalLabel">Edit Quiz</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editQuizForm" action="dashboard?action=edit" method="POST" enctype="multipart/form-data">
                    <!--id-->
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idEditInput" name="id">
                    </div>
                    <!--Title-->
                    <div class="form-group">
                        <label for="title">Title:</label>
                        <input type="text" class="form-control" id="titleEditInput" name="title">
                        <div id="titleEditError" class="error"></div>
                    </div>
                    <!--Questions-->
                    <div class="form-group">
                        <label for="questions">Questions:</label>
                        <input type="text" class="form-control" id="questionsEditInput" name="questions">
                        <div id="questionsEditError" class="error"></div>
                    </div>
                    <!--Category-->
                    <div class="form-group">
                        <label for="category">Category: </label>
                        <div class="input-group">
                            <select class="custom-select" id="categoryEditInput" name="category">
                                <c:forEach items="${lCategories}" var="category" begin="0" end="6">
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
                                <input type="file" class="custom-file-input" id="imageEdit" name="image"
                                       onchange="displayImage2(this)">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <img id="previewImage2" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Preview"
                             style="display: none; max-width: 300px; max-height: 300px;">
                        <input type="hidden" id="currentImage" name="currentImage" value="">
                    </div>
                    <!--Description-->
                    <div class="form-group">
                        <label for="descriptionEditInput">Description:</label>
                        <textarea class="form-control" id="descriptionEdit" name="description"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="editBookForm"
                        onclick="validateForm2()">Update</button>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm2() {
        let title = $('#titleEditInput').val();
        let questions = $('#questionsEditInput').val();

        //xoá thông báo lỗi hiện tại
        $('.error').html('');
        if (title === '') {
            $('#titleEditError').html('Enter name quiz');
        }
        if (questions === '') {
            $('#questionsEditError').html('Enter number questions');
        } else if (!$.isNumeric(questions) || parseInt(questions) < 0) {
            $('#questionsEditError').html('Enter number questions and > 0');
        }
        // Kiểm tra nếu không có lỗi thì submit form
        let error = '';
        $('.error').each(function () {
            error += $(this).html();
        });
        if (error === '') {
            $('#editQuizForm').submit();
        } else {
            event.preventDefault();
        }
    }
    function displayImage2(input) {
        var previewImage = document.getElementById("previewImage2");
        var file = input.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        }
        reader.readAsDataURL(file);
    }
    function editQuizModal(id, title, questions, image, categoryId) {
        $('#idEditInput').val(id);
        $('#titleEditInput').val(title);
        $('#questionsEditInput').val(questions);
        $('#categoryEditInput').val(categoryId);
        $('#previewImage2').attr('src', image);
        $('#previewImage2').css('display', 'block');
        $('#currentImage').val(image);
    }
</script>