$(function () {
	
	var cp = '';
	
	var errorUrl = cp + '/cm/internalError';
    var unauthenticatedUrl = cp + '/cm/login';
    var unauthorizedUrl = cp + '/cm/unauthorized';
    var notExistedUrl = cp + '/cm/notExistedResource';
    var notOkUrl = cp + '/cm/requestError';
    
    // Upload news image
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
        	var imageUrl = cp + data.result.path;
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
	        percent.text("Đang upload " + progress + "%");
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
    
    // Upload administrative procedure files
    $('#administrativeProcedureFileUpload').fileupload({
    	add: function(e, data) {
            var uploadErrors = [];
            if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 50000000) {
                uploadErrors.push('Kích thước file quá lớn (<= 50MB)');
            }
            if(uploadErrors.length > 0) {
                alert(uploadErrors.join("\n"));
            } else {
                data.submit();
            }
    	},
        dataType: 'json',
        
        done: function (e, data) {
        	var result = data.result;
        	for (var j = 0; j < result.length; j++) {
        		var fileId = result[j].id;
            	var fileName = result[j].fileName;
            	$('#uploadedAdministrativeProcedureFiles').append('<option value="' + fileId + '">' + fileName + '</option>');
        	}
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + "%");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(5000).fadeOut();
	        }
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {
    });
    
    $('#removeAdministrativeProcedureFile').click(function(event) {
    	event.preventDefault();
    	var _fileIds = $("#uploadedAdministrativeProcedureFiles option:selected").map(function(){
            return $(this).val();
        }).get();
    	if (_fileIds == null || _fileIds.length == 0) {
    		alert('Chưa chọn file cần xóa');
    		return;
    	}
    	if (!confirm('Bạn có chắc chắn muốn xóa file ?')) {
    		return;
    	}
    	url = cp + '/cm/administrativeProcedure/removeFiles';
    	$.ajax({
	        type : "POST",
	        url : url,
	        data : {
                fileIds: _fileIds.toString()
            },
	        dataType: "json",
	        success : function(response) {
	        	var statusCode = response.code;
	        	switch (statusCode) {
	        	case 1:
	        		$("#uploadedAdministrativeProcedureFiles option:selected").remove();
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
    
    // Upload document files
    $('#documentFileUpload').fileupload({
    	add: function(e, data) {
            var uploadErrors = [];
            if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 50000000) {
                uploadErrors.push('Kích thước file quá lớn (<= 50MB)');
            }
            if(uploadErrors.length > 0) {
                alert(uploadErrors.join("\n"));
            } else {
                data.submit();
            }
    	},
        dataType: 'json',
        
        done: function (e, data) {
        	var result = data.result;
        	for (var j = 0; j < result.length; j++) {
        		var fileId = result[j].id;
            	var fileName = result[j].fileName;
            	$('#uploadedDocumentFiles').append('<option value="' + fileId + '">' + fileName + '</option>');
        	}
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + "%");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(5000).fadeOut();
	        }
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {
    });
    
    $('#removeDocumentFile').click(function(event) {
    	event.preventDefault();
    	var _fileIds = $("#uploadedDocumentFiles option:selected").map(function(){
            return $(this).val();
        }).get();
    	if (_fileIds == null || _fileIds.length == 0) {
    		alert('Chưa chọn file cần xóa');
    		return;
    	}
    	if (!confirm('Bạn có chắc chắn muốn xóa file ?')) {
    		return;
    	}
    	url = cp + '/cm/document/removeFiles';
    	$.ajax({
	        type : "POST",
	        url : url,
	        data : {
                fileIds: _fileIds.toString()
            },
	        dataType: "json",
	        success : function(response) {
	        	var statusCode = response.code;
	        	switch (statusCode) {
	        	case 1:
	        		$("#uploadedDocumentFiles option:selected").remove();
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
    
    // Upload image
    $('#imageUpload').fileupload({
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
        	$("#removeImageIcon").empty();
        	var imageUrl = cp + data.result.path;
        	var removeIcon = cp + '/resources/images/remove.png';
        	var append1 = '<a href="' + imageUrl + '" id="thumbImage"><img src="' + imageUrl + '" alt="Ảnh đại diện" style="max-height: 100%; max-width: 100%;" class="thumb" /></a>';
        	var append2 = '<a href="javascript:void(0);" id="removeNewsImage"><img src="' + removeIcon + '" alt="Xóa ảnh" style="width: 20px; height: 20px" /></a>';
        	$("#uploadedImage").append(append1);
        	$("#removeImageIcon").append(append2);
        	$("a#thumbImage").fancybox({
        		'overlayShow'	: false,
				'transitionIn'	: 'elastic',
				'transitionOut'	: 'elastic'
        	});
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + "%");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(5000).fadeOut();
	        }
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {
    });
    
    $("#removeImageIcon").on('click', $("#removeImage"), function() {
    	if (!confirm('Bạn có chắc chắn muốn xóa ảnh ?')) {
			return;
		}
		url = cp + '/cm/image/remove';
		$.ajax({
	        type : "POST",
	        url : url,
	        dataType: "json",
	        success : function(response) {
	        	var statusCode = response.code;
	        	switch (statusCode) {
	        	case 1:
	        		$("#uploadedImage").empty();
	        		$("#removeImageIcon").empty();
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
    
    // Upload video
    $('#videoUpload').fileupload({
    	add: function(e, data) {
            var uploadErrors = [];
            var acceptFileTypes = /.(avi|AVI|wmv|WMV|flv|FLV|mpg|MPG|mp4|MP4|mpeg|MPEG|mp3|MP3|wav|WAV|wma|WMA)$/i;
            ///\.(webm|mkv|flv|vob|ogv|ogg|drc|mng|avi|mov|qt|wmv|yuv|rm|rmvb|asf|mp4|m4p|m4v|mpg|mp2|mpeg|mpe|mpv|m2v|svi|3gp|3g2|mxf|roq|nsv|swf|aif|iff|m3u|m4a|mid|mp3|mpa|ra|wav|wma)$/i;
            if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
                uploadErrors.push('Chỉ cho phép upload file định dạng video hoặc audio');
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
        	$("#uploadedVideo").empty();
        	$("#removeVideoIcon").empty();
        	var videoUrl = cp + data.result.path;
        	var removeIcon = cp + '/resources/images/remove.png';
        	var append1 = buildEmbedScript(videoUrl);
        	var append2 = '<a href="javascript:void(0);" id="removeVideo"><img src="' + removeIcon + '" alt="Xóa video" style="width: 20px; height: 20px" /></a>';
        	$("#uploadedVideo").append(append1);
        	$("#removeVideoIcon").append(append2);
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + "%");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(5000).fadeOut();
	        }
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {
    });
    
    function buildEmbedScript(videoUrl) {
    	var s = '<script type="text/javascript"> jwplayer("uploadedVideo").setup({file: "' + videoUrl + '"});</script>';
    	return s;
    }
    
    $("#removeVideoIcon").on('click', $("#removeVideo"), function() {
    	if (!confirm('Bạn có chắc chắn muốn xóa video ?')) {
			return;
		}
		url = cp + '/cm/video/remove';
		$.ajax({
	        type : "POST",
	        url : url,
	        dataType: "json",
	        success : function(response) {
	        	var statusCode = response.code;
	        	switch (statusCode) {
	        	case 1:
	        		$("#uploadedVideo").empty();
	        		$("#removeVideoIcon").empty();
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