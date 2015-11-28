var comboStore = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data : [
        {"abbr":"A", "name":"Apple"},
        {"abbr":"B", "name":"Blanan"},
        {"abbr":"D", "name":"Dog"}
    ]
});

var basicPanel = Ext.create('Ext.form.Panel',{
		        id:'basic',
		        layout: 'column',
		        defaults:{
		        	margin:'10 10 10 10',
		        	columnWidth:0.25,
		        	labelAlign:'right',
		        },
		        items:[{
		        	xtype:'textfield',
		        	id:'display',
		        	fieldLabel:'显示',
		        	columnWidth:0.8
		        },{
		        	xtype:'textfield',
		        	fieldLabel:'textfield',
		        	labelAlign:'right',
		        	validator:function(value){
		        		if(value>=0 && value<10)
		        			return true;
		        		else
		        			return 'sorry';
		        	},
		        	listeners:{
		        		change:function(e, newV, oldV, opt){
		        			var displayF = Ext.getCmp('display');
		        			displayF.setValue(newV);
		        		}
		        	}
		        },{
		        	xtype:'combo',
		        	fieldLabel: 'combo',
		            store: comboStore,
		            queryMode: 'local',
		            displayField: 'name',
		            valueField: 'abbr',
		        },{
		        	xtype: 'datefield',
		        	fieldLabel: 'datefield',
		        	format:'Y-m-d'
		        },{
		        	xtype: 'monthfield',
		        	fieldLabel: 'monthfield'
		        },{
		        	xtype: 'filefield',
		            fieldLabel: 'filefield',
		            labelWidth: 50,
		            msgTarget: 'side',
		            allowBlank: false,
		            anchor: '100%',
		            buttonText: 'Select'
		        },{
		        	xtype: 'fieldcontainer',
		            fieldLabel: 'radiofield',
		            defaultType: 'radiofield',
		            items:[{
		            	boxLabel: 'M',
	                    name: 'size',
	                    inputValue: 'm',
	                    id: 'radio1'
		            },{
		            	boxLabel: 'L',
	                    name: 'size',
	                    inputValue: 'l',
	                    id: 'radio2',
			            listeners:{
			            	change:function(e, newV, oldV, opt){
			            		var displayF = Ext.getCmp('display');
			        			displayF.setValue(e.getSubmitValue());
			            	}
			            }
		            }]
		        },{
		        	xtype:'timefield',
		        	fieldLabel:'timefield'
		        },{
		        	xtype:'colorfield',
		        	fieldLabel:'colorfield'
		        },{
		        	xtype:'objectfield',
		        	fieldLabel:'objectfield'
		        },{
		        	xtype: 'htmleditor',
		            fieldLabel: 'htmleditor',
		            columnWidth:0.95
		        }]
    	 });

var basicTab = {
		 title:'基本组件',
		 layout:'fit',
		 items:basicPanel
	 };