/**
 * Created by luolinj on 4/7/2015.
 */
$(function () {
   
    _.renderBox($('.c-layer1-left'), dataLayer1, '358px', 'post');
    _.renderPicBox($('.c-layer1-right'));

  
    _.renderBox($('.c-layer2-left'), dataLayer2, '358px', 'post');
    _.renderBox($('.c-layer2-center'), dataLayer2, '358px', 'about');

	_.renderBox($('.c-layer4-left'), dataLayer4, '445px', 'post');
	_.renderBox($('.c-layer4-right'), dataLayer4, '445px', 'post');

	_.renderPP($('.c-layer3-list'), dataLayer3);

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
    var newsList = {
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
    _.renderModulePostList($('.c-m-content-right'), newsList);
});

var _ = {
    renderBox: function (self, data, width, flag) {
		var innerDom = '';
		if ('post' === flag) {
			innerDom = _.renderPostList(data, false).join('');
		} else if ('about' === flag) {
			innerDom = _.renderAbout(data);
		} else if ('sponsor' === flag) {
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
    renderAbout: function (data) {
		return '<div class="b-about"><img src="images/biaozhi.jpg" alt=""/>' + data['content'] + '</div>';
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
		var list = [], i = 0, size = data['postList'].length;
		for (; i < size; i++) {
			var item = data['postList'][i];
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
    renderDirectPass: function (data) {
        var list = [], i = 0, size = data['picList'].length;
        for (; i < size; i++) {
            var item = data['picList'][i];
            list.push('<div class="m5"><a href="' + item['link'] + '"><img src="' + item['img'] + '" alt=""/></a></div>');
        }
        return list;
    },
    renderModulePostList: function (self, data) {
        var list = [], i = 0, size = data['list'].length;
        list.push('<ul>');
        for (; i < size; i++) {
            var item = data['list'][i];
            list.push('<li><div class="m-list-content-title"><a href="' + item['link'] + '" target="_self"><span><img src="images/list_li.gif" alt=""/></span>'  + item['title'] + '</a></div><div class="m-list-content-time">' + item['date'] + '</div></li>');
        }
        list.push('</ul>');

        var dom = '<div class="m-box"><div class="m-header"><img src="images/list_ico.gif" /><span>' + data['moduleH'] + '</span></div><div class="m-list"><div class="m-list-content">' + list.join('') + '</div></div></div>';
        self.append(dom);
    }
};