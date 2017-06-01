<%--
  RSS Reader component.
--%>
<%@include file="/apps/AemRss/global.jsp" %>
<%@page session="false" %>

<cq:includeClientLib css="AemRss.bootstrap" />
<cq:includeClientLib css="AemRss.animateCss" />
<cq:includeClientLib css="AemRss.rss-reader" />

<sling:adaptTo adaptable="${slingRequest}" adaptTo="ru.bmm.aem.rss.core.models.Feed" var="model"/>

<div class="rss-reader-container">
    <h2>${model.title}</h2>
    <img src="${model.imagePath}" />
    <div class="container-fluid">
        <c:choose>
            <c:when test="${model.hasError}">
                <pre>
                    ${model.errorMessage}
                </pre>
            </c:when>
            <c:otherwise>
                <div class="panel-group" id="aem-rss-accordion">
                <c:forEach items="${model.items}" var="item" varStatus="loop">
                    <div class="panel panel-default">
                        <div class="panel-heading accordion-toggle" data-toggle="collapse" data-parent="#aem-rss-accordion" href="#collapse${loop.index}">
                            <h4 class="panel-title">
                                ${item.title}
                            </h4>
                        </div>
                        <div id="collapse${loop.index}" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="item-text">
                                    ${item.description}
                                </div>
                                 <a href="${item.link}" target="_blank">Read more..</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
                <button id="show-more-items-btn" type="button" class="btn btn-info">Show more...</button>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<cq:includeClientLib js="AemRss.jquery" />
<cq:includeClientLib js="AemRss.bootstrap" />
<cq:includeClientLib js="AemRss.rss-reader" />

<script>
    $(document).ready(function(e) {
        initRssReader('${model.rssUrl}');
    });
</script>