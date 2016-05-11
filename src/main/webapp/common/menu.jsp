<%@ include file="/common/taglibs.jsp" %>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
    <div class="collapse navbar-collapse" id="navbar">
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${empty pageContext.request.remoteUser}">
                <li class="active">
                    <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
                </li>
            </c:if>
            <menu:displayMenu name="Home"/>
            <menu:displayMenu name="IdeaMenu"/>
            <menu:displayMenu name="UserMenu"/>
            <menu:displayMenu name="AdminMenu"/>
            <menu:displayMenu name="Logout"/>
            <!--Idea-START-->
            <!--Idea-END-->

            <!--Comment-START-->
                <%--<menu:displayMenu name="CommentMenu"/>--%>
            <!--Comment-END-->
            <!--Milestone-START-->
                <%--<menu:displayMenu name="MilestoneMenu"/>--%>
            <!--Milestone-END-->
            <!--Experience-START-->
    <%--<menu:displayMenu name="ExperienceMenu"/>--%>
    <!--Experience-END-->
    <!--Biography-START-->
    <%--<menu:displayMenu name="BiographyMenu"/>--%>
    <!--Biography-END-->
    <!--Tag-START-->
    <%--<menu:displayMenu name="TagMenu"/>--%>
    <!--Tag-END-->
    <!--Pastproject-START-->
    <%--<menu:displayMenu name="PastprojectMenu"/>--%>
    <!--Pastproject-END-->
    
    <!--Summary-START-->
    <%--<menu:displayMenu name="SummaryMenu"/>--%>
    <!--Summary-END-->
    <!--Activities-START-->
    <menu:displayMenu name="ActivitiesMenu"/>
    <!--Activities-END-->
    <!--Channes-START-->
    <menu:displayMenu name="ChannesMenu"/>
    <!--Channes-END-->
    <!--Coststructure-START-->
    <menu:displayMenu name="CoststructureMenu"/>
    <!--Coststructure-END-->
    <!--Customerrelationship-START-->
    <menu:displayMenu name="CustomerrelationshipMenu"/>
    <!--Customerrelationship-END-->
    <!--Customersegment-START-->
    <menu:displayMenu name="CustomersegmentMenu"/>
    <!--Customersegment-END-->
    <!--Revenuestream-START-->
    <menu:displayMenu name="RevenuestreamMenu"/>
    <!--Revenuestream-END-->
    <!--Valueproposition-START-->
    <menu:displayMenu name="ValuepropositionMenu"/>
    <!--Valueproposition-END-->
    <!--Partners-START-->
    <menu:displayMenu name="PartnersMenu"/>
    <!--Partners-END-->
    <!--Resources-START-->
    <menu:displayMenu name="ResourcesMenu"/>
    <!--Resources-END-->
</ul>
    </div>
</menu:useMenuDisplayer>
