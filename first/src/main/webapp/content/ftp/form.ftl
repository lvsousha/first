var comboStore = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"A", "name":"Apple"},
        {"abbr":"B", "name":"Blanan"},
        {"abbr":"D", "name":"Dog"}
    ]
});

var formPanel = Ext.create('Ext.form.Panel',{
		        id:'ftpForm',
		        flex:3,
                width: '100%',
		        layout: 'column',
		        defaults:{
		        	margin:'10 10 10 10',
		        	columnWidth:0.25,
		        	labelAlign:'right',
		        },
		        items:[{
		        	xtype:'textfield',
		        	fieldLabel:'username',
		        	labelAlign:'right',
		        	name:'username',
		        	id:'username',
		        	allowBlank:false,
//		        	validator:function(value){},
		        	listeners:{}
		        },{
		        	xtype:'textfield',
		        	fieldLabel:'password',
		        	labelAlign:'right',
		        	name:'password',
		        	id:'password',
		        	allowBlank:false,
		        	validator:function(value){},
		        	listeners:{}
		        },{
		        	xtype:'textfield',
		        	fieldLabel:'ip',
		        	labelAlign:'right',
		        	name:'ip',
		        	id:'ip',
		        	allowBlank:false,
		        	validator:function(value){},
		        	listeners:{}
		        },{
		        	xtype:'textfield',
		        	fieldLabel:'port',
		        	labelAlign:'right',
		        	name:'port',
		        	id:'port',
		        	validator:function(value){},
		        	listeners:{}
		        },{
		        	xtype: 'fieldcontainer',
		            fieldLabel: '选择上传文件',
		            items: [{
		                xtype: 'textfield',
		                flex: 1
		            },{
		            	xtype:'button',
		            	text:'上传文件',
		            	handler:function(){
		            		
		            	}
		            }]
		        },{
		        	xtype:'textfield',
		        	fieldLabel:'nodes',
		        	labelAlign:'right',
		        	name:'nodes',
		        	id:'nodes',
//		        	allowBlank:false,
//		        	validator:function(value){},
		        	listeners:{}
		        }]
    	 });