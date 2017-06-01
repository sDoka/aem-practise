$.fn.extend({
    animateCss: function (animationName) {
        var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
        this.addClass('animated ' + animationName).one(animationEnd, function() {
            $(this).removeClass('animated ' + animationName);
            return $(this);
        });
    }
});

function initRssReader(_currentFeedUrl) {
    console.log("Rss reader js file loaded");
    var currentFeedUrl = _currentFeedUrl,
        $showMoreItemsBtn = $('#show-more-items-btn'),
        $rssAccordionGroup = $('#aem-rss-accordion'),
        addNewRssItem = function(rssItemJsonObject) {
            var $lastRssItem = $rssAccordionGroup.children().last(),
                $newRssItem = $lastRssItem.clone(),
                $titleElement = $newRssItem.find('h4'),
                $descriptionElement = $newRssItem.find('.item-text');

            $titleElement.html(rssItemJsonObject.FeedMessage.title);
            $descriptionElement.html(rssItemJsonObject.FeedMessage.description);

            $newRssItem.insertAfter($lastRssItem).animateCss('fadeIn');
        },
        getMoreItems = function(currentItemsCount, callback){
            var path = '/bin/rss-aem?rssFeedUrl=' + currentFeedUrl + '&itemsCount=' + currentItemsCount;
            $.get(path, function(data) {
                var newItems = $.parseJSON(data);
                return callback(newItems);
            });
        };

    $showMoreItemsBtn.on('click', function(e) {
        var currentItemsCount = $rssAccordionGroup.children().size();

        $showMoreItemsBtn.fadeOut(1000, function() {
               getMoreItems(currentItemsCount, function(newItems) {
                   $.each(newItems, function(index, item) {
                        addNewRssItem(item);
                   });
               });
          });
    });
}
