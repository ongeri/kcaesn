<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>
        <fmt:message key="ideaDetail.title"/></title>
    <meta name="menu" content="IdeaMenu"/>
    <meta name="heading" content="<fmt:message key='ideaDetail.heading'/>"/>
    <link href='/styles/timelinecss.css' rel='stylesheet' type='text/css'>
    <style type="text/css">
        .post-comments {
            padding-bottom: 9px;
            margin: 5px 0 5px;
        }

        .comments-nav {
            border-bottom: 1px solid #eee;
            margin-bottom: 5px;
        }

        .post-comments .comment-meta {
            border-bottom: 1px solid #eee;
            margin-bottom: 5px;
        }

        .post-comments .media {
            border-left: 1px dotted #000;
            border-bottom: 1px dotted #000;
            margin-bottom: 5px;
            padding-left: 10px;
        }

        .post-comments .media-heading {
            font-size: 12px;
            color: grey;
        }

        .post-comments .comment-meta a {
            font-size: 12px;
            color: grey;
            font-weight: bolder;
            margin-right: 5px;
        }
    </style>
    <style type="text/css">
        .mini-submenu {
            display: none;
            background-color: rgba(0, 0, 0, 0);
            border: 1px solid rgba(0, 0, 0, 0.9);
            border-radius: 4px;
            padding: 9px;
            /*position: relative;*/
            width: 42px;

        }

        .mini-submenu:hover {
            cursor: pointer;
        }

        .mini-submenu .icon-bar {
            border-radius: 1px;
            display: block;
            height: 2px;
            width: 22px;
            margin-top: 3px;
        }

        .mini-submenu .icon-bar {
            background-color: #000;
        }

        #slide-submenu {
            background: rgba(0, 0, 0, 0.45);
            display: inline-block;
            padding: 0 8px;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>

<c:set var="delObject" scope="request"><fmt:message key="ideaList.idea"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3 sidebar">
    <div class="mini-submenu">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </div>
    <div class="list-group">
        <span href="#" class="list-group-item active">
            Submenu
            <span class="pull-right" id="slide-submenu">
                <i class="fa fa-times"></i>
            </span>
        </span>
        <a href="#" class="list-group-item">
            <i class="fa fa-comment-o"></i> Lorem ipsum
        </a>
        <a href="#" class="list-group-item">
            <i class="fa fa-search"></i> Lorem ipsum
        </a>
        <a href="#" class="list-group-item">
            <i class="fa fa-user"></i> Lorem ipsum
        </a>
        <a href="#" class="list-group-item">
            <i class="fa fa-folder-open-o"></i> Lorem ipsum <span class="badge">14</span>
        </a>
        <a href="#" class="list-group-item">
            <i class="fa fa-bar-chart-o"></i> Lorem ipsumr <span class="badge">14</span>
        </a>
        <a href="#" class="list-group-item">
            <i class="fa fa-envelope"></i> Lorem ipsum
        </a>
    </div>
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
                    <c:forEach items="${idea.comments}" var="comment" varStatus="loop">
                        <ul>
                            <li>${comment.title}</li>
                            <c:set var="node" value="${comment}" scope="request"/>
                            <jsp:include page="commentnode.jsp"/>
                        </ul>
                    </c:forEach>
                    <%@ include file="commentsfile.jsp" %>
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
        $('[data-toggle="collapse"]').on('click', function () {
            var $this = $(this),
                    $parent = typeof $this.data('parent') !== 'undefined' ? $($this.data('parent')) : undefined;
            if ($parent === undefined) { /* Just toggle my  */
                $this.find('.glyphicon').toggleClass('glyphicon-plus glyphicon-minus');
                return true;
            }

            /* Open element will be close if parent !== undefined */
            var currentIcon = $this.find('.glyphicon');
            currentIcon.toggleClass('glyphicon-plus glyphicon-minus');
            $parent.find('.glyphicon').not(currentIcon).removeClass('glyphicon-minus').addClass('glyphicon-plus');

        });

        $('#slide-submenu').on('click', function () {
            $(this).closest('.list-group').fadeOut('slide', function () {
                $('.mini-submenu').fadeIn();
            });

        });

        $('.mini-submenu').on('click', function () {
            $(this).next('.list-group').toggle('slide');
            $('.mini-submenu').hide();
        })
    });
</script>