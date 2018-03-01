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
                    <form id="categoryForm" role="form">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                        </div>
                        <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
                        <button type="button" onclick="save()" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    function save() {
        var data=$("#categoryForm").serialize();
        ajax("/sell/seller/category/save","POST",data);
    }
</script>
</body>
</html>