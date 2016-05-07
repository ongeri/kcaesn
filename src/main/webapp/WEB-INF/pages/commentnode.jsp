<%@ include file="/common/taglibs.jsp" %>
<c:forEach var="comment" items="${node.comments}">
    <ul>
        <li>${comment.title}</li>
        <c:set var="node" value="${comment}" scope="request"/>
        <jsp:include page="commentnode.jsp"/>
    </ul>
</c:forEach>