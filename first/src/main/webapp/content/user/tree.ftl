var menuStore = Ext.create('Ext.data.Store', {
    model: 'School',
    proxy: {
        type: 'ajax',
        url: '/first/content/example/list.html',
        reader: {
            type: 'json',
            root: 'school'
        }
    },
    autoLoad: true,
    listeners:{
   		load: function(store, records){
   			var mainMenus = Ext.getCmp('mainMenu');
   			Ext.each(records, function(e){
   				var menuPanel;
   				var treeStore = Ext.create('Ext.data.TreeStore',{
   					model:'Menu',
   				    proxy:{
   				    	type:'ajax',
   				    	url:'/first/content/example/getTree.html?parent='+e.data.id,
   				    	reader:{
   				    		type:'json',
   				    		root:''
   				    	}
   				    },
   				   	root: {
   				        text : '管理菜单',
   				        expanded : true
   				   	}
   				});
   				menuPanel = Ext.create('Ext.panel.Panel', {
   					title: e.data.name,
   					layout:'fit',
   					items:[{
   						xtype:'treepanel',
   						store:treeStore,
   						lines:false,
   						rootVisible:false,
   						listeners:{
   							itemclick: function(view, record, index, e, ep){
   								window.location = record.data.model.uri;
   							}
   						}
   					}]
   				});
   				mainMenus.items.add(e.data.id, menuPanel);
   			});
   			mainMenus.add();
   		}
   	}
});