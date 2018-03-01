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
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td><a target="_blank" href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.getProductStatusMsg() == "在架">
                                    <a name="${productInfo.productId}" onclick="offSale(this.name)">下架</a>
                                <#else>
                                    <a name="${productInfo.productId}" onclick="onSale(this.name)">上架</a>
                                </#if>
                            </td>
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
                            <a href="/sell/seller/product/list?page=${currentPage-1}&size=${productInfoPage.getSize()}">上一页</a>
                        </li>
                    </#if>

                    <#list 1..productInfoPage.getTotalPages() as index>
                        <#if currentPage==index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else >
                            <li>
                                <a href="/sell/seller/product/list?page=${index}&size=${productInfoPage.getSize()}">${index}</a>
                            </li>
                        </#if>
                    </#list>

                    <#if currentPage gte productInfoPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li>
                            <a href="/sell/seller/product/list?page=${currentPage+1}&size=${productInfoPage.getSize()}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    function onSale(productId) {
        ajax("/sell/seller/product/on_sale?productId=" + productId);
    }

    function offSale(productId) {
        ajax("/sell/seller/product/off_sale?productId=" + productId);
    }
</script>
</body>
</html>