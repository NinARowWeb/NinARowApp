

$(function () {
    $("#loginForm").submit(function () {
        $.ajax({
            data: $("#user-type, #username-input").val(),
            url: this.action,
            error: function(xhr, status, error){
                $("#error-name").innerHTML = xhr.responseText;
            },
            success: function(){}
        })
    })
});