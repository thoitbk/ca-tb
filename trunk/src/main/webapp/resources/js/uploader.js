$(function () {
	
    $('#fileupload').fileupload({
    	add: function(e, data) {
            var uploadErrors = [];
            var acceptFileTypes = /^image\/(gif|jpe?g|png)$/i;
            if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
                uploadErrors.push('Not an accepted file type');
            }
            if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 5000000) {
                uploadErrors.push('Filesize is too big');
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
        	$("#uploaded-files").empty();
            $.each(data.result, function (index, file) {
            	
            	
                $("#uploaded-files").append(
                		$('<div/>')
                		.append(file.fileName)
                		.append("&nbsp;<a href='javascript:delDocument(" + file.id + ");'><img src='/images/deleteDocument.png' /></a>")
                		.attr('id', file.id)
                		
                		
//                		$('<tr/>')
//                		.append($('<td/>').text(file.fileName))
//                		.append($('<td/>').text(file.fileSize))
//                		.append($('<td/>').text(file.fileType))
//                		.append($('<td/>').html("<a href='upload?f="+index+"'>Click</a>"))
//                		.append($('<td/>').text("@"+file.twitter))

                		);//end $("#uploaded-files").append()
            });
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        var percent = $('#progress');
	        percent.text("Đang upload " + progress + " %");
	        if (progress == 100) {
	        	percent.empty();
	        	percent.append("<span id='uploadsuccess'>Upload thành công</span>");
	        	$("#uploadsuccess").delay(2000).fadeOut();
//	        	percent.delay(2000).fadeOut();
	        }
	       /* $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );*/
   		},
   		
		dropZone: $('#dropzone')
    }).bind('fileuploadsubmit', function (e, data) {      
    });
});