<%@ include file="/common/taglibs.jsp" %>

<%--<c:set var="delObject" scope="request"><fmt:message key="milestoneList.milestone"/></c:set>--%>
<%--<script type="text/javascript">var msgDelConfirm =--%>
<%--"<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";--%>
<%--</script>--%>

<div class="row">
    <div class="col-md-8 col-sm-12 col-lg-6 well well-lg pull-right">
        <form:form commandName="milestone" method="post" action="/milestoneform?ididea=${milestone.idea.ididea}"
                   id="milestoneForm"
                   onsubmit="return validateMilestone(this)">
            <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
            <form:hidden path="idmilestone"/>
            <form:hidden path="idea.ididea"/>
            <form:hidden path="datecreated"/>
            <spring:bind path="milestone.name">
                <div class="form-group row ${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="milestone.name" styleClass="control-label col-sm-4"/>
            <div class="input-group date col-sm-8"><form:input cssClass="form-control" path="name"
                                                               id="name" maxlength="19"/>
            </div>
            <form:errors path="name" cssClass="help-block"/>
            </div>
            <spring:bind path="milestone.description">
                <div class="form-group row ${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="milestone.description" styleClass="control-label col-sm-4"/>
            <div class="input-group date col-sm-8">
                <form:input cssClass="form-control" path="description" id="description" maxlength="255"/>
            </div>
            <form:errors path="description" cssClass="help-block"/>
            </div>
            <spring:bind path="milestone.duedate">
                <div class="form-group row ${(not empty status.errorMessage) ? ' has-error' : ''}">
            </spring:bind>
            <appfuse:label key="milestone.duedate" styleClass="control-label col-sm-4"/>
            <div class="input-group date col-sm-8" data-provide="datepicker">
               <span class="input-group-addon">
                   <label for="duedate">
                       <i class="fa fa-calendar"></i>
                   </label>
               </span>
                <form:input cssClass="form-control date-picker" path="duedate" id="duedate" maxlength="19"/>
            </div>
            <form:errors path="duedate" cssClass="help-block"/>
            </div>
            <!-- todo: change this to read the identifier field from the other pojo -->
            <%--<form:select cssClass="form-control" path="idea" items="ideaList" itemLabel="label" itemValue="value"/>--%>
            <!-- todo: change this to read the identifier field from the other pojo -->
            <div class="form-group row ${(not empty status.errorMessage) ? ' has-error' : ''}">
                <appfuse:label key="milestone.parentMilestone" styleClass="control-label col-sm-4"/>
                <div class="input-group col-sm-8">
                    <form:select cssClass="form-control" path="parentMilestone">
                        <form:option value="NONE" label="--- Select ---"/>
                        <form:options itemLabel="name" itemValue="idmilestone" items="${otherMilestones}"/>
                    </form:select>
                </div>
                <form:errors path="parentMilestone" cssClass="help-block"/>
            </div>
            <button type="submit" class="btn btn-primary btn-sm" id="save" name="save"
                    onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty milestone.idmilestone}">
                <button type="submit" class="btn btn-danger btn-sm" id="delete" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>

            <button type="submit" class="btn btn-default btn-sm" data-dismiss="modal" id="cancel" name="cancel"
                    onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </form:form>
    </div>
</div>


<v:javascript formName="milestone" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['milestoneForm']).focus();
//        $('.date-picker').datepicker({ dateFormat: "dd/mm/yyyy", weekStart: "0"});
        $('.date-picker').datepicker({format: "mm/dd/yyyy", weekStart: "3"});

    });
</script>
