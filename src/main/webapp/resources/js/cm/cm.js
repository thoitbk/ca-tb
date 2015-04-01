$(document).ready(function() {
	
//	$(document).on({
//	    ajaxStart: function() { $('body').addClass("loading"); },
//	     ajaxStop: function() { $('body').removeClass("loading"); }    
//	});
	
	var cp = '';
	
    document.getElementById("ngay").innerHTML = getDate();
    
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
                    window.location.href=reloadUrl;
            },
            error : function(e) {
                    window.location.href=reloadUrl;
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
                    window.location.href=reloadUrl;
            },
            error : function(e) {
                    window.location.href=reloadUrl;
            }
        });
    });
    
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
                    window.location.href=reloadUrl;
            },
            error : function(e) {
                    window.location.href=reloadUrl;
            }
        });
    });
    
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
    
    //var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
    $("#delPosition").click(function(event){
        event.preventDefault();
        postUrl = $('#delPosition').attr('href');
        reloadUrl = cp + '/cm/position/add';
        post(postUrl, reloadUrl);
    });
    
    $("#delDepartment").click(function(event){
        event.preventDefault();
        postUrl = $('#delDepartment').attr('href');
        reloadUrl = cp + '/cm/department/add';
        post(postUrl, reloadUrl);
    });
    
    $("#delUser").click(function(event){
        event.preventDefault();
        postUrl = $('#delUser').attr('href');
        reloadUrl = cp + '/cm/user/add';
        post(postUrl, reloadUrl);
    });
    
    $("#delRole").click(function(event){
        event.preventDefault();
        postUrl = $('#delRole').attr('href');
        reloadUrl = cp + '/cm/role/add';
        post(postUrl, reloadUrl);
    });
    
    $("#delPermission").click(function(event){
        event.preventDefault();
        postUrl = $('#delPermission').attr('href');
        reloadUrl = cp + '/cm/permission/add';
        post(postUrl, reloadUrl);
    });
    
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
                    window.location.href=reloadUrl;
            },
            error : function(e) {
                    window.location.href=reloadUrl;
            }
        });
    }
});

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