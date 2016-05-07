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
            <span class="label label-info">12314</span> vertu 12 sat once yazmis
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
                                <label for="commentt">Your Comment</label>
                                                                        <textarea name="comment" id=commentt
                                                                                  class="form-control"
                                                                                  rows="3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-default">Send
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
