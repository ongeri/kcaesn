<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="partnersList.title"/></title>
    <meta name="menu" content="PartnersMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="partnersList.heading"/></h2>

<form method="get" action="${ctx}/partnerss" id="searchForm" class="form-inline">
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

<p><fmt:message key="partnersList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/partnersform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="partnersList" class="table table-condensed table-striped table-hover" requestURI="" id="partnersList" export="true" pagesize="25">
    <display:column property="idpartners" sortable="true" href="partnersform" media="html"
        paramId="idpartners" paramProperty="idpartners" titleKey="partners.idpartners"/>
    <display:column property="idpartners" media="csv excel xml pdf" titleKey="partners.idpartners"/>
    <display:column property="description" sortable="true" titleKey="partners.description"/>
    <display:column property="name" sortable="true" titleKey="partners.name"/>
    <display:column property="role" sortable="true" titleKey="partners.role"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="partnersList.partners"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="partnersList.partnerss"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="partnersList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="partnersList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="partnersList.title"/>.pdf</display:setProperty>
</display:table>
