<%@ include file="/common/taglibs.jsp" %>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
    <div class="collapse navbar-collapse" id="navbar">
        <ul class="nav navbar-nav">
            <c:if test="${empty pageContext.request.remoteUser}">
                <li class="active">
                    <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
                </li>
            </c:if>
            <menu:displayMenu name="Home"/>
            <menu:displayMenu name="UserMenu"/>
            <menu:displayMenu name="AdminMenu"/>
            <menu:displayMenu name="Logout"/>
            <!--Idea-START-->
            <menu:displayMenu name="IdeaMenu"/>
            <!--Idea-END-->

            <!--Comment-START-->
    <menu:displayMenu name="CommentMenu"/>
    <!--Comment-END-->
    <!--Milestone-START-->
    <menu:displayMenu name="MilestoneMenu"/>
    <!--Milestone-END-->
</ul>
    </div>
</menu:useMenuDisplayer>
