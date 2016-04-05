var basicStore = Ext.create('Ext.data.Store', {
    	     model: 'Basic',
    	     pageSize:25,
    	     proxy: {
    	         type: 'ajax',
    	         url: '/first/content/hbjh/basic_list',
    	         reader: {
    	             type: 'json',
    	             root: 'basics.models',
    	             totalProperty: 'basics.total'
    	         }
    	     },
    	     autoLoad: true,
    	     listeners:{
    	    		load: function(store, records){
//    	    			alert(Ext.isArray(records));
    	    		}
    	    	}
    	 });

var basicGrid = Ext.create('Ext.grid.Panel',{
	 store:basicStore,
	 layout:'fit',
	 columns:[{
		 header:'序号',
		 dataIndex:'orderno',
		 align:'center',
		 flex:1
	 },{
		 header:'企业名称',
		 dataIndex:'entname',
		 align:'center',
		 flex:1
	 },{
		 header:'法人',
		 dataIndex:'frname',
		 align:'center',
		 flex:1
	 },{
		 header:'身份证',
		 dataIndex:'cardnum',
		 align:'center',
		 flex:1
	 },{
		 header:'工商注册码',
		 dataIndex:'regno',
		 align:'center',
		 flex:1
	 }],
	 dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: basicStore,   // GridPanel使用相同的数据源
	        dock: 'bottom',
	        displayInfo: true
	    }],
});

var basicTab = {
		 title:'企业',
		 layout:'fit',
		 items:basicGrid,
		 listeners:{
	    	 activate:function(e,opts){
	    		 if(e.items.getAt(0).store.count() == 0)
	    			 e.items.getAt(0).store.load();
	    	 }
	     }
	 };