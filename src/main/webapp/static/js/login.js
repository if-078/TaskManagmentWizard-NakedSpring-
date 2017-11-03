   $(function(){
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="email"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    function doLogin(loginData) {
            $.ajax({
                url: "/login",
                type: "POST",
                data: loginData,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    var token = jqXHR.getResponseHeader('Authentication');
                    window.localStorage.setItem("token", token);
                    window.localStorage.setItem("userId", data.id);
                    $("#main").show();
                    $("#login").show();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 401) {
                        $('#loginErrorModal')
                            .modal("show")
                            .find(".modal-body")
                            .empty()
                            .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                    } else {
                        throw new Error("an unexpected error occured: " + errorThrown);
                    }
                }
            });
        }

       }