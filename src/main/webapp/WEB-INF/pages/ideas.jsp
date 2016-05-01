<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="ideaList.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="ideaList.heading"/></h2>

<form method="get" action="${ctx}/ideas" id="searchForm" class="form-inline">
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

<p><fmt:message key="ideaList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/ideaform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message
            key="button.done"/></a>
</div>

<display:table name="ideaList" class="table table-condensed table-striped table-hover" requestURI="" id="ideaList"
               export="true" pagesize="25">
    <display:column property="ididea" sortable="true" href="ideaform" media="html"
                    paramId="ididea" paramProperty="ididea" titleKey="idea.ididea"/>
    <display:column property="ididea" media="csv excel xml pdf" titleKey="idea.ididea"/>
    <display:column property="description" sortable="true" titleKey="idea.description"/>
    <display:column property="title" sortable="true" titleKey="idea.title"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="ideaList.idea"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="ideaList.ideas"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="ideaList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="ideaList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="ideaList.title"/>.pdf</display:setProperty>
</display:table>
