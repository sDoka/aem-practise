<%@include file="/apps/AemRss/global.jsp" %>


<cq:includeClientLib css="AemRss.bootstrap" />
<cq:includeClientLib css="AemRss.animateCss" />
<cq:includeClientLib css="AemRss.helloworld" />

<sling:adaptTo adaptable="${resource}" adaptTo="ru.bmm.aem.rss.core.models.HelloWorldModel" var="helloModel"/>

<div class="alert alert-success" role="alert">
    <strong>Well done!</strong> You successfully read this important alert message.
</div>


<div class="col-md-6 animated-column animated-column-one" id="first-column">
    HelloWorld says on first column:
    <p> ${helloModel.message} </p>
</div>
<div class="col-md-6  animated-column animated-column-two" id="second-column">
    HelloWorld says on second column:
    <p> ${helloModel.message} </p>
</div>

<cq:includeClientLib js="AemRss.jquery" />
<cq:includeClientLib js="AemRss.bootstrap" />
<cq:includeClientLib js="AemRss.helloworld" />

<script>
$(document).ready(function(e) {
    initHelloWorld();
});
</script>