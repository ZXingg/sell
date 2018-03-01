<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
<#include "../common/tip.ftl">
<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>类目id</th>
                            <th>名字</th>
                            <th>type</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list categoryPage.content as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${category.createTime}</td>
                            <td>${category.updateTime}</td>
                            <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
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
                            <a href="/sell/seller/order/list?page=${currentPage-1}&size=${categoryPage.getSize()}">上一页</a>
                        </li>
                    </#if>

                    <#list 1..categoryPage.getTotalPages() as index>
                        <#if currentPage==index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${index}&size=${categoryPage.getSize()}">${index}</a>
                            </li>
                        </#if>
                    </#list>

                    <#if currentPage gte categoryPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage+1}&size=${categoryPage.getSize()}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>