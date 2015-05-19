/**
 * Created by luolinj on 4/7/2015.
 */
var newsList;

$(function () {
	_.bindSearch();
	var menu = $('#menu');
    _.bindAboutEvent(menu);
	_.switchModule($('li', menu));

    _.renderBox($('.c-layer1-left'), dataLayer1, '358px', 'post');
    _.renderPicBox($('.c-layer1-right'));

   
    var dataLayer21 = {
        'boxH': '公告栏',
        'isMore': false,
        'content': '南岸区电子商务协会是由重庆市南岸区商圈管委会、重庆国际电子商务产业园、重庆易网集团、新锐数字营销机构、淘会场、种钱网等由重庆市南岸区从事电子商务研究开发、应用等相关领域的企事业单位自愿结合组成的地方性、联合性、非营利性社团组织，经重庆市民政局批准，2015年1月正式成立，其主管部门是重庆市南岸区商圈管委会。协会主要是从事信息化和电子商务服务、研究、培训、购买政府服务等相关工作。在重庆市南岸区商圈管委会指导下，为进一步促进电子商务发展，推进行业内电子商务经营、服务水平的提高，充分利用全社会电子商务行业的资源，通过开展丰富的咨询、培训、交流、沙龙等活动，进一步团结会员、服务会员、服务行业、服务政府、服务社会。'
    };
    _.renderBox($('.c-layer2-left'), dataLayer2, '358px', 'post');
    _.renderBox($('.c-layer2-center'), dataLayer21, '530px', 'about');

	_.renderBox($('.c-layer4-left'), dataLayer41, '445px', 'post');
	_.renderBox($('.c-layer4-right'), dataLayer42, '445px', 'post');

	console.log(dataLayer3);
	_.renderPP($('.c-layer3-list'), dataLayer3);

	var dataLayer5 = {
		'boxH': '友情链接',
		'boxLink': '',
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
		}
		]
	};
	_.renderBox($('.f-links-sponsor'), dataLayer5, '100%', 'sponsor');

});

var req = {
	getSearchResult: function (options, callback) {
		$.ajax($.extend({
			type: 'POST',
			url: '/cqeca/search_news',
			dataType: 'JSON'
		}, options, true)).done(function(data){
			if (data && $.isFunction(callback)) {
				callback(data);
			}
		});
	}
};

var _ = {
    bindAboutEvent: function (self) {
        $('.more', self).parent().mouseover(function () {
            $('.more', $(this)).show();
        }).mouseleave(function () {
            $('.more', $(this)).hide();
        });
    },
    renderBox: function (self, data, width, flag) {
		var innerDom = '';
		if ('post' === flag) {
			innerDom = _.renderPostList(data, false).join('');
		} else if ('about' === flag) {
			innerDom = _.renderAbout(data);
		} else if ('sponsor' === flag) {
			innerDom = _.renderSponsors(data).join('');
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
    renderAbout: function (data) {
		return '<div class="b-about"><img src="images/logo.png" alt=""/>' + data['content'] + '</div>';
    },
    renderPicBox: function (o) {
        o.picScroll({
            size: {'height': '270px', width: '530px'},
            color: '#ddd', //rgba(221, 221, 221, 0.4) it doesn't work in IE8-
            hovercolor: '#C70909',
            time: 2000,
            hasNumber: false
        });
    },
	renderPP: function (self, data) {
		var list = [], i = 0, size = data.length;
		for (; i < size; i++) {
			var item = data[i];
			list.push('<li><a href="' + item['link'] + '" target="_self"><img src="' + item['img'] + '" alt=""/></a></li>');
		}

		var dom = '<ul>' + list.join('') + '</ul>';
		self.append(dom);
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
	switchModule: function (o) {
		o.click(function () {
			var thiz = $(this);
			if ($('.m-selected').length === 1) {
				o.removeClass('m-selected');
			}
			thiz.toggleClass('m-selected');
		});
	},
	bindSearch: function () {
		$('#search').on('click', function () {
			console.log('search function!');
			var searchObj = $('#searchText'),
				text = searchObj.val(),
				patrn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;

			if (patrn.test(text)) {
				alert("提示信息：您输入的搜索关键字含有非法字符！");
				searchObj.focus();
				return false;
			}
			console.log('text:' + text);
			req.getSearchResult({'data': {'searchText' : text}}, function (data) {
				// 你可以这里重定向，或者不要这个callback，直接你后台重定向
			});
		});
	}

};