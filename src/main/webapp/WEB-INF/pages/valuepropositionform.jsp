<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="valuepropositionDetail.title"/></title>
    <meta name="menu" content="ValuepropositionMenu"/>
    <meta name="heading" content="<fmt:message key='valuepropositionDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="valuepropositionList.valueproposition"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="valueproposition" method="post" action="valuepropositionform" cssClass="well"
           id="valuepropositionForm" onsubmit="return validateValueproposition(this)">
    <form:hidden path="idvalueproposition"/>
    <form:hidden path="idea.ididea"/>
    <spring:bind path="valueproposition.name">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="valueproposition.name" styleClass="control-label"/>
    <form:input cssClass="form-control" path="name" id="name" maxlength="255"/>
    <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="valueproposition.description">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="valueproposition.description" styleClass="control-label"/>
    <form:input cssClass="form-control" path="description" id="description" maxlength="255"/>
    <form:errors path="description" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <%--<form:select cssClass="form-control" path="idea" items="ideaList" itemLabel="label" itemValue="value"/>--%>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty valueproposition.idvalueproposition}">
            <button type="submit" class="btn btn-danger" id="delete" name="delete"
                    onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>

<v:javascript formName="valueproposition" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['valuepropositionForm']).focus();
    });
</script>
