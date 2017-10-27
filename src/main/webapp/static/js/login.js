   $(function () {
               $("#loginform").submit(function (event) {
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
                          var token= jqXHR.getResponseHeader('Authentication');
                          window.sessionStorage.setItem("token",token);
                          window.sessionStorage.setItem("user_id",data.id);
                          window.location.href="index"
                       },
                       error: function (jqXHR, textStatus, errorThrown) {
                           if (jqXHR.status === 403) { 
                                $("#error").show().html("Bad user credentials.");
                           } else {
                           }
                       }
                   });
               }

           });
