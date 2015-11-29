Ext.define('File', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'model.name', type: 'string'},
        {name: 'model.timestamp',  type: 'string'},
        {name: 'text',  type: 'string'},
        {name: 'model',  type: 'object'}
    ]
});

var treeStore = Ext.create('Ext.data.TreeStore',{
	model:'File',
	autoLoad:false,
    proxy:{
    	type:'ajax',
    	url:'',
    	reader:{
    		type:'json',
    		root:''
    	}
    },
   	root: {
        text : '文件',
        expanded : true,
        childern:'childern'
   	}
});

var treePanel = Ext.create('Ext.tree.Panel',{
	 title:'ftp服务器文件',
	 flex:7,
	 width: '100%',
	 store:treeStore,
	 columns: [{
			text: '树形',
			xtype: 'treecolumn',
			width:200,
			dataIndex: 'text'
		},{
			text: '文件名',
			dataIndex: 'model.name',
			align: 'center'
		},{
			text: 'timestamp',
			dataIndex: 'model.timestamp',
			align: 'center'
		}],
	 dockedItems: [{
	        xtype: 'toolbar',
	        dock: 'top',
	        items: [{
	            xtype:'button',
	        	text: 'Get',
	        	handler:function(){
	        		treePanel.getStore().proxy.url = '/first/content/ftp/remoteFiles'
	        		treePanel.getStore().load();
	        	}
	        }]
	 }],
	 listeners:{
		 itemclick: function(view, record, index, e, ep){
			 window.location = record.data.model.uri;
		 }
	 }
});