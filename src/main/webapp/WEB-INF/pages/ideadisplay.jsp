<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>
        <fmt:message key="ideaDetail.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
    <meta name="heading" content="<fmt:message key='ideaDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="ideaList.idea"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">

</div>

<div class="col-sm-9">
    <c:set var="test" value="${idea.ideabody.content}"/>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div><h2>${idea.title}</h2></div>
            <div>${idea.description}</div>
        </div>
        <div class="panel-body">
            <%--<div><c:out value="idea.ideabody.content"></c:out></div>--%>
            <% out.println(StringEscapeUtils.unescapeHtml(String.valueOf(pageContext.getAttribute("test"))));%></div>
        <div class="panel-footer"></div>
    </div>
</div>