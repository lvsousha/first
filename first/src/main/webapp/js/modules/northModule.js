var north = Ext.create('Ext.panel.Panel',{
		id : 'North',
		region:'north',
		split: true,
		collapsible: true,
		height:100,
		layout:'fit',
		items:{
        	xtype:'image',
        	id:'code_image',
        	src:'../images/background.jpg',
        	alt:'aaa'
        }
	});