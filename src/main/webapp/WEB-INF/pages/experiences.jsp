<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="experienceList.title"/></title>
    <meta name="menu" content="ExperienceMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="experienceList.heading"/></h2>

<form method="get" action="${ctx}/experiences" id="searchForm" class="form-inline">
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

<p><fmt:message key="experienceList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/experienceform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="experienceList" class="table table-condensed table-striped table-hover" requestURI="" id="experienceList" export="true" pagesize="25">
    <display:column property="idexperience" sortable="true" href="experienceform" media="html"
        paramId="idexperience" paramProperty="idexperience" titleKey="experience.idexperience"/>
    <display:column property="idexperience" media="csv excel xml pdf" titleKey="experience.idexperience"/>
    <display:column property="activity" sortable="true" titleKey="experience.activity"/>
    <display:column property="description" sortable="true" titleKey="experience.description"/>
    <display:column property="enddate" sortable="true" titleKey="experience.enddate"/>
    <display:column property="place" sortable="true" titleKey="experience.place"/>
    <display:column property="startdate" sortable="true" titleKey="experience.startdate"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="experienceList.experience"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="experienceList.experiences"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="experienceList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="experienceList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="experienceList.title"/>.pdf</display:setProperty>
</display:table>
