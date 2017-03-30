<#assign domain="${requestContextPath.contextPath}" />

<#include "/common/context.ftl" />

<#-- 版本号 -->
<#assign version="version=" + .now?string("yyyyMMddHHmmss")>
