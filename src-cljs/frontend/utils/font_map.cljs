(ns frontend.utils.font-map)

(def class->unicode
  {"fa-sitemap" \
   "fa-facebook-square" \
   "fa-volume-down" \
   "fa-phone-square" \
   "fa-facebook-f" \
   "fa-car" \
   "fa-shopping-cart" \
   "fa-arrow-circle-o-up" \
   "fa-repeat" \
   "fa-slack" \
   "fa-folder-open-o" \
   "fa-cc-visa" \
   "fa-archive" \
   "fa-male" \
   "fa-file-photo-o" \
   "fa-check" \
   "fa-apple" \
   "fa-file-picture-o" \
   "fa-envelope-square" \
   "fa-xing" \
   "fa-users" \
   "fa-briefcase" \
   "fa-truck" \
   "fa-life-ring" \
   "fa-twitch" \
   "fa-subscript" \
   "fa-navicon" \
   "fa-list" \
   "fa-square-o" \
   "fa-user-times" \
   "fa-cutlery" \
   "fa-tag" \
   "fa-angle-down" \
   "fa-cut" \
   "fa-rss" \
   "fa-wrench" \
   "fa-external-link-square" \
   "fa-file-text-o" \
   "fa-exclamation-circle" \
   "fa-wheelchair" \
   "fa-gift" \
   "fa-mortar-board" \
   "fa-file-o" \
   "fa-dropbox" \
   "fa-tty" \
   "fa-github-square" \
   "fa-dollar" \
   "fa-drupal" \
   "fa-eject" \
   "fa-file-archive-o" \
   "fa-code" \
   "fa-arrows-alt" \
   "fa-suitcase" \
   "fa-arrows-v" \
   "fa-diamond" \
   "fa-square" \
   "fa-ticket" \
   "fa-frown-o" \
   "fa-tumblr-square" \
   "fa-plane" \
   "fa-plus-square" \
   "fa-sellsy" \
   "fa-flag" \
   "fa-money" \
   "fa-file-image-o" \
   "fa-times-circle" \
   "fa-usd" \
   "fa-camera" \
   "fa-arrow-circle-o-right" \
   "fa-flask" \
   "fa-file-code-o" \
   "fa-sort-numeric-desc" \
   "fa-futbol-o" \
   "fa-bed" \
   "fa-paperclip" \
   "fa-star-half" \
   "fa-thumbs-o-down" \
   "fa-binoculars" \
   "fa-maxcdn" \
   "fa-reply-all" \
   "fa-arrow-right" \
   "fa-dedent" \
   "fa-chevron-right" \
   "fa-photo" \
   "fa-th-list" \
   "fa-music" \
   "fa-sort" \
   "fa-files-o" \
   "fa-random" \
   "fa-filter" \
   "fa-angle-right" \
   "fa-share-alt" \
   "fa-align-left" \
   "fa-barcode" \
   "fa-question-circle" \
   "fa-minus" \
   "fa-remove" \
   "fa-align-center" \
   "fa-arrows" \
   "fa-ambulance" \
   "fa-beer" \
   "fa-html5" \
   "fa-check-square" \
   "fa-vimeo-square" \
   "fa-fax" \
   "fa-pencil-square-o" \
   "fa-gavel" \
   "fa-rebel" \
   "fa-rotate-left" \
   "fa-minus-circle" \
   "fa-phone" \
   "fa-venus-mars" \
   "fa-undo" \
   "fa-bell-slash-o" \
   "fa-skyatlas" \
   "fa-tencent-weibo" \
   "fa-venus" \
   "fa-dashboard" \
   "fa-star-half-empty" \
   "fa-paper-plane-o" \
   "fa-bug" \
   "fa-bitcoin" \
   "fa-ge" \
   "fa-line-chart" \
   "fa-columns" \
   "fa-gbp" \
   "fa-minus-square-o" \
   "fa-play-circle" \
   "fa-hdd-o" \
   "fa-heart" \
   "fa-caret-right" \
   "fa-jsfiddle" \
   "fa-font" \
   "fa-empire" \
   "fa-behance" \
   "fa-chevron-down" \
   "fa-try" \
   "fa-pie-chart" \
   "fa-twitter-square" \
   "fa-microphone" \
   "fa-star" \
   "fa-pinterest" \
   "fa-angle-double-right" \
   "fa-pied-piper" \
   "fa-youtube-square" \
   "fa-qrcode" \
   "fa-paw" \
   "fa-subway" \
   "fa-gittip" \
   "fa-pause" \
   "fa-chevron-circle-left" \
   "fa-folder" \
   "fa-xing-square" \
   "fa-transgender" \
   "fa-backward" \
   "fa-train" \
   "fa-sort-amount-desc" \
   "fa-medkit" \
   "fa-soundcloud" \
   "fa-stumbleupon" \
   "fa-meanpath" \
   "fa-compress" \
   "fa-level-down" \
   "fa-paste" \
   "fa-scissors" \
   "fa-forward" \
   "fa-university" \
   "fa-chevron-circle-up" \
   "fa-newspaper-o" \
   "fa-sort-alpha-desc" \
   "fa-image" \
   "fa-recycle" \
   "fa-certificate" \
   "fa-floppy-o" \
   "fa-turkish-lira" \
   "fa-mars-double" \
   "fa-gamepad" \
   "fa-puzzle-piece" \
   "fa-angle-double-up" \
   "fa-trello" \
   "fa-sort-down" \
   "fa-github-alt" \
   "fa-flag-checkered" \
   "fa-file-zip-o" \
   "fa-linux" \
   "fa-lastfm-square" \
   "fa-git" \
   "fa-thumb-tack" \
   "fa-joomla" \
   "fa-calculator" \
   "fa-circle-thin" \
   "fa-crop" \
   "fa-caret-square-o-left" \
   "fa-flag-o" \
   "fa-chevron-circle-down" \
   "fa-exclamation" \
   "fa-digg" \
   "fa-rupee" \
   "fa-link" \
   "fa-bell-o" \
   "fa-linkedin-square" \
   "fa-sun-o" \
   "fa-fire" \
   "fa-adn" \
   "fa-credit-card" \
   "fa-anchor" \
   "fa-print" \
   "fa-pinterest-square" \
   "fa-sliders" \
   "fa-facebook-official" \
   "fa-share" \
   "fa-arrow-circle-left" \
   "fa-road" \
   "fa-dashcube" \
   "fa-play" \
   "fa-volume-up" \
   "fa-qq" \
   "fa-caret-down" \
   "fa-file-movie-o" \
   "fa-check-circle" \
   "fa-h-square" \
   "fa-stethoscope" \
   "fa-server" \
   "fa-exclamation-triangle" \
   "fa-cog" \
   "fa-coffee" \
   "fa-envelope" \
   "fa-flickr" \
   "fa-hand-o-down" \
   "fa-eye" \
   "fa-film" \
   "fa-wechat" \
   "fa-upload" \
   "fa-arrows-h" \
   "fa-lightbulb-o" \
   "fa-folder-open" \
   "fa-ellipsis-v" \
   "fa-bitbucket-square" \
   "fa-stack-exchange" \
   "fa-volume-off" \
   "fa-superscript" \
   "fa-quote-left" \
   "fa-mars-stroke" \
   "fa-life-buoy" \
   "fa-cart-arrow-down" \
   "fa-twitter" \
   "fa-connectdevelop" \
   "fa-tumblr" \
   "fa-heart-o" \
   "fa-umbrella" \
   "fa-desktop" \
   "fa-user-md" \
   "fa-weibo" \
   "fa-copyright" \
   "fa-close" \
   "fa-strikethrough" \
   "fa-rotate-right" \
   "fa-arrow-left" \
   "fa-instagram" \
   "fa-cloud-download" \
   "fa-sheqel" \
   "fa-arrow-circle-o-down" \
   "fa-smile-o" \
   "fa-reddit" \
   "fa-level-up" \
   "fa-sort-alpha-asc" \
   "fa-bolt" \
   "fa-share-alt-square" \
   "fa-life-saver" \
   "fa-steam" \
   "fa-download" \
   "fa-folder-o" \
   "fa-keyboard-o" \
   "fa-trash" \
   "fa-sort-asc" \
   "fa-unsorted" \
   "fa-fighter-jet" \
   "fa-paypal" \
   "fa-file-pdf-o" \
   "fa-thumbs-o-up" \
   "fa-expand" \
   "fa-list-alt" \
   "fa-spotify" \
   "fa-btc" \
   "fa-tachometer" \
   "fa-align-right" \
   "fa-google-plus" \
   "fa-plus-square-o" \
   "fa-deviantart" \
   "fa-bell" \
   "fa-reorder" \
   "fa-caret-left" \
   "fa-terminal" \
   "fa-pencil" \
   "fa-globe" \
   "fa-sort-up" \
   "fa-eur" \
   "fa-sign-in" \
   "fa-paint-brush" \
   "fa-mail-reply" \
   "fa-hacker-news" \
   "fa-step-forward" \
   "fa-eraser" \
   "fa-star-half-o" \
   "fa-unlink" \
   "fa-bookmark" \
   "fa-file-excel-o" \
   "fa-eyedropper" \
   "fa-map-marker" \
   "fa-refresh" \
   "fa-cc-discover" \
   "fa-outdent" \
   "fa-yen" \
   "fa-life-bouy" \
   "fa-bell-slash" \
   "fa-cart-plus" \
   "fa-calendar" \
   "fa-arrow-circle-down" \
   "fa-sort-numeric-asc" \
   "fa-hand-o-left" \
   "fa-building" \
   "fa-retweet" \
   "fa-warning" \
   "fa-arrow-down" \
   "fa-jpy" \
   "fa-long-arrow-down" \
   "fa-compass" \
   "fa-search" \
   "fa-microphone-slash" \
   "fa-area-chart" \
   "fa-euro" \
   "fa-circle-o-notch" \
   "fa-unlock-alt" \
   "fa-pied-piper-alt" \
   "fa-cloud-upload" \
   "fa-circle" \
   "fa-won" \
   "fa-krw" \
   "fa-question" \
   "fa-comment" \
   "fa-meh-o" \
   "fa-institution" \
   "fa-rub" \
   "fa-inr" \
   "fa-magic" \
   "fa-skype" \
   "fa-street-view" \
   "fa-caret-up" \
   "fa-star-half-full" \
   "fa-plug" \
   "fa-cc-mastercard" \
   "fa-angle-double-left" \
   "fa-mars" \
   "fa-viacoin" \
   "fa-sort-amount-asc" \
   "fa-cubes" \
   "fa-shekel" \
   "fa-bitbucket" \
   "fa-graduation-cap" \
   "fa-yelp" \
   "fa-leaf" \
   "fa-hospital-o" \
   "fa-openid" \
   "fa-arrow-circle-o-left" \
   "fa-arrow-circle-right" \
   "fa-fast-backward" \
   "fa-signal" \
   "fa-bookmark-o" \
   "fa-user-secret" \
   "fa-female" \
   "fa-mail-forward" \
   "fa-picture-o" \
   "fa-power-off" \
   "fa-hand-o-up" \
   "fa-toggle-left" \
   "fa-text-width" \
   "fa-behance-square" \
   "fa-lemon-o" \
   "fa-clock-o" \
   "fa-toggle-off" \
   "fa-dot-circle-o" \
   "fa-sort-desc" \
   "fa-git-square" \
   "fa-cc-stripe" \
   "fa-plus" \
   "fa-cc" \
   "fa-steam-square" \
   "fa-exchange" \
   "fa-forumbee" \
   "fa-times-circle-o" \
   "fa-file-word-o" \
   "fa-soccer-ball-o" \
   "fa-code-fork" \
   "fa-header" \
   "fa-search-plus" \
   "fa-pencil-square" \
   "fa-linkedin" \
   "fa-minus-square" \
   "fa-angle-double-down" \
   "fa-clipboard" \
   "fa-align-justify" \
   "fa-angle-up" \
   "fa-stumbleupon-circle" \
   "fa-youtube" \
   "fa-send" \
   "fa-thumbs-up" \
   "fa-crosshairs" \
   "fa-foursquare" \
   "fa-toggle-on" \
   "fa-home" \
   "fa-hotel" \
   "fa-spoon" \
   "fa-space-shuttle" \
   "fa-lastfm" \
   "fa-legal" \
   "fa-vine" \
   "fa-check-circle-o" \
   "fa-file-audio-o" \
   "fa-flash" \
   "fa-ils" \
   "fa-eye-slash" \
   "fa-external-link" \
   "fa-info-circle" \
   "fa-long-arrow-up" \
   "fa-automobile" \
   "fa-book" \
   "fa-tags" \
   "fa-long-arrow-right" \
   "fa-arrow-up" \
   "fa-windows" \
   "fa-circle-o" \
   "fa-rocket" \
   "fa-google-plus-square" \
   "fa-laptop" \
   "fa-quote-right" \
   "fa-cloud" \
   "fa-mars-stroke-h" \
   "fa-user" \
   "fa-venus-double" \
   "fa-at" \
   "fa-inbox" \
   "fa-css3" \
   "fa-bus" \
   "fa-magnet" \
   "fa-bullhorn" \
   "fa-taxi" \
   "fa-gears" \
   "fa-bars" \
   "fa-stack-overflow" \
   "fa-edit" \
   "fa-simplybuilt" \
   "fa-group" \
   "fa-comments" \
   "fa-key" \
   "fa-database" \
   "fa-tree" \
   "fa-file-powerpoint-o" \
   "fa-mail-reply-all" \
   "fa-paper-plane" \
   "fa-pagelines" \
   "fa-star-o" \
   "fa-angle-left" \
   "fa-bicycle" \
   "fa-paragraph" \
   "fa-step-backward" \
   "fa-wordpress" \
   "fa-file" \
   "fa-list-ol" \
   "fa-weixin" \
   "fa-calendar-o" \
   "fa-ioxhost" \
   "fa-ban" \
   "fa-yahoo" \
   "fa-tasks" \
   "fa-italic" \
   "fa-vk" \
   "fa-check-square-o" \
   "fa-plus-circle" \
   "fa-file-video-o" \
   "fa-asterisk" \
   "fa-cc-amex" \
   "fa-cab" \
   "fa-motorcycle" \
   "fa-gratipay" \
   "fa-bomb" \
   "fa-birthday-cake" \
   "fa-unlock" \
   "fa-facebook" \
   "fa-mars-stroke-v" \
   "fa-chevron-circle-right" \
   "fa-send-o" \
   "fa-info" \
   "fa-support" \
   "fa-gear" \
   "fa-table" \
   "fa-trophy" \
   "fa-comments-o" \
   "fa-th-large" \
   "fa-ruble" \
   "fa-android" \
   "fa-search-minus" \
   "fa-google-wallet" \
   "fa-arrow-circle-up" \
   "fa-bullseye" \
   "fa-github" \
   "fa-neuter" \
   "fa-cube" \
   "fa-transgender-alt" \
   "fa-ship" \
   "fa-delicious" \
   "fa-dribbble" \
   "fa-building-o" \
   "fa-headphones" \
   "fa-location-arrow" \
   "fa-thumbs-down" \
   "fa-shield" \
   "fa-stop" \
   "fa-codepen" \
   "fa-chain" \
   "fa-heartbeat" \
   "fa-bar-chart-o" \
   "fa-rmb" \
   "fa-youtube-play" \
   "fa-history" \
   "fa-bank" \
   "fa-caret-square-o-down" \
   "fa-fast-forward" \
   "fa-share-square-o" \
   "fa-underline" \
   "fa-hand-o-right" \
   "fa-reply" \
   "fa-cny" \
   "fa-toggle-up" \
   "fa-buysellads" \
   "fa-tint" \
   "fa-text-height" \
   "fa-bold" \
   "fa-ellipsis-h" \
   "fa-cc-paypal" \
   "fa-wifi" \
   "fa-renren" \
   "fa-child" \
   "fa-moon-o" \
   "fa-pinterest-p" \
   "fa-list-ul" \
   "fa-language" \
   "fa-indent" \
   "fa-play-circle-o" \
   "fa-trash-o" \
   "fa-chain-broken" \
   "fa-copy" \
   "fa-camera-retro" \
   "fa-cogs" \
   "fa-file-sound-o" \
   "fa-whatsapp" \
   "fa-mobile" \
   "fa-comment-o" \
   "fa-lock" \
   "fa-ra" \
   "fa-mobile-phone" \
   "fa-long-arrow-left" \
   "fa-sign-out" \
   "fa-google" \
   "fa-chevron-left" \
   "fa-mercury" \
   "fa-toggle-right" \
   "fa-genderless" \
   "fa-user-plus" \
   "fa-envelope-o" \
   "fa-file-text" \
   "fa-rss-square" \
   "fa-adjust" \
   "fa-bar-chart" \
   "fa-rouble" \
   "fa-shirtsinbulk" \
   "fa-angellist" \
   "fa-th" \
   "fa-leanpub" \
   "fa-share-square" \
   "fa-times" \
   "fa-save" \
   "fa-spinner" \
   "fa-caret-square-o-up" \
   "fa-toggle-down" \
   "fa-video-camera" \
   "fa-slideshare" \
   "fa-fire-extinguisher" \
   "fa-medium" \
   "fa-reddit-square" \
   "fa-caret-square-o-right" \
   "fa-chevron-up" \
   "fa-tablet" \})