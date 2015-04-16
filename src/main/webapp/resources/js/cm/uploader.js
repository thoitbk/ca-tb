$(function () {
	
	var ct = '';
	
    $('#newsImageUpload').fileupload({
    	add: function(e, data) {
            var uploadErrors = [];
            var acceptFileTypes = /^image\/(gif|jpe?g|png|bmp|ico|icon)$/i;
            if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
                uploadErrors.push('Chỉ cho phép upload file định dạng ảnh');
            }
            if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 50000000) {
                uploadErrors.push('Kích thước file quá lớn (<= 50MB)');
            }
            if(uploadErrors.length > 0) {
                alert(uploadErrors.join("\n"));
            } else {
                data.submit();
            }
    	},
    	sequentialUploads: true,
        dataType: 'json',
        
        done: function (e, data) {
        	$("#imageBox").empty();
        	var imageUrl = ct + '/' + data.result.path;
        	var appendString = '<div id="uploadedImage" style="height: 50px; vertical-align: middle; text-align: left;">' +
        							'<a href="' + imageUrl + '">' + 
							   			'<img src="' + imageUrl + '" alt="Ảnh đại diện" style="max-height: 100%; max-width: 100%;" />' +
							   		'</a>' + 
							   '</div>';
        	$("#imageBox").append(appendString);
        	$('#uploadedImage').gallerie();
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + " %");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(2000).fadeOut();
	        }
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {
    });
});