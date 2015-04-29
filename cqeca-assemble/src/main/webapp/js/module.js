/**
 * Created by luolinj on 4/29/2015.
 */
var newsList;

$(function () {

	var dataLayer5 = {
		'boxH': '友情链接',
		'boxLink': 'http://luolinjia.com',
		'isMore': true,
		'picList': [{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		},{
			'link': 'http://luolinjia.com',
			'logo': 'images/logo/1.jpg'
		}]
	};
	_.renderBox($('.f-links-sponsor'), dataLayer5, '100%', 'sponsor');

    var dataModule = {
        'boxH': '公告栏',
        'boxLink': 'http://luolinjia.com',
        'isMore': true,
        'list': [{
            'link': 'http://baidu.com',
            'title': '祝贺第五届最受关注十大评选颁奖盛典圆满落a啊啊啊',
            'date': '2015-04-08'
        },{
            'link': 'http://baidu.com',
            'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
            'date': '2015-04-07'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        }]
    };
    _.renderBox($('.c-m-left-up'), dataModule, '240px', 'module');

    var dataDirect = {
        'boxH': '快捷通道',
        'isMore': false,
        'picList': [{
                'link': 'http://luolinjia.com',
                'img': 'images/mld1.jpg'
            }, {
                'link': 'http://luolinjia.com',
                'img': 'images/mld2.jpg'
            }]
    };
    _.renderBox($('.c-m-left-down'), dataDirect, '240px', 'direct');

    // module news list
    var _newsList = {
        'moduleH': '其它信息',
        'list': [{
            'link': 'http://baidu.com',
            'title': '祝贺第五届最受关注十大评选颁奖盛典圆满落a啊啊啊',
            'date': '2015-04-08'
        },{
            'link': 'http://baidu.com',
            'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
            'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
			'link': 'http://baidu.com',
			'title': '转发：浙江省商务厅什么等等我转发：浙江省商务厅什么等等我',
			'date': '2015-04-07'
		},{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        },{
            'link': 'http://baidu.com',
            'title': '电商先行，法律随后 ——记杭州市电子商务a厅什么等',
            'date': '2015-04-06'
        }]
    };
	newsList = _newsList;
    _.renderModulePostList($('.c-m-content-right'), newsList);
});

var _ = {
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
    renderModulePostList: function (self, data) {
//        var list = [], i = 0, size = data['list'].length;
//        list.push('<ul>');
//        for (; i < size; i++) {
//            var item = data['list'][i];
//            list.push('<li><div class="m-list-content-title"><a href="' + item['link'] + '" target="_self"><span><img src="images/list_li.gif" alt=""/></span>'  + item['title'] + '</a></div><div class="m-list-content-time">' + item['date'] + '</div></li>');
//        }
//        list.push('</ul>');

		var dom = '<div class="m-box"><div class="m-header"><img src="images/list_ico.gif" /><span>' + data['moduleH'] + '</span></div><div class="m-list"><div class="m-list-content"></div></div><div id="pagination"></div></div>';
        self.append(dom);
		_.renderPagination();
    },
	renderPagination: function () {
		$('#pagination').pagination(newsList['list'].length, {
			callback: _.pageselectCallback,
			prev_text: '前一页',
			next_text: '后一页'
		});
	},
	pageselectCallback: function (page_index, jq) {
		// Get number of elements per pagionation page from form
		var items_per_page = 12,
			max_elem = Math.min((page_index + 1) * items_per_page, newsList['list'].length),
			list = [];
		list.push('<ul>');
		// Iterate through a selection of the content and build an HTML string
		for (var i = page_index * items_per_page; i < max_elem; i++) {
			var item = newsList['list'][i];
			list.push('<li><div class="m-list-content-title"><a href="' + item['link'] + '" target="_self"><span><img src="images/list_li.gif" alt=""/></span>'  + item['title'] + '</a></div><div class="m-list-content-time">' + item['date'] + '</div></li>');

		}
		list.push('</ul>');

		// Replace old content with new content
		$('.m-list-content').empty().append(list.join(''));

		// Prevent click event propagation
		return false;
	}
};