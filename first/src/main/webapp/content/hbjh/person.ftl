var personStore = Ext.create('Ext.data.Store', {
    	     model: 'Person',
    	     pageSize:25,
    	     proxy: {
    	         type: 'ajax',
    	         url: '/first/content/hbjh/person',
    	         reader: {
    	             type: 'json',
    	             root: 'model.persons',
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

var personGrid = Ext.create('Ext.grid.Panel',{
	 store:personStore,
	 title:'高管',
	 layout:'fit',
	 columns:[{
		 header:'序号',
		 dataIndex:'orderno',
		 align:'center',
		 flex:1
	 },{
		 header:'高管名称',
		 dataIndex:'pername',
		 align:'center',
		 flex:1
	 },{
		 header:'个人标识',
		 dataIndex:'person_id_bocom',
		 align:'center',
		 flex:1
	 },{
		 header:'职位',
		 dataIndex:'position',
		 align:'center',
		 flex:1
	 }],
	 dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: personStore,   // GridPanel使用相同的数据源
	        dock: 'bottom',
	        displayInfo: true
	    }],
});

var personTab = {
		 title:'高管和股东',
		 layout:'fit',
		 itemId:'person',
		 items:{
			 xtype:'panel',
			 id:'entname',
			 titleAlign:'center',
			 layout:{
				 type: 'hbox',
			     align: 'stretch'
			 },
			 items:[{
			        xtype: 'panel',
			        layout:'fit',
			        flex: 1,
			        items:personGrid
			    },{
			        xtype: 'panel',
			        layout:'fit',
			        flex: 1,
			        items:shareholderGrid
			    }]
		 },
		 listeners:{
//	    	 activate:function(e,opts){
//	    		 if(e.items.getAt(0).store.count() == 0)
//	    			 e.items.getAt(0).store.load();
//	    	 }
	     }
	 };