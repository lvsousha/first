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