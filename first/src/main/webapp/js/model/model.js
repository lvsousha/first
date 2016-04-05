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

Ext.define('Basic', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'orderno', type: 'String'},
        {name: 'entname', type: 'string'},
        {name: 'frname',  type: 'string'},
        {name: 'cardnum',  type: 'string'},
        {name: 'regno',  type: 'string'}
    ]
});

Ext.define('Person', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'orderno', type: 'String'},
        {name: 'pername', type: 'string'},
        {name: 'position',  type: 'string'},
        {name: 'person_id_bocom',  type: 'string'}
    ]
});

Ext.define('Shareholder', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'orderno', type: 'String'},
        {name: 'shaname', type: 'string'},
        {name: 'person_id_bocom',  type: 'string'},
        {name: 'condate',  type: 'string'},
        {name: 'subconam',  type: 'string'},
        {name: 'regcapcur',  type: 'string'}
    ]
});