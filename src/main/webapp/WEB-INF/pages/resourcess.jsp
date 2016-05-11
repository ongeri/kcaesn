<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="resourcesList.title"/></title>
    <meta name="menu" content="ResourcesMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="resourcesList.heading"/></h2>

<form method="get" action="${ctx}/resourcess" id="searchForm" class="form-inline">
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

<p><fmt:message key="resourcesList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/resourcesform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="resourcesList" class="table table-condensed table-striped table-hover" requestURI="" id="resourcesList" export="true" pagesize="25">
    <display:column property="idresources" sortable="true" href="resourcesform" media="html"
        paramId="idresources" paramProperty="idresources" titleKey="resources.idresources"/>
    <display:column property="idresources" media="csv excel xml pdf" titleKey="resources.idresources"/>
    <display:column property="description" sortable="true" titleKey="resources.description"/>
    <display:column property="name" sortable="true" titleKey="resources.name"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="resourcesList.resources"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="resourcesList.resourcess"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="resourcesList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="resourcesList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="resourcesList.title"/>.pdf</display:setProperty>
</display:table>
