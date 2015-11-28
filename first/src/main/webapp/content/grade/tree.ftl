Ext.define('Menu', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'model.menuname', type: 'string'},
        {name: 'model.uri',  type: 'string'},
        {name: 'text',  type: 'string'},
        {name: 'model',  type: 'object'}
    ]
});

var treeStore = Ext.create('Ext.data.TreeStore',{
	model:'Menu',
    proxy:{
    	type:'ajax',
    	url:'/first/content/example/getTree.html',
    	reader:{
    		type:'json',
    		root:''
    	}
    },
   	root: {
        text : '管理菜单',
        expanded : true,
        childern:'childern'
   	}
});

var treePanel = Ext.create('Ext.tree.Panel',{
	 title:'菜单栏',
	 store:treeStore,
//	 columns: [{
//			text: '树形',
//			xtype: 'treecolumn',
//			width:200,
//			dataIndex: 'text'
//		},{
//			text: 'menuname',
//			dataIndex: 'model.menuname',
//			align: 'center'
//		},{
//			text: 'uri',
//			dataIndex: 'model.uri',
//			align: 'center'
//		}],
	 listeners:{
		 itemclick: function(view, record, index, e, ep){
			 window.location = record.data.model.uri;
		 }
	 }
});

var treeTab = {
		 title:'Tree',
		 layout:'fit',
		 items:treePanel
	 };