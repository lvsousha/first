var menuStore = Ext.create('Ext.data.TreeStore',{
	model:'Menu',
	autoLoad:true,
    proxy:{
    	type:'ajax',
    	url:'/first/content/menu/getTree',
    	reader:{
    		type:'json',
    		root:''
    	}
    },
   	root: {
//        text : '菜单',
        expanded : true,
        childern:'childern'
   	}
});

var menu = Ext.create('Ext.panel.Panel', {
		id : 'mainMenu',
		region:'west',
		title: '菜单栏',
		split: true,
		collapsible: true,
		width: 150,
		minWidth: 50,
		maxWidth: 300,
		stateful: true,
		layout: {
			type: 'accordion',
			titleCollapse: true,
			animate: true
		},
		items:[{
			xtype:'treepanel',
			store:menuStore,
			rootVisible:false,
			items:{
				dataIndex:'model.url'
			},
			listeners:{
				select: function(e, record, index, eopts){
					window.location = record.data.model.url;
				}
			}
		}],
});