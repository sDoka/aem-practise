<%@include file="/apps/AemRss/global.jsp" %>


<cq:includeClientLib css="AemRss.bootstrap" />
<cq:includeClientLib css="AemRss.animateCss" />
<cq:includeClientLib css="AemRss.helloworld" />

<sling:adaptTo adaptable="${resource}" adaptTo="ru.bmm.aem.rss.core.models.HelloWorldModel" var="helloModel"/>

<div class="alert alert-success" role="alert">
    <strong>Well done!</strong> This is a demonstration of bootstrap activite.
</div>


<div class="col-md-6 animated-column animated-column-one" id="first-column">
    HelloWorld says on first column from SlingModel:
    <p> ${helloModel.message} </p>
</div>
<div class="col-md-6  animated-column animated-column-two" id="second-column">
    These columns jump with animateCss
</div>

<cq:includeClientLib js="AemRss.jquery" />
<cq:includeClientLib js="AemRss.bootstrap" />
<cq:includeClientLib js="AemRss.helloworld" />

<script>
$(document).ready(function(e) {
    initHelloWorld();
});
</script>