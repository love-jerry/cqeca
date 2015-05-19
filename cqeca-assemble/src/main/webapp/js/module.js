/**
 * Created by luolinj on 4/29/2015.
 */


$(function () {

	var menu = $('#menu');
	_.bindAboutEvent($('#menu'));
	_.switchModule($('li', menu));

	var dataLayer5 = {
		'boxH': '友情链接',
		'boxLink': 'http://luolinjia.com',
		'isMore': true,
		'picList': [{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},
		{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},
		{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},
		{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},
		{
			'link': '',
			'logo': 'images/logo/1.jpg'
		},
		{
			'link': '',
			'logo': 'images/logo/1.jpg'
		}
		]
	};
	_.renderBox($('.f-links-sponsor'), dataLayer5, '100%', 'sponsor');

    _.renderBox($('.c-m-left-up'), dataModule, '240px', 'module');

    var dataDirect = {
        'boxH': '快捷通道',
        'isMore': false,
        'picList': [{
                'link': '',
                'img': 'images/mld1.jpg'
            }, {
                'link': '',
                'img': 'images/mld2.jpg'
            }]
    };
    _.renderBox($('.c-m-left-down'), dataDirect, '240px', 'direct');

    // module news list
    
	var pageOptions = {
		'totalNo': totalNo,
		'perNo': perNo,
		'cPage': cPage
	};
    _.renderModulePostList($('.c-m-content-right'), moduleH, pageOptions);
});

var req = {
	getDetailPage: function (options, callback) {
		$.ajax($.extend({
			type: 'POST',
			url: '/cqeca/find_news_data',  // /getDetailPage
			dataType: 'JSON'
		}, options, true)).done(function(data){
			if (data && $.isFunction(callback)) {
				callback(data);
			}
		});
	}
};

var _ = {
	setModuleH: function (o, m) {
		if ($('.m-selected').length === 1) {
			o.removeClass('m-selected');
		}
		o.each(function(){
			var thiz = $(this);
			if (m === thiz.text()) {
				thiz.addClass('m-selected');
			}
		});
	},
	bindAboutEvent: function (self) {
		$('.more', self).parent().mouseover(function () {
			$('.more', $(this)).show();
		}).mouseleave(function () {
			$('.more', $(this)).hide();
		});
	},
	switchModule: function (o) {
		o.click(function () {
			var thiz = $(this);
			if ($('.m-selected').length === 1) {
				o.removeClass('m-selected');
			}
			thiz.toggleClass('m-selected');
		});
	},
    renderBox: function (self, data, width, flag) {
		var innerDom = '';
		if ('sponsor' === flag) {
			innerDom = _.renderSponsors(data).join('');
		} else if ('module' === flag) {
            innerDom = _.renderPostList(data, true).join('');
        } else if ('direct' === flag) {
            innerDom = _.renderDirectPass(data).join('');
        }

        var dom = '<div class="b-box"><div class="b-header"><span>' + data['boxH'] + '</span>' + (data['isMore'] ? '<a href="' + data['boxLink'] + '" target="_self"><img src="images/more.gif" alt=""/></a>' : '') + '</div><div class="b-list"><div class="b-list-content">' + innerDom + '</div></div></div>';
        self.append(dom);
        $('.b-box', self).css({'width': width});
    },
    renderPostList: function (data, isModule) {
        var list = [], i = 0, size = data['list'].length;
        list.push('<ul>');
        for (; i < size; i++) {
            var item = data['list'][i];
            list.push('<li><div class="b-list-content-title"><a href="' + item['link'] + '" target="_self"><span><img src="images/ico.jpg" alt=""/></span>'  + item['title'] + '</a></div>' + (isModule ? '' : '<div class="b-list-content-time">' + item['date'] + '</div>') + '</li>');
        }
        list.push('</ul>');
        return list;
    },
    renderSponsors: function (data) {
		var list = [], i = 0, size = data['picList'].length;
		list.push('<ul>');
		for (; i < size; i++) {
			var item = data['picList'][i];
			list.push('<li><a href="' + item['link'] + '"><img src="' + item['logo'] + '" alt=""/></a></li>');
		}
		list.push('</ul>');
		return list;
    },
    renderDirectPass: function (data) {
        var list = [], i = 0, size = data['picList'].length;
        for (; i < size; i++) {
            var item = data['picList'][i];
            list.push('<div class="m5"><a href="' + item['link'] + '"><img src="' + item['img'] + '" alt=""/></a></div>');
        }
        return list;
    },
    renderModulePostList: function (self, data, options) {

		var dom = '<div class="m-box"><div class="m-header"><img src="images/list_ico.gif" /><span>' + data + '</span></div><div class="m-list"><div class="m-list-content"></div></div><div id="pagination"></div></div>';
        self.append(dom);
		_.renderPagination(options['totalNo'], options['perNo'], options['cPage']);
    },
	renderPagination: function (totalNo, perNo, cPage) {
		_.pageselectCallback(0);
		// init the pagination
		$("#pagination").pagination(totalNo, {
			items_per_page: perNo, // 这里为每页显示的条数
			num_display_entries: 5,
			current_page: cPage,
			callback: _.pageselectCallback,
			prev_text: "前一页",
			next_text: "后一页"
		});
	},
	pageselectCallback: function (page_index) {

		req.getDetailPage({data: {'page': parseInt(page_index), 'moduleH': moduleH}}, function (data) {
			var list = [], i = 0, size = data.length;
			list.push('<ul>');
			for (; i < size; i++) {
				var item = data[i];
				list.push('<li><div class="m-list-content-title"><a href="' + item['link'] + '" target="_self"><span><img src="images/list_li.gif" alt=""/></span>'  + item['title'] + '</a></div><div class="m-list-content-time">' + item['date'] + '</div></li>');
			}
			list.push('</ul>');
			// Replace old content with new content
			$('.m-list-content').empty().append(list.join(''));

			// Prevent click event propagation
			return false;
		});


//		// Get number of elements per pagionation page from form
//		var items_per_page = 12,
//			max_elem = Math.min((page_index + 1) * items_per_page, newsList['list'].length),
//			list = [];
//		list.push('<ul>');
//		// Iterate through a selection of the content and build an HTML string
//		for (var i = page_index * items_per_page; i < max_elem; i++) {
//			var item = newsList['list'][i];
//			list.push('<li><div class="m-list-content-title"><a href="' + item['link'] + '" target="_self"><span><img src="images/list_li.gif" alt=""/></span>'  + item['title'] + '</a></div><div class="m-list-content-time">' + item['date'] + '</div></li>');
//
//		}
//		list.push('</ul>');
//
//		// Replace old content with new content
//		$('.m-list-content').empty().append(list.join(''));
//
//		// Prevent click event propagation
//		return false;
	}
};