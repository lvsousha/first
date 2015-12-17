Ext.define('File', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'model.name', type: 'string'},
        {name: 'model.path', type: 'string'},
        {name: 'text',  type: 'string'},
        {name: 'model',  type: 'object'}
    ]
});

var treeStore = Ext.create('Ext.data.TreeStore',{
	model:'File',
	autoLoad:true,
    proxy:{
    	type:'ajax',
    	url:'/first/content/file/remoteFiles',
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
	 title:'远程服务器文件',
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
			dataIndex: 'model',
			align: 'center'
		}],
	 dockedItems: [{
	        xtype: 'toolbar',
	        dock: 'top',
	        items: [{
	            xtype:'button',
	        	text: 'Get',
		        hidden:true,
	        	handler:function(){
	        		treePanel.getStore().proxy.url = '/first/content/ftp/remoteFiles'
	        		treePanel.getStore().load();
	        	}
	        },{
	        	xtype:'button',
	        	text: 'Download',
	        	handler:function(){
	        		var records = treePanel.getChecked();
	        		var path = '';
	        		Ext.each(records, function(value){
	        			path = value.data.model;
	        		})
	        		window.open("/first/content/file/download?path="+path);
//	        		Ext.Ajax.request({
//	                	url:'file/download',
//	                	params:model,
//	                	success: function(response, opts){
//	                		alert('success');
////	                		window.open("http://www.jb51.net"); 
////	                		var obj = Ext.decode(response.responseText);
////	                		if(obj.success == true){
////	                			window.location.href=obj.url;
////	                		}else{
////	                			alert(obj.errorMessage);
////	                		}               		
//	                	},
//	                    failure: function(response, opts) {
//	                        alert('failure');
//	                    }
//	                })
	        	}
	        }]
	 }],
	 listeners:{
		 itemclick: function(view, record, index, e, ep){
//			 window.location = record.data.model.uri;
		 }
	 }
});