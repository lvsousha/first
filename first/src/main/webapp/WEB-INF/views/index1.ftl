<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
<link rel="stylesheet" type="text/css" charset=UTF-8 href="extjs/ext-theme-classic/ext-theme-classic-all.css" />
<script type="text/javascript"  charset=UTF-8 src="extjs/ext-all.js"></script>
<script type="text/javascript" charset=UTF-8 src="extjs/ext-lang-zh_CN.js"></script>
</head>
<body>
<script type="text/javascript">
     Ext.onReady(function(){

    	 var treeStore = Ext.create('Ext.data.TreeStore',{   		
    		 proxy:{
    			type:'ajax',
    			url:'user/create3.html',
    			reader:{
    				type:'json',
    				root:'children'
    			}
    		 },
   		     root : {  
                text : '管理菜单2',  
                expanded : true           
             }
    	 });
    	 
    	 var treePanel = Ext.create('Ext.tree.Panel',{
    		 title:'树形结构',
    		 store:treeStore
    	 });
    	 
    	 Ext.define('Grade',{
    		 extend:'Ext.data.Model',
    		 fields:[{
    			 name:'id'
    		 },{
    			 name:'chinese'
    		 },{
    			 name:'english'
    		 },{
    			 name:'sciense'
    		 },{
    			 name:'history'
    		 },{
    			 name:'math'
    		 }]
    	 });
    	 
    	 Ext.define('User', {
    	     extend: 'Ext.data.Model',
    	     fields: [
    	         {name: 'id', type: 'int'},
    	         {name: 'username', type: 'string'},
    	         {name: 'password',  type: 'string'}
    	     ]
    	 });

    	 var userStore = Ext.create('Ext.data.Store', {
    	     model: 'User',
    	     proxy: {
    	         type: 'ajax',
    	         url: 'user/create4.html',
    	         reader: {
    	             type: 'json',
    	             root: 'user'
    	         }
    	     },
    	     autoLoad: false
    	 });
    	 
    	 var userGrid = Ext.create('Ext.grid.Panel',{
    		 store:userStore,
    		 height:400,
    		 width:600,
    		 columns:[{
    			 header:'序号',
    			 dataIndex:'id',
    			 flex:1
    		 },{
    			 header:'用户名',
    			 dataIndex:'username',
    			 flex:1
    		 },{
    			 header:'密码',
    			 dataIndex:'password',
    			 flex:1
    		 }]
    	 });
    	 
    	 var gradeStore = Ext.create('Ext.data.Store',{
    		 model:'Grade',
    		 proxy:{
    			 type:'ajax',
    			 url:'user/create1.html',
    			 reader:{
    				 type:'json',
    				 root:'grades'
    			 }
    		 },
    		 autoLoad:false
    	 });  	   
    	 
    	 var gradeGrid = Ext.create('Ext.grid.Panel',{
    		 store:gradeStore,
    		 height:400,
    		 width:600,
    		 columns:[{
    			 header:'序号',
    			 dataIndex:'id',
    			 flex:1
    		 },{
    			 header:'语文',
    			 dataIndex:'chinese',
    			 flex:1
    		 },{
    			 header:'英语',
    			 dataIndex:'english',
    			 flex:1
    		 },{
    			 header:'科学',
    			 dataIndex:'sciense',
    			 flex:1
    		 },{
    			 header:'数学',
    			 dataIndex:'math',
    			 flex:1
    		 },{
    			 header:'历史',
    			 dataIndex:'history',
    			 flex:1
    		 }]
    	 });
    	 
    	 var menu = Ext.create('Ext.menu.Menu',{
    		 plain:true,
    		 items:[{
    			text:'新建',
    			handler:function(){
    				alert('你点击了新建')
    			}
    		},{
    			text:'保存',
    			handler:function(){
    				alert('you click the Save')
    			}
    		},{
    			text:'删除',
    			handler:function(){
    				alert('you click the Delete')
    			}
    		}]
    	 });
    	 
    	 var formPanel = Ext.create('Ext.form.Panel',{
		        id:'login',
		        layout: 'column',
		        defaults:{
		        	xtype:'textfield',
		        	columnWidth:'0.25',
		        	margin:'10 0 10 0'
		        },
		        items:[{
		        	name:'username',
		        	fieldLabel:'用户名',
		        	labelAlign:'right'
		        },{
		        	name:'password',
		        	fieldLabel:'密码',
		        	labelAlign:'right'
		        },{
		        	xtype:'button',
		        	text:'提交',
		        	handler:function(){		        		
		        		Ext.Ajax.request({
		        		    url: 'user/create5.html',
		        		    params:Ext.getCmp('login').getForm().getValues(),
		        		    success: function(response, opts) {
		        		    	var obj = Ext.decode(response.responseText);
		        		    	Ext.getCmp('login').getForm().setValues(obj.user);
		        		    },
		        		    failure: function(response, opts) {
		        		        alert('失败');
		        		    }
		        		});
		        	}
		        }]
    	 });

    	 var tabPanel = Ext.create('Ext.tab.Panel',{
    		 activeTab:0,
    		 id:'tabpanel',
    		 items:[{
    			 title:'成绩',
    			 id:'gradeGrid',
    			 layout:'fit',
    			 items:gradeGrid,
    			 listeners:{
    		    	 activate:function(e,opts){
    		    		 if(e.items.getAt(0).store.count() == 0)
    		    			 e.items.getAt(0).store.load();    		    		 
    		    	 }
    		     }
    		 },{
    			 title:'用户',
    			 layout:'fit',
    			 items:userGrid,
    			 listeners:{
    		    	 activate:function(e,opts){
    		    		 if(e.items.getAt(0).store.count() == 0)
    		    			 e.items.getAt(0).store.load();     		    		 
    		    	 }
    		     }
    		 },{
    			 title:'表单',
    			 layout:'fit',
    			 items:formPanel
    		 },{
    			 title:'TREE',
    			 layout:'fit',
    			 items:treePanel
    		 }],
    		 dockedItems: [{
		            xtype: 'toolbar',
		            dock: 'top',
		            items: [{
		                text: '菜单',
		                menu: menu
		            }]
		     }],
		     listeners:{
		    	 activate:function(e,opts){
		    		 alert("in");
		    		 e.store.load();
		    	 }
		     }
    	 });

    	 Ext.create('Ext.container.Viewport', {
    		    layout: 'border',
    		    items: [{
    		        region:'west',
    		        xtype: 'panel',
    		        width: 200,
    		        collapsible: true,
    		        id: 'west-region-container',
    		        layout: 'accordion',
    		        items:treePanel
    		    },{
    		        region: 'center',
    		        xtype: 'panel',
    		        layout: 'fit',
    		        items:tabPanel   		        
    		    },{
    		        title: 'North',
    		        region: 'north',
    		        xtype: 'panel',
    		        height: 100,
    		        layout: 'fit',
    		        margins: '5 5 0 0'
    		    }],
    		    renderTo: Ext.getBody()
    		});
       });
</script>
</body>
</html>