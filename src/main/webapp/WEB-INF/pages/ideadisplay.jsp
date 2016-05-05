<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>
        <fmt:message key="ideaDetail.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
    <meta name="heading" content="<fmt:message key='ideaDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="ideaList.idea"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">

</div>

<div class="col-sm-9">
    <c:set var="test" value="${idea.ideabody.content}"/>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div><h3>${idea.title}</h3></div>
            <div>${idea.description}</div>
        </div>
        <div class="panel-body">
            <ul class="nav nav-tabs" id="myTab" style="border-bottom:1px solid #DDDDDD;">
                <li class="active"><a data-target="#details" data-toggle="tab">Details</a></li>
                <li><a data-target="#milestones" data-toggle="tab">Milestones</a></li>
                <li><a data-target="#comments" data-toggle="tab">Comments</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="details">
                    <% out.println(StringEscapeUtils.unescapeHtml(String.valueOf(pageContext.getAttribute("test"))));%>
                </div>
                <div class="tab-pane" id="milestones">
                    <button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#myModal"
                            id="addmilestonebtn">Add Milestone
                    </button>
                    <div id="milestonescontent"></div>
                </div>
                <div class="tab-pane" id="comments">
                    Comments
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</div>
<!-- Modal<%--#FEFA5A--%>-->
<div id="myModal" class="modal fade" role="dialog">

</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#addmilestonebtn").click(function () {
            $.ajax({
                url: '/milestoneform?ajax=true&ididea=${idea.ididea}',
                success: function (data) {
                    $('#myModal').html(data)
                },
                type: 'GET'
            });
        });
    });
</script>