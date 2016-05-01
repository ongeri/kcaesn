<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="ideaDetail.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
    <meta name="heading" content="<fmt:message key='ideaDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="ideaList.idea"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="ideaDetail.heading"/></h2>
    <fmt:message key="ideaDetail.message"/>
</div>

<div class="col-sm-6">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="idea" method="post" action="ideaform" cssClass="well"
               id="ideaForm" onsubmit="return validateIdea(this)">
        <form:hidden path="ididea"/>
        <spring:bind path="idea.description">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="idea.description" styleClass="control-label"/>
        <form:input cssClass="form-control" path="description" id="description" maxlength="255"/>
        <form:errors path="description" cssClass="help-block"/>
        </div>
        <spring:bind path="ideabody.content">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="ideabody.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="ideabody.content" id="content" maxlength="255"/>
        <form:errors path="ideabody.content" cssClass="help-block"/>
        </div>
        <!-- todo: change this to read the identifier field from the other pojo -->
        <%--<form:select cssClass="form-control" path="ideabody" items="ideabodyList" itemLabel="label" itemValue="value"/>--%>
        <spring:bind path="idea.title">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="idea.title" styleClass="control-label"/>
        <form:input cssClass="form-control" path="title" id="title" maxlength="45"/>
        <form:errors path="title" cssClass="help-block"/>
        </div>
        <!-- todo: change this to read the identifier field from the other pojo -->
        <%--<form:select cssClass="form-control" path="user" items="userList" itemLabel="label" itemValue="value"/>--%>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty idea.ididea}">
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
</div>

<v:javascript formName="idea" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['ideaForm']).focus();
    });
</script>
