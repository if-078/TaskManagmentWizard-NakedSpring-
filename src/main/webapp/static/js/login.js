   $(function () {
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
                       url: "/login",
                       type: "POST",
                       data: JSON.stringify(loginData),
                       contentType: "application/json; charset=utf-8",
                       dataType: "json",
                       success: function (data, textStatus, jqXHR) {
                           setJwtToken(data.token);                          
                       },
                       error: function (jqXHR, textStatus, errorThrown) {
                           if (jqXHR.status === 403) { 
                               window.history.replace();
                           } else {
                               throw new Error("an unexpected error occured: " + errorThrown);
                           }
                       }
                   });
               }

           });
