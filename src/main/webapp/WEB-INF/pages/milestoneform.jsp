<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="milestoneDetail.title"/></title>
    <meta name="menu" content="MilestoneMenu"/>
    <meta name="heading" content="<fmt:message key='milestoneDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="milestoneList.milestone"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="milestoneDetail.heading"/></h2>
    <fmt:message key="milestoneDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="milestone" method="post" action="milestoneform" cssClass="well"
           id="milestoneForm" onsubmit="return validateMilestone(this)">
<form:hidden path="idmilestone"/>
    <spring:bind path="milestone.datecreated">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="milestone.datecreated" styleClass="control-label"/>
        <form:input cssClass="form-control" path="datecreated" id="datecreated"  maxlength="19"/>
        <form:errors path="datecreated" cssClass="help-block"/>
    </div>
    <spring:bind path="milestone.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="milestone.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="255"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="milestone.duedate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="milestone.duedate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="duedate" id="duedate"  maxlength="19"/>
        <form:errors path="duedate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="idea" items="ideaList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="milestone" items="milestoneList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty milestone.idmilestone}">
            <button type="submit" class="btn btn-danger" id="delete" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="milestone" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['milestoneForm']).focus();
    });
</script>
