<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="commentDetail.title"/></title>
    <meta name="menu" content="CommentMenu"/>
    <meta name="heading" content="<fmt:message key='commentDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="commentList.comment"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="commentDetail.heading"/></h2>
    <fmt:message key="commentDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="comment" method="post" action="/commentform" cssClass="well"
               id="commentForm" onsubmit="return validateComment(this)">
<form:hidden path="idcomment"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="comment" items="commentList" itemLabel="label" itemValue="value"/>
    <spring:bind path="comment.commenttext">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="comment.commenttext" styleClass="control-label"/>
        <form:input cssClass="form-control" path="commenttext" id="commenttext"  maxlength="255"/>
        <form:errors path="commenttext" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="idea" items="ideaList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="user" items="userList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty comment.idcomment}">
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

<v:javascript formName="comment" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['commentForm']).focus();
    });
</script>
