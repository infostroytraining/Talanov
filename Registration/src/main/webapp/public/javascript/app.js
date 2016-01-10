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
                    Materialize.toast('Success!', 3000);
                    UploadFile.upload(formData, (err, result)=> {
                        if (err === undefined) {
                            $(".progress").css("display", "none");
                        } else {
                            $(".progress").css("display", "none");
                        }
                    });
                } else {
                    $(".progress").css("display", "none");
                    Materialize.toast(result.message, 3000);
                }
            }
        });
    }
});

function validate(data) {
    let status = true;
    if(data.firstName.length === 0){
        Materialize.toast('Field "First name" must be entered!', 3000);
        status = false;
    }
    if(data.lastName.length === 0){
        Materialize.toast('Field "Last name" must be entered!', 3000);
        status = false;
    }
    if(data.email.length !== 0) {
        if (!validator.isEmail(data.email)) {
            Materialize.toast('Field "email" have incorrect format !', 3000);
            status = false;
        }
    } else {
        Materialize.toast('Field "Email" must be entered!', 3000);
        status = false;
    }
    if (!validator.matches(data.password, '^[A-Za-z0-9]{6,18}$')) {
        Materialize.toast('Field "password" have incorrect format !', 3000);
        status = false;
    }
    if(data.password !== $('#passRepeat').val()){
        Materialize.toast('Fields "password" and password repeat do not coincide!', 3000);
        status = false;
    }
    if(data.captcha.length === 0) {
        Materialize.toast('Field "Captcha" must be entered!', 3000);
        status = false;
    }

    return status;
}


