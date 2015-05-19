/**
 * Created by luolinj on 4/29/2015.
 */

$(function () {

	var menu = $('#menu');
	_.bindAboutEvent(menu);

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
	//_.setModuleH($('li', $('#menu')), moduleH);
});

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
    }
};