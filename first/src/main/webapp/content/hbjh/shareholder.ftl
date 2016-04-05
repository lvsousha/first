var shareholderStore = Ext.create('Ext.data.Store', {
    	     model: 'Shareholder',
    	     pageSize:25,
    	     proxy: {
    	         type: 'ajax',
    	         url: '/first/content/hbjh/shareholder',
    	         reader: {
    	             type: 'json',
    	             root: 'model.shareholders',
    	             totalProperty: 'model.total'
    	         }
    	     },
    	     autoLoad: false,
    	     listeners:{
    	    		load: function(store, records){
//    	    			alert(Ext.isArray(records));
    	    		}
    	    	}
    	 });

var shareholderGrid = Ext.create('Ext.grid.Panel',{
	 store:shareholderStore,
	 title:'股东',
	 layout:'fit',
	 columns:[{
		 header:'序号',
		 dataIndex:'orderno',
		 align:'center',
		 flex:1
	 },{
		 header:'股东名称',
		 dataIndex:'shaname',
		 align:'center',
		 flex:1
	 },{
		 header:'出资金额',
		 dataIndex:'subconam',
		 align:'center',
		 flex:1
	 },{
		 header:'币种',
		 dataIndex:'regcapcur',
		 align:'center',
		 flex:1
	 },{
		 header:'出资日期',
		 dataIndex:'condate',
		 align:'center',
		 flex:1
	 }],
	 dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: shareholderStore,   // GridPanel使用相同的数据源
	        dock: 'bottom',
	        displayInfo: true
	    }],
});

var shareholderTab = {
		 title:'股东',
		 layout:'fit',
		 listeners:{
//	    	 activate:function(e,opts){
//	    		 if(e.items.getAt(0).store.count() == 0)
//	    			 e.items.getAt(0).store.load();
//	    	 }
	     }
	 };