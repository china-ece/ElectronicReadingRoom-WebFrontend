$(document).ready(
    function () {
        //init
        $('#detail div').hide();
        //search
        $('#search_query').keyup(function (e) {
                if (e.which == 13) {
                    if($('#search_query').val().trim().length == 0)
                        return;
                    $('#detail div').hide();
                    var progress = 0;
                    $('#sidebar').html('<div class="progress progress-striped active"><div class="bar" style="width: 0%;"></div></div>');
                    var wait = setInterval(function(){
                        progress += 20;
                        if(progress > 100){
                            progress = 100;
                            clearInterval(wait);
                        }
                        $('div.bar').css("width", progress + '%');
                    }, 500);
                    $.getJSON('/search/' + $('#search_query').val(), function(data) {
                        items = "";
                        $.each(data, function(key, val) {
                            items += '<li class="nav-header">' + key + '</li>';
                            $.each(val, function(index, element) {
                                items += '<li data=\'' + JSON.stringify(element) + '\'><a href="#">' + element.name + '</a></li>';
                            });
                        });
                        if(items.length == 0)
                            $('#sidebar').text($('#search_query').val() + ' 查无结果');
                        else{
                            $('#sidebar').html(items);
                        }
                    });
                }
            }
        );
        //show detail
        $('#sidebar li').live("click", function(){
            $('#sidebar li').removeClass('active');
            $(this).addClass("active");
            var data = jQuery.parseJSON($(this).attr('data'));
            $('#name').text(data.name);
            var html = "";
            html += "<span class='label'>资料编号："+ data.id +"</span><span class='label label-success'>资料类别："+ data.category +"</span>";
            html += "<span class='label label-warning'>工作程度："+ data.jobProgress +"</span><span class='label label-info'>行政区划："+ data.location +"</span>";
            var date = new Date();
            date.setTime(data.finishDate);
            html += "<span class='label label-important'>形成单位："+ data.department +"</span><span class='label label-inverse'>形成时间："+ date.format('yyyy-MM-dd') +"</span>";
            $('#attribute').html(html);
            getDetail(data.id);
            $('#detail div').show();
        });
        //
    }
);

Date.prototype.format = function(format) //author: meizz
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1 ? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

function getDetail(id) {
    $('table tr').remove();
    $.getJSON('/detail/' + id, function(data){
        $.each(data, function(key, val) {
            if(val.length == 0)
                $('#' + key + ' table').append('<tr><td>查无结果</td></tr>');
            else{
                $.each(val, function(index, element) {
                    $('#' + key + ' table').append('<tr onclick=readDoc(\"'+ key + '\",\"'+ element.id +'\")><td>' + element.id +'</td><td>' + element.name + '</td></tr>');
                });
            }
        });
    });
}

function readDoc(key,id) {
    $.pageslide({ direction: 'left', href: '#reader' });
}