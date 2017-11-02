   $(function(){
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    function doLogin(loginData) {
            $.ajax({
                url: "/auth",
                type: "POST",
                data: JSON.stringify(loginData),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    setJwtToken(data.token);
                    $login.hide();
                    $notLoggedIn.hide();
                    showTokenInformation();
                    showUserInformation();
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