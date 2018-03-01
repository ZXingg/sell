<html lang="zh-CN">
<#include "../common/header.ftl"/>
<body>
<div id="wrapper" class="toggled">
<#include "../common/tip.ftl">
<#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
            <#if error?? >
                <h3 style="color:red;text-align: center">${error}</h3>
            </#if>
            <#--orderDTO不为null-->
            <#if orderDTO?? >

                <div class="col-md-4 column">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list orderDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-4 column">
                    <#if orderDTO.getOrderStatusMsg() == "新订单">
                        <button name="${orderDTO.orderId}" onclick="finish(this.name)" type="button"
                                class="btn btn-default btn-primary">完结订单
                        </button>
                        <button name="${orderDTO.orderId}" onclick="cancel(this.name)" type="button"
                                class="btn btn-default btn-danger">取消订单
                        </button>
                    </#if>
                </div>

            </#if>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function cancel(orderId) {
        ajax("/sell/seller/order/cancel?orderId=" + orderId);
    }
    function finish(orderId) {
        ajax("/sell/seller/order/finish?orderId=" + orderId);
    }
</script>
</body>
</html>