var gradeStore = Ext.create('Ext.data.Store',{
    		 model:'Grade',
    		 proxy:{
    			 type:'ajax',
    			 url:'/first/content/grade/list.html',
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