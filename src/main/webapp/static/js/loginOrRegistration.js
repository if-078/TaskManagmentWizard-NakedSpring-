/*$.ajaxSetup({
 global:true
 });

 $(document).ajaxError(function(jqXHR){
 if(jqXHR.status===401){
 $("#main, #registration").hide();
 $("#login").show();
 }

 });*/

function resetToken() {
    window.sessionStorage.removeItem("token");
    window.location.href = "";
}

function setToken(jqXHR) {
    var token = jqXHR.getResponseHeader('Authentication');
    window.sessionStorage.setItem("token", token);

    $.ajaxSetup({
        headers: createAuthToken()
    });
}

$("#reg-button").click(function () {
    $("#login").hide();
    $("#registration").show();
});

function doRegister(regData) {
    $.ajax({
        url: "/register",
        type: "POST",
        data: JSON.stringify(regData),
        contentType: "application/json; charset=utf-8",
        headers: createAuthToken(),
        success: function (data, textStatus, jqXHR, response) {
            if (response === 201) {
                alert(response);
            }
            if (jqXHR.status == 201) {
                location.reload();
            }
        },
        error: function (jqXHR) {
            if (jqXHR.status == 409) {
                $("#existing-email").show();
            }
        }
    });
}

function doLogin(loginData) {
    $.ajax({
        url: "/login",
        type: "POST",
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=utf-8",
        headers: createAuthToken(),
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            userId = data["id"];
            userName = data['username'];
            window.sessionStorage.setItem("id", userId);
            window.sessionStorage.setItem("name", userName);
            $("#user-login").text("Hello, " + userName);
            setToken(jqXHR);
            $("#logout").show();
            $("#login").hide();
            $("#main").show();
            $("#leftPanel").load("static/load-pages/statusFilter.html");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 401) {
                window.sessionStorage.removeItem("token");
                $("#password-label").show();
            } else {
                throw new Error("an unexpected error occured: " + errorThrown);
            }
        }
    });
}

$("input").on("click", function () {
    $("#password-label").hide();
});
$("#logout").click(function () {

    $.ajax({
        url: "api/logout",
        type: "POST",
        headers: createAuthToken(),
        success: function () {
            resetToken();
            location.reload();
            $("#logout").hide()
        }
    });
});

$("#registration-form").validate({
    submitHandler: function (form) {
        $(form).on('submit', function (event) {
            event.preventDefault();
            var $form = $(this);
            var formData = {
                name: $form.find('input[name="rname"]').val(),
                pass: $form.find('input[name="rpassword"]').val(),
                email: $form.find('input[name="remail"]').val()
            };
            doRegister(formData);
        });
    },
    rules: {
        rname: {
            required: true,
            minlength: 5
        },
        remail: {
            required: true,
            email: true
        },
        rpassword: {
            required: true,
            minlength: 5
        },
        rconfirm: {
            required: true,
            minlength: 5,
            equalTo: "#reg-password"
        }

    },
    messages: {
        rname: {
            required: "Please enter your name",
            minlength: "Your name must consist of at least 5 characters"
        },
        rpassword: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
        rconfirm: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long",
            equalTo: "Please enter the same password as above"
        }
    }
});

$("#loginForm").validate(
    {
        submitHandler: function (form) {
            $(form).submit(function (event) {
                event.preventDefault();
                var $form = $(this);
                var formData = {
                    userEmail: $form.find('input[name="email"]').val(),
                    password: $form.find('input[name="password"]').val()
                };
                doLogin(formData);
            });
        }
    }
);



