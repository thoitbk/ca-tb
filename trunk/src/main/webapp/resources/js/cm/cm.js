$(document).ready(function() {
	
//	$(document).on({
//	    ajaxStart: function() { $('body').addClass("loading"); },
//	     ajaxStop: function() { $('body').removeClass("loading"); }    
//	});
	//var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	var cp = '';
    
    var errorUrl = cp + '/cm/internalError';
    var unauthenticatedUrl = cp + '/cm/login';
    var unauthorizedUrl = cp + '/cm/unauthorized';
    var notOkUrl = cp + '/cm/requestError';
    
    $('#ngay').text(getDate());
    
    // Assign and revoke role to/from user
    $("#roleId").change(function () {
    	var id = $(this).find('option:selected').val();
    	if (id < 0) {
    		window.location.href = cp + '/cm/manageUserRole';
    	} else {
    		window.location.href = cp + '/cm/manageUserRole?id=' + id;
    	}
    });
    
    $('#assignRole').click(function(event) {
    	event.preventDefault();
    	
    	var _roleId = $('#roleId').val();
    	if (_roleId < 0) {
    		alert('Chưa chọn quyền');
    		return;
    	}
    	
    	var _userIds = $("#unAssigned option:selected").map(function(){
            return $(this).val();
        }).get();
    	if (_userIds == null || _userIds.length == 0) {
    		alert('Chưa chọn người dùng cần cấp quyền');
    		return;
    	}
    	
    	postUrl = cp + '/cm/assignRoleToUser';
    	reloadUrl = cp + '/cm/manageUserRole?id=' + _roleId;
    	
    	$("body").addClass("loading");
    	
    	$.ajax({
            type : "POST",
            url : postUrl,
            dataType: "json",
            data : {
            	roleId: _roleId,
                userIds: _userIds.toString()
            },
            success : function(response) {
            	reload(response.code, reloadUrl);
            },
            error : function(response) {
            	reload(response.code, reloadUrl);
            }
        });
    });
    
    $('#revokeRole').click(function(event) {
    	event.preventDefault();
    	
    	var _roleId = $('#roleId').val();
    	if (_roleId < 0) {
    		alert('Chưa chọn quyền');
    		return;
    	}
    	
    	var _userIds = $("#assigned option:selected").map(function(){
            return $(this).val();
        }).get();
    	if (_userIds == null || _userIds.length == 0) {
    		alert('Chưa chọn người dùng cần thu hồi quyền');
    		return;
    	}
    	
    	postUrl = cp + '/cm/revokeRoleFromUser';
    	reloadUrl = cp + '/cm/manageUserRole?id=' + _roleId;
    	
    	$('body').addClass("loading");
    	
    	$.ajax({
            type : "POST",
            url : postUrl,
            dataType: "json",
            data : {
            	roleId: _roleId,
                userIds: _userIds.toString()
            },
            success : function(response) {
            	reload(response.code, reloadUrl);
            },
            error : function(response) {
            	reload(response.code, reloadUrl);
            }
        });
    });
    
    // Assign permissions to roles
    $("#role").change(function () {
    	var id = $(this).find('option:selected').val();
    	if (id < 0) {
    		window.location.href = cp + '/cm/showPermission';
    	} else {
    		window.location.href = cp + '/cm/showPermission?id=' + id;
    	}
    });
    
    $('#updatePerOfRole').click(function(event) {
    	event.preventDefault();
    	
    	var _roleId = $('#role').val();
    	if (_roleId < 0) {
    		alert('Chưa chọn nhóm người dùng');
    		return;
    	}
    	var _permissionIds = $(".checkbox:checked").map(function(){
            return $(this).val();
        }).get();
    	
    	if (_permissionIds == null || _permissionIds.length == 0) {
    		_permissionIds = [-100];
    	}
    	
    	postUrl = cp + '/cm/changePermission';
    	reloadUrl = cp + '/cm/showPermission?id=' + _roleId;
    	
    	if (!confirm('Bạn có chắc chắn muốn cập nhật ?')) {
            return;
        }
    	
    	$('body').addClass("loading");
    	
    	$.ajax({
            type : "POST",
            url : postUrl,
            dataType: "json",
            data : {
            	roleId: _roleId,
                permissionIds: _permissionIds.toString()
            },
            success : function(response) {
            	reload(response.code, reloadUrl);
            },
            error : function(response) {
            	reload(response.code, reloadUrl);
            }
        });
    });
    
    // Manage adding news catalog
    $("#displayLocation").change(function () {
    	var location = $(this).find('option:selected').val();
    	if (location == null || location == '') {
    		window.location.href = cp + '/cm/newsCatalog/add';
    	} else {
    		window.location.href = cp + '/cm/newsCatalog/add?location=' + location;
    	}
    });
    
    $("#parentId").change(function () {
    	var location = $('#displayLocation').val();
    	var parent = $(this).find('option:selected').val();
    	var _url = '';
    	if (location == null || location == '') {
    		if (parent == null || parent < 0) {
    			_url = cp + '/cm/newsCatalog/add';
    		} else {
    			_url = cp + '/cm/newsCatalog/add?parent=' + parent;
    		}
    	} else {
    		if (parent == null || parent < 0) {
    			_url = cp + '/cm/newsCatalog/add?location=' + location;
    		} else {
    			_url = cp + '/cm/newsCatalog/add?location=' + location + "&parent=" + parent;
    		}
    	}
		window.location.href = _url;
    });
    
    // Update news catalog
    $("#updateDisplayLocation").change(function () {
    	var location = $(this).find('option:selected').val();
    	var postUrl = cp + '/cm/newsCatalogsByLocation';
    	reloadUrl = '/cm/newsCatalog/add';
    	if (location != null && location != '') {
    		postUrl = postUrl + "?location=" + location;
    	}
    	
    	$.ajax({
            type : "POST",
            url : postUrl,
            dataType: "json",
            success : function(response) {
            	if (response.code == null) {
            		$("#updateParentId").empty().append('<option value="-1">------ Chọn danh mục cha ------</option>');
            		jQuery.each(response, function(key, val) {
                		$("#updateParentId").append('<option value="' + key + '">' + val + '</option>');
            		});
            	} else {
            		reload(response.code, reloadUrl);
            	}
            },
            error : function(response) {
            	reload(response.code, reloadUrl);
            }
        });
    });
    
    // ------------------------------- For delete functionalities ----------------------------------
    // select all checkboxs
    $('#selectAll').click(function(event) {
        if(this.checked) {
            $('.checkbox').each(function() {
                this.checked = true;
            });
        }else{
            $('.checkbox').each(function() {
                this.checked = false;
            });         
        }
    });
    
    $('#enablePassword').click(function() {
    	$('#password').attr("disabled",false);
    });
	
    // Delete position
    $("#delPosition").click(function(event){
        event.preventDefault();
        postUrl = $('#delPosition').attr('href');
        reloadUrl = cp + '/cm/position/add';
        post(postUrl, reloadUrl);
    });
    
    // Delete department
    $("#delDepartment").click(function(event){
        event.preventDefault();
        postUrl = $('#delDepartment').attr('href');
        reloadUrl = cp + '/cm/department/add';
        post(postUrl, reloadUrl);
    });
    
    // Delete user
    $("#delUser").click(function(event){
        event.preventDefault();
        postUrl = $('#delUser').attr('href');
        reloadUrl = cp + '/cm/user/add';
        post(postUrl, reloadUrl);
    });
    
    // Delete role
    $("#delRole").click(function(event){
        event.preventDefault();
        postUrl = $('#delRole').attr('href');
        reloadUrl = cp + '/cm/role/add';
        post(postUrl, reloadUrl);
    });
    
    // Delete permission
    $("#delPermission").click(function(event){
        event.preventDefault();
        postUrl = $('#delPermission').attr('href');
        reloadUrl = cp + '/cm/permission/add';
        post(postUrl, reloadUrl);
    });
    
    // Delete newsCatalog
    $("#delNewsCatalog").click(function(event){
        event.preventDefault();
        postUrl = $('#delNewsCatalog').attr('href');
        reloadUrl = cp + '/cm/newsCatalog/add';
        post(postUrl, reloadUrl);
    });
    
    // Send post request to specific url
    function post(postUrl, reloadUrl) {
        var _ids = $(".checkbox:checked").map(function(){
            return $(this).val();
        }).get();
        if (_ids.length == 0) {
            alert('Chưa chọn mục cần xóa');
            return;
        }
        if (!confirm('Bạn có chắc chắn muốn xóa ?')) {
            return;
        }
        
        $('body').addClass("loading");
        
        $.ajax({
            type : "POST",
            url : postUrl,
            dataType: "json",
            data : {
                ids: _ids.toString()
            },
            success : function(response) {
            	reload(response.code, reloadUrl);
            },
            error : function(response) {
            	reload(response.code, reloadUrl);
            }
        });
    }
    
    function reload(statusCode, reloadUrl) {
    	_reload = reloadUrl;
    	switch (statusCode) {
    	case 1:
    		_reload = reloadUrl;
    		break;
    	case 2:
    		_reload = errorUrl;
    		break;
    	case 3:
    		_reload = unauthenticatedUrl;
    		break;
    	case 4:
    		_reload = unauthorizedUrl;
    		break;
    	default:
    		_reload = notOkUrl;
    		break;
    	}
    	window.location.href = _reload;
    }
});

// Get current date
function getDate() {
        var d = new Date();
        var weekday = new Array(7);
        weekday[0]=  "Chủ nhật";
        weekday[1] = "Thứ hai";
        weekday[2] = "Thứ ba";
        weekday[3] = "Thứ tư";
        weekday[4] = "Thứ năm";
        weekday[5] = "Thứ sáu";
        weekday[6] = "Thứ bảy";

        var thu = weekday[d.getDay()];
        var ngay = d.getDate();
        var thang = d.getMonth() + 1;
        var nam = d.getFullYear();
        
        return thu + ", ngày " + ngay + " tháng " + thang + " năm " + nam;
}