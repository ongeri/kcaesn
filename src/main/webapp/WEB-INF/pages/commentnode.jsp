<%@ include file="/common/taglibs.jsp" %>
<c:forEach var="comment" items="${node.comments}">
    <div class="media">
        <!-- answer to the first comment -->

        <div class="media-heading">
            <button class="btn btn-default btn-collapse btn-xs" type="button"
                    data-toggle="collapse" data-target="#collapse${comment.idcomment}"
                    aria-expanded="false" aria-controls="collapseExample"><span
                    class="glyphicon glyphicon-minus" aria-hidden="true"></span>
            </button>
            <a href="userprofile?userid=${comment.user.id}"> <b>${comment.user.username}</b></a> ${comment.title}
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
                                                     aria-expanded="false"
                                                     aria-controls="collapseExample"><i class="fa fa-comment-o"> </i>
                                                      Reply</a>
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
