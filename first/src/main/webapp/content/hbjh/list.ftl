var basicStore = Ext.create('Ext.data.Store', {
    	     model: 'Basic',
    	     pageSize:25,
    	     proxy: {
    	         type: 'ajax',
    	         url: '/first/content/hbjh/basic_list',
    	         reader: {
    	             type: 'json',
    	             root: 'model.basics',
    	             totalProperty: 'model.total'
    	         }
    	     },
    	     autoLoad: true,
    	     listeners:{
    	    		load: function(store, records){
    	        		var selectModel = basicGrid.getSelectionModel();
    	                if(store.prevPage)
    	                	basicStore.selectRow = records.length - 1;
    	                else
    	                	basicStore.selectRow = 0;
    	                if(store.getCount() > 0){
    	                    if(selectModel.getSelection().length === 0){
    	                    	basicGrid.getSelectionModel().deselectAll();
    	                  		selectModel.select(store.getAt(basicStore.selectRow));
    	                    }
    	                }
    	                else{
    	                    var paging = basicGrid.down('pagingtoolbar');
    	                    var pageData = paging.getPageData();
    	                    if(pageData.currentPage > 1)
    	                    	basicStore.loadPage(1);
    	                }
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
	 listeners:{
		 select: function(rowModel, record, index, eOpts) {
			 storeLoad(record);
		 },
		 itemdblclick: function(view, record, item, index, e, eOpts){
			 storeLoad(record);
			 App.openTab('person');
		 }
	 }
});

var storeLoad = function(record){
	var orderno = record.get('orderno');
	 var entname = record.get('entname');
	 personStore.proxy.url = '/first/content/hbjh/person?orderno='+orderno;
	 personStore.load();
	 shareholderStore.proxy.url = '/first/content/hbjh/shareholder?orderno='+orderno;
	 shareholderStore.load();
	 Ext.getCmp('entname').setTitle(entname);
}

var basicTab = {
		 title:'企业',
		 layout:'fit',
		 itemId:'basic',
		 items:basicGrid,
		 listeners:{
	    	 activate:function(e,opts){
	    		 if(e.items.getAt(0).store.count() == 0)
	    			 e.items.getAt(0).store.load();
	    	 }
	     }
	 };