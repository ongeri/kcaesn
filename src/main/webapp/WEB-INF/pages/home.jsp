<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<!-- Jumbotron Header -->
<header class="jumbotron hero-spacer"
        style="background: #dddddd url('/images/jumbotron-bg.jpg') no-repeat fixed center center">

    <h2><fmt:message key="home.heading"/></h2>

    <p><fmt:message key="home.message"/></p>

    <p><a class="btn btn-primary btn-large">Call to action!</a></p>
</header>
<!-- Page Features -->
<div class="row text-center">

    <div class="col-md-3 col-sm-6 hero-feature">
        <div class="thumbnail">
            <a href="<c:url value='/profile'/>"><img src="/images/blank-profile-hi.png" alt=""
                                                     style="height: 162px; width: 100%;"></a>

            <div class="caption">
                <a href="<c:url value='/profile'/>"><h3>Profile</h3></a>

                <p>Go to your profile.</p>

                <p>
                    <a href="/profile" class="btn btn-primary">View</a> <a href="/userform"
                                                                           class="btn btn-default">Edit</a>
                </p>
            </div>
        </div>
    </div>

    <div class="col-md-3 col-sm-6 hero-feature">
        <div class="thumbnail">
            <a href="<c:url value='/ideas'/>"> <img src="/images/Entreprenuers-sharing-ideas11.jpg" alt=""
                                                    style="height: 162px; width: 100%;"></a>

            <div class="caption">
                <a href="<c:url value='/ideas'/>"><h3>I-Share</h3></a>

                <p>View published ideas and innovations.</p>

                <p>
                    <a href="/ideaform" class="btn btn-primary">Add Idea</a> <a href="/ideas" class="btn btn-default">View
                    Ideas</a>
                </p>
            </div>
        </div>
    </div>

    <div class="col-md-3 col-sm-6 hero-feature">
        <div class="thumbnail">
            <a href="<c:url value='#'/>"><img src="/images/lightbulb-ideawww.valueinvestasia.com_.jpg" alt=""
                                              style="height: 162px; width: 100%;"></a>

            <div class="caption">
                <a href="<c:url value='#'/>"><h3>Invest</h3></a>

                <p>This section is coming soon</p>

                <p>
                    <a href="invest" class="btn btn-primary">Investments</a>
                    <!--<a href="#" class="btn btn-default">More Info</a>-->
                </p>
            </div>
        </div>
    </div>

    <div class="col-md-3 col-sm-6 hero-feature">
        <div class="thumbnail">
            <a href="<c:url value='#'/>"> <img src="/images/k-base.jpg" alt="" style="height: 162px; width: 100%;"></a>

            <div class="caption">
                <a href="<c:url value='#'/>"><h3>Library</h3></a>

                <p>Access to built-up knowledge</p>

                <p>
                    <a href="#" class="btn btn-primary">Browse</a>
                </p>
            </div>
        </div>
    </div>

</div>
</body>
