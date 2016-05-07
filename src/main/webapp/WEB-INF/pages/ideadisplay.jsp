<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>
        <fmt:message key="ideaDetail.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
    <meta name="heading" content="<fmt:message key='ideaDetail.heading'/>"/>
    <link href='/styles/timelinecss.css' rel='stylesheet' type='text/css'>
    <link href="/styles/comments.css" rel='stylesheet' type='text/css'>
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
                        <a type="button" class="btn btn-sm btn-primary"
                           href="/ideaform?ididea=${idea.ididea}">Edit</a>
                    </c:if>
                </div>
            </div>
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
                    <div>
                        <button type="button" class="btn btn-default btn-xs right" data-toggle="modal"
                                data-target="#myModal"
                                id="addmilestonebtn">
                            Add Milestone
                        </button>
                    </div>
                    <div id="milestonescontent">
                        <%--<%@ include file="/common/timeline.jsp" %>--%>
                        <ul class="timeline">
                            <c:forEach items="${idea.milestones}" var="milestone" varStatus="loop">
                                <li class="${loop.index%2==0 ? '' : 'timeline-inverted'}">
                                    <div class="timeline-badge info"><i class="glyphicon glyphicon-floppy-disk"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">${milestone.name}</h4>
                                        </div>
                                        <div>Due Date:${milestone.duedate}</div>
                                        <div class="timeline-body">
                                            <p>${milestone.description}</p>
                                            <hr>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle"
                                                        data-toggle="dropdown">
                                                    <i class="glyphicon glyphicon-cog"></i> <span class="caret"></span>
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
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="tab-pane" id="comments">
                    <div>
                        <div id="commentformbox"></div>
                        <button type="button" class="btn btn-default btn-xs right" id="addcommentbtn">
                            Add Comment
                        </button>
                    </div>
                    Comments
                    <%--<c:forEach items="${idea.comments}" var="comment" varStatus="loop">--%>
                    <%--<ul>--%>
                    <%--<li>${comment.title}</li>--%>
                    <%--<c:set var="node" value="${comment}" scope="request"/>--%>
                    <%--<jsp:include page="commentnode.jsp"/>--%>
                    <%--</ul>--%>
                    <%--</c:forEach>--%>

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
                                        <span class="label label-info">12314</span> ${comment.title}
                                    </div>

                                    <div class="panel-collapse collapse in" id="collapse${comment.idcomment}">

                                        <div class="media-left">
                                            <div class="vote-wrap">
                                                <div class="save-post">
                                                    <a href="#"><span class="glyphicon glyphicon-star"
                                                                      aria-label="Save"></span></a>
                                                </div>
                                                <div class="vote up">
                                                    <i class="glyphicon glyphicon-menu-up"></i>
                                                </div>
                                                <div class="vote inactive">
                                                    <i class="glyphicon glyphicon-menu-down"></i>
                                                </div>
                                            </div>
                                            <!-- vote-wrap -->
                                        </div>
                                        <!-- media-left -->


                                        <div class="media-body">
                                            <p>${comment.commenttext}</p>

                                            <div class="comment-meta">
                                                <span><a href="#">delete</a></span>
                                                <span><a href="#">report</a></span>
                                                <span><a href="#">hide</a></span>
                            <span>
                              <a class="" role="button" data-toggle="collapse" href="#reply${comment.idcomment}"
                                 aria-expanded="false" aria-controls="collapseExample">reply</a>
                            </span>

                                                <div class="collapse" id="reply${comment.idcomment}">
                                                    <form>
                                                        <div class="form-group">
                                                            <label for="commenttitle${comment.idcomment}">Title</label>
                                                            <input id="commenttitle${comment.idcomment}"
                                                                   name="commenttitle${comment.idcomment}"
                                                                   class="form-control" value="" maxlength="19"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="commentt">Your Comment</label>
                                                            <textarea name="comment" id=commentt class="form-control"
                                                                      rows="3"></textarea>
                                                        </div>
                                                        <button type="submit" class="btn btn-xs btn-default">Send
                                                        </button>
                                                    </form>
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
                    $('#myModal').html(data)
                },
                type: 'GET'
            });
        });
        $("#addcommentbtn").click(function () {
            $.ajax({
                url: '/commentform?ajax=true&ididea=${idea.ididea}',
                success: function (data) {
                    $('#commentformbox').html(data)
                },
                type: 'GET'
            });
        });
    });
</script>