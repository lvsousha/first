var layoutPanel = Ext.create('Ext.form.Panel',{
    			id:'layout',
		        layout:{
		            type: 'table',
		            columns: 4
		        },
		        defaults:{
		        	margin:'10 10 10 10'
		        },
		        items:[{
		        	xtype:'panel',		        	
		        	title:'First',
		        	items:[{
			        	xtype:'textfield',
			        	name:'username',
			        	fieldLabel:'用户名',
			        	labelAlign:'right'
			        }]
		        },{
		        	xtype:'panel',
		        	title:'Second',
		        	items:[{
			        	xtype:'textfield',
			        	fieldLabel:'用户名',
			        	labelAlign:'right'
			        }]
		        },{
		        	xtype:'panel',
		        	title:'Three',
		        	items:[{
			        	xtype:'textfield',
			        	fieldLabel:'用户名',
			        	labelAlign:'right'
			        }]
		        },{
		        	xtype:'panel',
		        	title:'Four',
		        	items:[{
			        	xtype:'textfield',
			        	fieldLabel:'用户名',
			        	labelAlign:'right'
			        }]
		        },{
		        	xtype:'panel',
		        	title:'Five',
		        	colspan:4,
		        	height:500,
		        	layout: {
		                type: 'hbox',
		                align: 'stretch'
		            },
		            items:[{
		                xtype: 'panel',
		                title: 'One',
		                flex: 6,
		                layout: {
		                    type: 'vbox',
		                },
		                items:[{
		                    xtype: 'panel',
		                    title: '1',
		                    id:'1',
		                    width: '100%',
		                    flex: 6,
		                    layout: 'card',
		                    items: [{
		                    	xtype: 'panel',
			                    title: 'card1'
		                    },{
		                    	xtype: 'panel',
			                    title: 'card2'
		                    }],
		                    dockedItems: [{
			                    xtype: 'toolbar',
			                    dock: 'bottom',
			                    items:['->',{
			                    	xtype:'button',
			                    	text:'Prev',
			                    	handler:function(){
			                    		if(Ext.getCmp('1').getLayout().getPrev())
			                    			Ext.getCmp('1').getLayout().prev();
			                    		else
			                    			Ext.Msg.alert('sorry', '这是第一张');
			                    	}
			                    },{
			                    	xtype:'button',
			                    	text:'Next',
			                    	handler:function(){
			                    		if(Ext.getCmp('1').getLayout().getNext())
			                    			Ext.getCmp('1').getLayout().next();
			                    		else
			                    			Ext.Msg.alert('', '这是最后一张');
			                    	}
			                    }],
			                }]
		                },{
		                    xtype: 'panel',
		                    title: '2',
		                    width: 250,
		                    flex: 1
		                },{
		                    xtype: 'panel',
		                    title: '3',
		                    width: '50%',
		                    flex: 1
		                }]
		            },{
		                xtype: 'panel',
		                title: 'Two',
		                flex: 1
		            },{
		            	xtype: 'tbspacer',
		            	width: 30
		            },{
		                xtype: 'panel',
		                title: 'Three',
		                flex: 1
		            }]
		        }]
    	 });

var layoutTab = {
		 title:'布局',
		 layout:'fit',
		 items:layoutPanel
	 };