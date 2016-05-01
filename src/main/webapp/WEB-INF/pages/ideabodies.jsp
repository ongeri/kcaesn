<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="ideabodyList.title"/></title>
    <meta name="menu" content="IdeabodyMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="ideabodyList.heading"/></h2>

<form method="get" action="${ctx}/ideabodies" id="searchForm" class="form-inline">
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

<p><fmt:message key="ideabodyList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/ideabodyform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message
            key="button.done"/></a>
</div>

<display:table name="ideabodyList" class="table table-condensed table-striped table-hover" requestURI=""
               id="ideabodyList" export="true" pagesize="25">
    <display:column property="idideabody" sortable="true" href="ideabodyform" media="html"
                    paramId="idideabody" paramProperty="idideabody" titleKey="ideabody.idideabody"/>
    <display:column property="idideabody" media="csv excel xml pdf" titleKey="ideabody.idideabody"/>
    <display:column property="content" sortable="true" titleKey="ideabody.content"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="ideabodyList.ideabody"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message
            key="ideabodyList.ideabodies"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="ideabodyList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="ideabodyList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="ideabodyList.title"/>.pdf</display:setProperty>
</display:table>
