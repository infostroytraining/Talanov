"use strict";

var formData;
$('input[type=file]').change(function () {
    formData = new FormData();
    formData.append("image", this.files[0]);
});

$('#register').on('click', function () {
    var data = {
        firstName: $('#first_name').val(),
        lastName: $('#last_name').val(),
        email: $('#email').val(),
        password: $('#password').val(),
        captcha: $('#captcha').val()
    };

    if (validate(data)) {
        $(".progress").css("display", "block");
        RegistrationService.register(data, (err, result)=> {
            "use strict";
            if (err === undefined) {
                if (result.status === "Ok") {
                    UploadFile.upload(formData, (err, result)=> {
                        if (err === undefined) {
                            $(".progress").css("display", "none");
                        } else {
                            $(".progress").css("display", "none");
                        }
                    });
                } else {
                    $(".progress").css("display", "none");
                    alert(result.message);
                }
            }
        });
    }
});

var abc = false;
function validate(data) {
    var status =  true;
    abc = true;
    var firstName = data.firstName;
    var lastName = data.lastName;
    var email = data.email;
    var password = data.password;
    var captcha = data.captcha;

    if (firstName.search(/^[a-zA-Zа-яА-Я]{3,20}$/) === -1) {
        $('#fist_name_error').html(`<a>First Name</a><span class="valid-error"> Help me!</span>`);
        status = false;
    }
    if (lastName.search(/^[a-zA-Zа-яА-Я]{3,20}$/) === -1) {
        $('#last_name_error').html(`<a>Last Name</a><span class="valid-error"> Help me!</span>`);
        status = false;
    }
    if(email.search(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,6})+$/) === -1){
        $('#email_error').html(`<a>Email</a><span class="valid-error"> Help me!</span>`);
        status = false;
    }
    if(password.search(/^[a-zA-Z0-9]{6,18}$/) === -1){
        $('#password_error').html(`<a>Password</a><span class="valid-error"> Help me!</span>`);
        status = false;
    }
    if(captcha.length === 0){
        $('#captcha_error').html(`<a>Captcha</a><span class="valid-error"> Empty!</span>`);
        status = false;
    }

    return status;
}

$('.validate').on("keydown", function(){
    if(abc) {
        var firstName = $('#first_name').val();
        var lastName = $('#last_name').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var captcha = $('#captcha').val();

        if (firstName.search(/^[a-zA-Zа-яА-Я]{3,20}$/) !== -1) {
            $('#fist_name_error').html(`<a>First Name</a><span class="valid-success"> Success!</span>`);
        }
        if (lastName.search(/^[a-zA-Zа-яА-Я]{3,20}$/) !== -1) {
            $('#last_name_error').html(`<a>Last Name</a><span class="valid-success"> Success!</span>`);
        }
        if (email.search(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,6})+$/) !== -1) {
            $('#email_error').html(`<a>Email</a><span class="valid-success"> Success!</span>`);
        }
        if (password.search(/^[a-zA-Z0-9]{6,18}$/) !== -1) {
            $('#password_error').html(`<a>Password</a><span class="valid-success"> Success!</span>`);
        }
        if (captcha.length !== 0) {
            $('#captcha_error').html(`<a>Captcha</a><span class="valid-success"> Success!</span>`);
        }
    }
});

