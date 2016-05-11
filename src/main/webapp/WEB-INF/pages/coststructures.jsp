<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="coststructureList.title"/></title>
    <meta name="menu" content="CoststructureMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="coststructureList.heading"/></h2>

<form method="get" action="${ctx}/coststructures" id="searchForm" class="form-inline">
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

<p><fmt:message key="coststructureList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/coststructureform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="coststructureList" class="table table-condensed table-striped table-hover" requestURI="" id="coststructureList" export="true" pagesize="25">
    <display:column property="idcoststructure" sortable="true" href="coststructureform" media="html"
        paramId="idcoststructure" paramProperty="idcoststructure" titleKey="coststructure.idcoststructure"/>
    <display:column property="idcoststructure" media="csv excel xml pdf" titleKey="coststructure.idcoststructure"/>
    <display:column property="description" sortable="true" titleKey="coststructure.description"/>
    <display:column property="name" sortable="true" titleKey="coststructure.name"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="coststructureList.coststructure"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="coststructureList.coststructures"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="coststructureList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="coststructureList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="coststructureList.title"/>.pdf</display:setProperty>
</display:table>
