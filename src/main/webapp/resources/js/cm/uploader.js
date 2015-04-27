$(function () {
	
	var cp = '';
	
	var errorUrl = cp + '/cm/internalError';
    var unauthenticatedUrl = cp + '/cm/login';
    var unauthorizedUrl = cp + '/cm/unauthorized';
    var notExistedUrl = cp + '/cm/notExistedResource';
    var notOkUrl = cp + '/cm/requestError';
    
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
        	$("#uploadedImage").empty();
        	$("#removeIcon").empty();
        	var imageUrl = cp + '/' + data.result.path;
        	var removeIcon = cp + '/resources/images/remove.png';
        	var append1 = '<a href="' + imageUrl + '" id="thumbImage"><img src="' + imageUrl + '" alt="Ảnh đại diện" style="max-height: 100%; max-width: 100%;" class="thumb" /></a>';
        	var append2 = '<a href="javascript:void(0);" id="removeNewsImage"><img src="' + removeIcon + '" alt="Xóa ảnh" style="width: 20px; height: 20px" /></a>';
        	$("#uploadedImage").append(append1);
        	$("#removeIcon").append(append2);
        	$("a#thumbImage").fancybox({
        		'overlayShow'	: false,
				'transitionIn'	: 'elastic',
				'transitionOut'	: 'elastic'
        	});
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + " %");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(5000).fadeOut();
	        }
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {
    });
    
    $("#removeIcon").on('click', $("#removeNewsImage"), function() {
    	if (!confirm('Bạn có chắc chắn muốn xóa ảnh ?')) {
			return;
		}
		url = cp + '/cm/news/removeNewsImage';
		$.ajax({
	        type : "POST",
	        url : url,
	        dataType: "json",
	        success : function(response) {
	        	var statusCode = response.code;
	        	switch (statusCode) {
	        	case 1:
	        		$("#uploadedImage").empty();
	        		$("#removeIcon").empty();
	        		break;
	        	case 2:
	        		window.location.href = errorUrl;
	        		break;
	        	case 3:
	        		window.location.href = unauthenticatedUrl;
	        		break;
	        	case 4:
	        		window.location.href = unauthorizedUrl;
	        		break;
	        	case 5:
	        		window.location.href = notExistedUrl;
	        		break;
	        	default:
	        		window.location.href = notOkUrl;
	        		break;
	        	}
	        }
	    });
    });
});