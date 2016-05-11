<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pastprojectDetail.title"/></title>
    <meta name="menu" content="PastprojectMenu"/>
    <meta name="heading" content="<fmt:message key='pastprojectDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pastprojectList.pastproject"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="pastprojectDetail.heading"/></h2>
    <fmt:message key="pastprojectDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="pastproject" method="post" action="pastprojectform" cssClass="well"
           id="pastprojectForm" onsubmit="return validatePastproject(this)">
<form:hidden path="idpastproject"/>
    <spring:bind path="pastproject.dateended">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="pastproject.dateended" styleClass="control-label"/>
        <form:input cssClass="form-control" path="dateended" id="dateended"  maxlength="19"/>
        <form:errors path="dateended" cssClass="help-block"/>
    </div>
    <spring:bind path="pastproject.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="pastproject.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="255"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="pastproject.projectname">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="pastproject.projectname" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectname" id="projectname"  maxlength="255"/>
        <form:errors path="projectname" cssClass="help-block"/>
    </div>
    <spring:bind path="pastproject.startdate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="pastproject.startdate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startdate" id="startdate"  maxlength="19"/>
        <form:errors path="startdate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="user" items="userList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pastproject.idpastproject}">
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

<v:javascript formName="pastproject" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pastprojectForm']).focus();
    });
</script>
