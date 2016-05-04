<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="commentList.title"/></title>
    <meta name="menu" content="CommentMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="commentList.heading"/></h2>

<form method="get" action="${ctx}/comments" id="searchForm" class="form-inline">
<div id="search" class="text-right">
    <span class="col-sm-9">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
    </span>
    <button id="button.search" class="btn btn-default btn-sm" type="submit">
        <i class="icon-search"></i> <fmt:message key="button.search"/>
    </button>
</div>
</form>

<p><fmt:message key="commentList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/commentform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="commentList" class="table table-condensed table-striped table-hover" requestURI="" id="commentList" export="true" pagesize="25">
    <display:column property="idcomment" sortable="true" href="commentform" media="html"
        paramId="idcomment" paramProperty="idcomment" titleKey="comment.idcomment"/>
    <display:column property="idcomment" media="csv excel xml pdf" titleKey="comment.idcomment"/>
    <display:column property="commenttext" sortable="true" titleKey="comment.commenttext"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="commentList.comment"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="commentList.comments"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="commentList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="commentList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="commentList.title"/>.pdf</display:setProperty>
</display:table>
