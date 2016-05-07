<%@ include file="/common/taglibs.jsp" %>
<div class="sidebar">
    <div class="mini-submenu">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </div>
    <div class="list-group">
        <span href="#" class="list-group-item active">
            I-Share
            <span class="pull-right" id="slide-submenu">
                <i class="fa fa-times"></i>
            </span>
        </span>
        <a href="ideas" class="list-group-item">
            <i class="fa fa-list"></i> All Ideas
        </a>
        <a href="ideas?creator=" class="list-group-item">
            <i class="fa fa-user-md"></i> My Ideas
        </a>
        <a href="recent" class="list-group-item">
            <i class="fa fa-clock-o"></i> Recent
        </a>
        <a href="trending" class="list-group-item">
            <i class="fa fa-line-chart"></i> Trending
        </a>
        <a href="ideaform" class="list-group-item">
            <i class="fa fa-plus-circle"></i> Add an
            Idea
        </a>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
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
