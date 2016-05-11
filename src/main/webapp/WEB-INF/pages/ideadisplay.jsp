<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>
        <fmt:message key="ideaDetail.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
    <meta name="heading" content="<fmt:message key='ideaDetail.heading'/>"/>
    <link href='/styles/timelinecss.css' rel='stylesheet' type='text/css'>
    <link href="/styles/comments.css" rel='stylesheet' type='text/css'>
    <link href="/styles/canvas.css" rel='stylesheet' type='text/css'>
</head>

<c:set var="delObject" scope="request"><fmt:message key="ideaList.idea"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3 sidebar">
    <%@include file="ideasidebar.jsp" %>
</div>


<div class="col-sm-9">
    <c:set var="test" value="${idea.ideabody.content}"/>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-sm-9"><h3>${idea.title}</h3></div>
                <div class="col-sm-3">
                    <c:if test="${not empty pageContext.request.remoteUser}">
                        <a type="button" class="btn btn-sm btn-primary pull-right"
                           href="/ideaform?ididea=${idea.ididea}">Edit</a>
                    </c:if>
                </div>
            </div>
            <div>${idea.description}</div>
        </div>
        <div class="panel-body">
            <ul class="nav nav-tabs" id="myTab" style="border-bottom:1px solid #DDDDDD;">
                <li class="active"><a data-target="#details" data-toggle="tab">Details</a></li>
                <li><a data-target="#bizcanvastab" data-toggle="tab">Business Canvas</a></li>
                <li><a data-target="#milestones" data-toggle="tab">Milestones</a></li>
                <li><a data-target="#comments" data-toggle="tab">Comments</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="details">
                    <% out.println(StringEscapeUtils.unescapeHtml(String.valueOf(pageContext.getAttribute("test"))));%>
                </div>
                <div class="tab-pane" id="bizcanvastab">
                    <!-- Canvas -->
                    <table id="bizcanvas" cellspacing="0" border="1">
                        <!-- Upper part -->
                        <tr>
                            <td colspan="2" rowspan="2">
                                <h4>Key Partners</h4>
                                </p>
                            </td>
                            <td colspan="2">
                                <h4>Key Activities</h4>

                                <p>...</p>
                            </td>
                            <td colspan="2" rowspan="2">
                                <h4>Value Proposition</h4>

                                <p>...</p>
                            </td>
                            <td colspan="2">
                                <h4>Customer Relationship</h4>

                                <p>...</p>
                            </td>
                            <td colspan="2" rowspan="2">
                                <h4>Customer Segments</h4>

                                <p>...</p>
                            </td>
                        </tr>

                        <!-- Lower part -->
                        <tr>
                            <td colspan="2">
                                <h4>Key Resources</h4>

                                <p>...</p>
                            </td>
                            <td colspan="2">
                                <h4>Channels</h4>

                                <p>...</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <h4>Cost Structure</h4>

                                <p>...</p>
                            </td>
                            <td colspan="5">
                                <h4>Revenue Streams</h4>

                                <p>...</p>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane" id="milestones">
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-default btn-xs pull-right" role="button"
                               data-toggle="collapse"
                               href="#milestoneformbox${idea.ididea}"
                               id="addmilestonebtn"
                               aria-expanded="false"
                               aria-controls="collapseExample"><i class="fa fa-check-circle-o"> </i>
                                Add Milestone</a>
                            <%--<div id="commentformbox"></div>--%>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="collapse" id="milestoneformbox${idea.ididea}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div id="milestonescontent">
                                <%--<%@ include file="/common/timeline.jsp" %>--%>
                                <ul class="timeline">
                                    <c:forEach items="${idea.milestones}" var="milestone" varStatus="loop">
                                        <li class="${loop.index%2==0 ? '' : 'timeline-inverted'}">
                                            <div class="timeline-badge info">
                                                <small><i class="fa fa-check-circle-o"></i></small>
                                            </div>
                                            <div class="timeline-panel">
                                                <div class="timeline-heading row">
                                                    <div class="col-sm-12">
                                                        <h5 class="timeline-title pull-left">${milestone.name}</h5>

                                                        <div class="btn-group pull-right">
                                                            <button type="button"
                                                                    class="btn btn-primary btn-sm dropdown-toggle"
                                                                    data-toggle="dropdown">
                                                                <i class="fa fa-cogs"></i> <span
                                                                    class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li><a href="#">Action</a></li>
                                                                <li><a href="#">Another action</a></li>
                                                                <li><a href="#">Something else here</a></li>
                                                                <li class="divider"></li>
                                                                <li><a href="#">Separated link</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div><span> <i
                                                        class="fa fa-calendar-check-o"></i><small
                                                        class="text-muted">${milestone.duedate}</small></span></span>
                                                </div>
                                                <div class="timeline-body">
                                                        ${milestone.description}
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="comments">
                    <div class="row">
                        <div class="col-sm-12">
                            <a class="btn btn-default btn-xs pull-right" role="button"
                               data-toggle="collapse"
                               href="#maincommentformbox${idea.ididea}"
                               id="addcommentbtn"
                               aria-expanded="false"
                               aria-controls="collapseExample"><i class="fa fa-comment-o"> </i>
                                Add Comment</a>
                            <%--<div id="commentformbox"></div>--%>
                            <div class="collapse" id="maincommentformbox${idea.ididea}">
                            </div>
                        </div>
                    </div>
                    <div class="post-comments">

                        <div class="row">
                            <c:forEach var="comment" items="${idea.comments}">
                                <div class="media">
                                    <!-- answer to the first comment -->

                                    <div class="media-heading">
                                        <button class="btn btn-default btn-collapse btn-xs" type="button"
                                                data-toggle="collapse" data-target="#collapse${comment.idcomment}"
                                                aria-expanded="false" aria-controls="collapseExample"><span
                                                class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                        </button>
                                        <a href="profile?id=${comment.user.id}">
                                            <b>${comment.user.username}</b></a> ${comment.title}
                                    </div>

                                    <div class="panel-collapse collapse in" id="collapse${comment.idcomment}">

                                        <div class="media-left">
                                            <div class="vote-wrap">
                                                    <%--<div class="save-post">--%>
                                                    <%--<a href="#"><span class="glyphicon glyphicon-star"--%>
                                                    <%--aria-label="Save"></span></a>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="vote up">--%>
                                                    <%--<i class="glyphicon glyphicon-menu-up"></i>--%>
                                                    <%--</div>--%>
                                                <div class="vote inactive">
                                                    <i class="glyphicon glyphicon-menu-down"></i>
                                                </div>
                                            </div>
                                            <!-- vote-wrap -->
                                        </div>
                                        <!-- media-left -->


                                        <div class="media-body">
                                            <div>${comment.commenttext}</div>

                                            <div class="comment-meta">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div class="pull-left">
                        <span>
                                                  <a class="" role="button"
                                                     data-toggle="collapse"
                                                     href="#reply${comment.idcomment}"
                                                     id="replyto${comment.idcomment}"
                                                     aria-expanded="false"
                                                     aria-controls="collapseExample"><i class="fa fa-comment-o"> </i>
                                                      Reply</a>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $("#replyto${comment.idcomment}").click(function () {
                                        $.ajax({
                                            url: '/commentform?ajax=true&parentcommentid=${comment.idcomment}',
                                            success: function (data) {
                                                $('#reply${comment.idcomment}').html(data)
                                            },
                                            type: 'GET'
                                        });
                                    });
                                });
                            </script>
                                                </span>
                                                        </div>
                                                        <div class="pull-right"> <span><a class="" role="button"
                                                                                          id="like"><i
                                                                class="fa fa-thumbs-o-up"></i>Vote
                                                            Up</a>
                                                    </span>
                                                    <span><a class="" role="button" id="dislike"><i
                                                            class="fa fa-thumbs-o-down"></i>Vote
                                                        Down</a>
                                                    </span>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="collapse" id="reply${comment.idcomment}">

                                                </div>
                                            </div>
                                            <!-- comment-meta -->
                                        </div>
                                        <c:set var="node" value="${comment}" scope="request"/>
                                        <jsp:include page="commentnode.jsp"/>
                                    </div>
                                    <!-- comments -->
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <%--<c:forEach items="${idea.comments}" var="comment" varStatus="loop">--%>
                    <%--<ul>--%>
                    <%--<li>${comment.title}</li>--%>
                    <%--<c:set var="node" value="${comment}" scope="request"/>--%>
                    <%--<jsp:include page="commentnode.jsp"/>--%>
                    <%--</ul>--%>
                    <%--</c:forEach>--%>
                    <%--<%@ include file="commentsfile.jsp" %>--%>
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
                    $('#milestoneformbox${idea.ididea}').html(data)
                },
                type: 'GET'
            });
        });
        $("#addcommentbtn").click(function () {
            $.ajax({
                url: '/commentform?ajax=true&ididea=${idea.ididea}',
                success: function (data) {
                    $('#maincommentformbox${idea.ididea}').html(data)
                },
                type: 'GET'
            });
        });
    });
</script>