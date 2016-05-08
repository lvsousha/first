$(document).ready(function(){
	$.ajax({
		url:'/first/content/hbjh/basic_list',
		data:{
			start:0,
			limit:15
		},
		success:function(result){
			var list = result.model.basics;
			console.log(list);
			for(i in list){
				var tr = "<tr>";
				for(item in list[i]){
					tr +="<td>"+list[i][item]+"</td>";
				}
				tr += "</tr>";
				$("#ent").append(tr);
			}
		}
	})
});