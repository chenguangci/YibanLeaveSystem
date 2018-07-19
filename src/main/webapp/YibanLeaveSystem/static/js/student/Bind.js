var collages=["计算机科学与软件学院、大数据学院","文学院"];
var classs=[["软件工程","网络工程","物联网工程"],["书法","广播学","新闻"]];
var collage,clas;
function init()
{
	collage=document.getElementById("Collage");
	clas=document.getElementById("Class");
	collage.options.length=1;
	for(var i=0;i<collages.length;i++)
	{
		var option =new Option(collages[i],collages[i]);
		collage.options.add(option);
	}
	
}

function collageChange(){
	
	clas.options.length=1;
	if(collage.selectedIndex==0)
	return ;
	var pIndex=collage.selectedIndex;
	console.log(pIndex);
	for(var i=0;i<classs[pIndex-1].length;i++)
	{
		var value=classs[pIndex-1][i];
		var option =new Option(value,value);
		clas.options.add(option);
	}
}

window.onload=function(){
	init();
	collage.onchange=collageChange;//绑定onchange事件
};

