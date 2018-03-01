<#--提示框-->
<div id="tip"></div>
<#--新订单提醒-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    新消息提醒
                </h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="javascript:document.getElementById('notice').pause();location.href='/sell/seller/order/list'" type="button" class="btn btn-primary">查看新订单</button>
            </div>
        </div>

    </div>

</div>
<#--音乐提醒-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>