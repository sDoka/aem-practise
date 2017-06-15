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
        addNewRssItem = function(index, rssItemJsonObject) {
            var $lastRssItem = $rssAccordionGroup.children().last(),
                $newRssItem = $lastRssItem.clone(),
                $titleElement = $newRssItem.find('h4'),
                $descriptionElement = $newRssItem.find('.item-text'),
                $collapseBlock = $newRssItem.find("div[id^='collapse']"),
                lastIndex = parseInt($collapseBlock.attr('index')),
                $headerBlock = $newRssItem.find("div[href^='#collapse']"),
                currentIndex = lastIndex + index + 1;

            $collapseBlock.attr('id', 'collapse' + currentIndex);
            $headerBlock.attr('href', '#collapse' + currentIndex);
            $titleElement.html(rssItemJsonObject.title);
            $descriptionElement.html(rssItemJsonObject.description);

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
                        addNewRssItem(index, item);
                   });
               });
          });
    });
}
