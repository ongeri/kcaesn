<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="channesList.title"/></title>
    <meta name="menu" content="ChannesMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="channesList.heading"/></h2>

<form method="get" action="${ctx}/channess" id="searchForm" class="form-inline">
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

<p><fmt:message key="channesList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/channesform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="channesList" class="table table-condensed table-striped table-hover" requestURI="" id="channesList" export="true" pagesize="25">
    <display:column property="idchannes" sortable="true" href="channesform" media="html"
        paramId="idchannes" paramProperty="idchannes" titleKey="channes.idchannes"/>
    <display:column property="idchannes" media="csv excel xml pdf" titleKey="channes.idchannes"/>
    <display:column property="description" sortable="true" titleKey="channes.description"/>
    <display:column property="name" sortable="true" titleKey="channes.name"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="channesList.channes"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="channesList.channess"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="channesList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="channesList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="channesList.title"/>.pdf</display:setProperty>
</display:table>
