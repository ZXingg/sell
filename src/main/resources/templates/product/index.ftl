<html lang="zh-CN">
<#include "../common/header.ftl"/>
<body>

<div id="wrapper" class="toggled">
<#include "../common/tip.ftl">
<#--左边栏-->
<#include "../common/nav.ftl">
<#--内容区-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form id="productInfoForm" role="form">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control"
                                   value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control"
                                   value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control"
                                   value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control"
                                   value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                            <input name="productIcon" type="text" class="form-control"
                                   value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                            <#list productCategoryList as category>
                                <option value="${category.categoryType}"
                                    <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                        selected
                                    </#if>
                                >
                                ${category.categoryName}
                                </option>
                            </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        <button type="button" class="btn btn-default" onclick="save()">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    function save() {
        var data=$("#productInfoForm").serialize();
        ajax("/sell/seller/product/save","POST",data);
    }
</script>
</body>
</html>