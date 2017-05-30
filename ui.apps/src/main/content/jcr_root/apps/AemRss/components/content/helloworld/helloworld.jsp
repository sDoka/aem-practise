<%@include file="/apps/AemRss/global.jsp" %>
<sling:adaptTo adaptable="${resource}" adaptTo="ru.bmm.aem.rss.core.models.HelloWorldModel" var="helloModel"/>

HelloWorld says :
<p> ${helloModel.message} </p>