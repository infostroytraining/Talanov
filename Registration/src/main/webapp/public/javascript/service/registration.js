"use strict";
let RegistrationService = (()=> {

    let register = (data, func)=> {
        $.ajax({
            url: '/registration',
            method: 'POST',
            dataType: 'json',
            data: `json=${JSON.stringify(data)}`,
            success: function (result) {
                func(undefined, result);
            },
            error: function (error) {
                func(error);
            }
        });
    };

    return {
        register: register
    }
})();

