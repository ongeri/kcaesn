<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="milestoneList.title"/></title>
    <meta name="menu" content="MilestoneMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="milestoneList.heading"/></h2>

<form method="get" action="${ctx}/milestones" id="searchForm" class="form-inline">
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

<p><fmt:message key="milestoneList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/milestoneform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="milestoneList" class="table table-condensed table-striped table-hover" requestURI="" id="milestoneList" export="true" pagesize="25">
    <display:column property="idmilestone" sortable="true" href="milestoneform" media="html"
        paramId="idmilestone" paramProperty="idmilestone" titleKey="milestone.idmilestone"/>
    <display:column property="idmilestone" media="csv excel xml pdf" titleKey="milestone.idmilestone"/>
    <display:column property="datecreated" sortable="true" titleKey="milestone.datecreated"/>
    <display:column property="description" sortable="true" titleKey="milestone.description"/>
    <display:column property="duedate" sortable="true" titleKey="milestone.duedate"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="milestoneList.milestone"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="milestoneList.milestones"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="milestoneList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="milestoneList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="milestoneList.title"/>.pdf</display:setProperty>
</display:table>
