<%--
  RSS Reader component.
--%>
<%@include file="/apps/AemRss/global.jsp" %>
<%@page session="false" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<cq:includeClientLib categories="AemRss"/>

<sling:adaptTo adaptable="${slingRequest}" adaptTo="ru.bmm.aem.rss.core.models.Feed" var="model"/>

<div class="rssreader">
    <h1> Rss reader</h1>

    <h2>${model.title}</h2>

    <div class="row">
        <div class="col-md-4 col-xs-12">
            <img src="${model.imagePath}" />
        </div>
        <div class="col-md-8 col-xs-12">
            <c:choose>
                <c:when test="${model.hasError}">
                    <pre>
                        ${model.errorMessage}
                    </pre>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${model.items}" var="item">
                        <div class="panel-group" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab">
                                    <h4 class="panel-title">
                                        <a>${item.title}</a>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse" aria-expanded="false">
                                    <div class="panel-body">
                                        <div>
                                            ${item.description}
                                        </div>
                                        <a href="${item.link}" target="_blank">Read more..</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
