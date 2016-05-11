<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pastprojectList.title"/></title>
    <meta name="menu" content="PastprojectMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="pastprojectList.heading"/></h2>

<form method="get" action="${ctx}/pastprojects" id="searchForm" class="form-inline">
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

<p><fmt:message key="pastprojectList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/pastprojectform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="pastprojectList" class="table table-condensed table-striped table-hover" requestURI="" id="pastprojectList" export="true" pagesize="25">
    <display:column property="idpastproject" sortable="true" href="pastprojectform" media="html"
        paramId="idpastproject" paramProperty="idpastproject" titleKey="pastproject.idpastproject"/>
    <display:column property="idpastproject" media="csv excel xml pdf" titleKey="pastproject.idpastproject"/>
    <display:column property="dateended" sortable="true" titleKey="pastproject.dateended"/>
    <display:column property="description" sortable="true" titleKey="pastproject.description"/>
    <display:column property="projectname" sortable="true" titleKey="pastproject.projectname"/>
    <display:column property="startdate" sortable="true" titleKey="pastproject.startdate"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="pastprojectList.pastproject"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="pastprojectList.pastprojects"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="pastprojectList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="pastprojectList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="pastprojectList.title"/>.pdf</display:setProperty>
</display:table>
