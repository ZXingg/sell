<html lang="zh-CN">
<#include "../common/header.ftl"/>
<body>

<div id="wrapper" class="toggled">
<#include "../common/tip.ftl">
<#--左边栏-->
<#include "../common/nav.ftl">
<#--内容区-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderStatusMsg()}</td>
                            <td>${orderDTO.getPayStatusMsg()}</td>
                            <td>${orderDTO.createTime}</td>
                            <td><a target="_blank" href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                            </td>
                            <#if orderDTO.getOrderStatusMsg() == "新订单">
                                <td><a name="${orderDTO.orderId}" onclick="cancel(this.name)">取消</a></td>
                            <#else >
                                <td><a style="color: lightgray">取消</a></td>
                            </#if>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else >
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage-1}&size=${orderDTOPage.getSize()}">上一页</a>
                        </li>
                    </#if>

                    <#list 1..orderDTOPage.getTotalPages() as index>
                        <#if currentPage==index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${index}&size=${orderDTOPage.getSize()}">${index}</a>
                            </li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage+1}&size=${orderDTOPage.getSize()}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>



<script type="text/javascript">
    function cancel(orderId) {
        ajax("/sell/seller/order/cancel?orderId=" + orderId);
    }
</script>
</body>
</html>