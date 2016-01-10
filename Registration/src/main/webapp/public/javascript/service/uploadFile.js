"use strict";
let UploadFile = (()=> {

    let upload = (file, func)=> {
        $.ajax({
            url: '/upload_profile_photo',
            data: file,
            cache: false,
            contentType: false,
            processData: false,
            method: 'POST',
            success: function (result) {
                func(undefined, result);
            },
            error: function (error) {
                func(error);
            }
        });
    };

    return {
        upload: upload
    }
})();