<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="experienceDetail.title"/></title>
    <meta name="menu" content="ExperienceMenu"/>
    <meta name="heading" content="<fmt:message key='experienceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="experienceList.experience"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="experienceDetail.heading"/></h2>
    <fmt:message key="experienceDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="experience" method="post" action="experienceform" cssClass="well"
           id="experienceForm" onsubmit="return validateExperience(this)">
<form:hidden path="idexperience"/>
    <spring:bind path="experience.activity">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="experience.activity" styleClass="control-label"/>
        <form:input cssClass="form-control" path="activity" id="activity"  maxlength="255"/>
        <form:errors path="activity" cssClass="help-block"/>
    </div>
    <spring:bind path="experience.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="experience.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description"  maxlength="255"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    <spring:bind path="experience.enddate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="experience.enddate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="enddate" id="enddate"  maxlength="19"/>
        <form:errors path="enddate" cssClass="help-block"/>
    </div>
    <spring:bind path="experience.place">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="experience.place" styleClass="control-label"/>
        <form:input cssClass="form-control" path="place" id="place"  maxlength="255"/>
        <form:errors path="place" cssClass="help-block"/>
    </div>
    <spring:bind path="experience.startdate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="experience.startdate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startdate" id="startdate"  maxlength="19"/>
        <form:errors path="startdate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="user" items="userList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty experience.idexperience}">
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

<v:javascript formName="experience" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['experienceForm']).focus();
    });
</script>
