var menu = Ext.create('Ext.panel.Panel', {
		id : 'mainMenu',
		region:'west',
		title: '例子',
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
		items:[],
	});