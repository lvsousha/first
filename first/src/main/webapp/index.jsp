<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
<link rel="stylesheet" type="text/css" charset=UTF-8 href="extjs/ext-theme-classic/ext-theme-classic-all.css" />
<script type="text/javascript"  charset=UTF-8 src="extjs/ext-all.js"></script>
<script type="text/javascript" charset=UTF-8 src="extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript" charset=UTF-8 src="extjs/model.js"></script>

</head>
<body>
<script type="text/javascript">
     Ext.onReady(function(){

    	 var treeStore = Ext.create('Ext.data.TreeStore',{   		
    		 proxy:{
    			type:'ajax',
    			url:'example/getTree.html',
    			reader:{
    				type:'json',
    				root:'children'
    			}
    		 },
   		     root : {  
                text : '管理菜单',  
                expanded : true           
             }
    	 });

    	 var userStore = Ext.create('Ext.data.Store', {
    	     model: 'User',
    	     proxy: {
    	         type: 'ajax',
    	         url: 'example/getUsers.html',
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
    			 url:'example/getGrades.html',
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
    				var panel = this.up('tabpanel').activeTab;
    				
    				alert(panel.child('form').getForm().findField('username').getSubmitValue());
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
		        }]
    	 });
    	 
    	 var formTab = {
    			 title:'表单',
    			 layout:'fit',
    			 items:formPanel
    		 };
    	 
    	 var gradeTab = {
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
    		 };
    	 
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
    	 
    	 var testTab = {
 	            xtype:"panel",
	            id:"index",
	            iconCls:"homemanage",
	            title:"测试",
	            html:"<iframe src='http://localhost:8080/first/index.ftl'scrolling='yes' frameborder=0 width=100% height=100%></iframe>"
	        };

    	var App = Ext.create('Ext.container.Viewport', {
    		id:'myApp',
	        centerPanel:{},
	        westPanel:{},
	        northPanel:{},
	        southPanel:{},
	        eastPanel:{},
    		layout: 'border',
    		    items: [{
    		        region:'west',
    		        xtype: 'panel',
    		        width: 200,
    		        collapsible: true,
    		        id: 'west-region-container',
    		        layout: 'accordion'
    		    },{
    		        region: 'center',
    		        xtype: 'panel',
    		        id:'center-region-container',
    		        layout: 'fit'
    		    },{
    		        title: 'North',
    		        region: 'north',
    		        xtype: 'panel',
    		        height: 100,
    		        layout: 'fit',
    		        margins: '5 5 0 0'
    		    }],
    		    createCenterPanel:function(config){
    		    	  this.centerPanel = Ext.create('Ext.tab.Panel',{
    		    		 title:config.modelName,
    		    		 activeTab:0,
    		    		 id:'tabpanel',
    		    		 items:config.tabs,
    		    		 dockedItems: [{
    				            xtype: 'toolbar',
    				            dock: 'top',
    				            items: [{
    				            	xtype:'button',
    				                id:'new',
    				                text:'新建',
    				                handler:function(){
    				                	var form = this.up('tabpanel').activeTab.child('form');
    				                	if(form == null){
    				                		;
    				                	}
    				                	form.getForm().setValues();
    				                	alert('IN');
    				                }
    				            },{
    				                xtype:'button',
    				                id:'save',
    				                text:'保存',
    				                handler:function(){
    				                	var form = this.up('tabpanel').activeTab.child('form');
    				                	if(form == null);
    				                	else{
    				                		Ext.Ajax.request({
    						        		    url: 'example/insertUser.html',
    						        		    params:form.getForm().getValues(),
    						        		    success: function(response, opts) {
    						        		    	var obj = Ext.decode(response.responseText);
    						        		    	form.getForm().setValues(obj.user);
    						        		    	alert('成功');
    						        		    },
    						        		    failure: function(response, opts) {
    						        		        alert('失败');
    						        		    }
    						        		});
    				                	}
    				                }
    				            },{
    				            	xtype:'button',
    				                id:'delete',
    				                text:'删除',
    				                handler:function(){
    				                	var form = this.up('tabpanel').activeTab.child('form');
    				                	if(form == null);
    				                	else{
    				                		Ext.Ajax.request({
    						        		    url: 'example/deleteUser.html',
    						        		    params:form.getForm().getValues(),
    						        		    success: function(response, opts) {
    						        		    	var obj = Ext.decode(response.responseText);
    						        		    	form.getForm().setValues(obj.user);
    						        		    	alert('成功');
    						        		    },
    						        		    failure: function(response, opts) {
    						        		        alert('失败');
    						        		    }
    						        		});
    				                	}
    				                }
    				            }]
    				     }],
    				     listeners:{
    				    	 activate:function(e,opts){
    				    		 alert('IN');
    				    		 e.store.load();
    				    	 }
    				     }
    		    	 });   
    		    	this.queryById('center-region-container').add(this.centerPanel);
    		    },
    		    createWestPanel:function(){
    		    	this.westPanel = Ext.create('Ext.tree.Panel',{
    		    		 title:'树形结构',
    		    		 store:treeStore
    		    	 });
    		    	this.queryById('west-region-container').add(this.westPanel);
		    	 },
    		    renderTo: Ext.getBody()
    		});
    	
    	
    	App.createCenterPanel({
    		modelName:'实例',
    		tabs:[gradeTab,userTab,formTab,testTab]
    	});
    	App.createWestPanel();    	
       });
</script>
</body>
</html>