var userStore = Ext.create('Ext.data.Store', {
    	     model: 'User',
    	     pageSize:10,
    	     proxy: {
    	         type: 'ajax',
    	         url: '/first/content/user/list.html',
    	         reader: {
    	             type: 'json',
    	             root: 'user'
    	         }
    	     },
    	     autoLoad: false,
    	     listeners:{
    	    		load: function(store, records){
//    	    			alert(Ext.isArray(records));
    	    		}
    	    	}
    	 });

var userGrid = Ext.create('Ext.grid.Panel',{
	 store:userStore,
	 layout:'fit',
	 columns:[{
		 header:'序号',
		 dataIndex:'id',
		 align:'center',
		 flex:1
	 },{
		 header:'用户名',
		 dataIndex:'username',
		 align:'center',
		 flex:1
	 },{
		 header:'密码',
		 dataIndex:'password',
		 align:'center',
		 flex:1
	 }],
	 dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: userStore,   // GridPanel使用相同的数据源
	        dock: 'bottom',
	        displayInfo: true
	    }],
});

var userTab = {
		 title:'用户',
		 layout:'fit',
		 items:userGrid,
		 listeners:{
	    	 activate:function(e,opts){
	    		 if(e.items.getAt(0).store.count() == 0)
	    			 e.items.getAt(0).store.load();
	    	 }
	     }
	 };