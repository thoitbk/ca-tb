$(document).ready(function() {
    document.getElementById("ngay").innerHTML = getDate();
        
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
    
    var baseHref = document.getElementsByTagName('base')[0].href;
    
    $("#delPosition").click(function(event){
        event.preventDefault();
        postUrl = $('#delPosition').attr('href');
        reloadUrl = baseHref + 'cm/position/add';
        post(postUrl, reloadUrl);
    });
    
    $("#delDepartment").click(function(event){
        event.preventDefault();
        postUrl = $('#delDepartment').attr('href');
        reloadUrl = baseHref + 'cm/department/add';
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
        
        $.ajax({
            type : "POST",
            url : postUrl,
            dataType: "json",
            data : {
                ids: _ids
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