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

Ext.define('Menu', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'model.menuname', type: 'string'},
        {name: 'model.uri',  type: 'string'},
        {name: 'text',  type: 'string'},
        {name: 'model',  type: 'object'}
    ]
});

Ext.define('School', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'name', type: 'string'}
    ]
});