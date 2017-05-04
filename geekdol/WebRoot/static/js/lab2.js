$(function(){
	
	$('#b1').click(function(){
		$obj = $('#s1 option:selected').clone(true);
		if($obj.size() == 0){
			alert("请至少选择一条!");
		}
		$('#s2').append($obj);
		$('#s1 option:selected').remove();
	});
	
	$('#b2').click(function(){
		$('#s2').append($('#s1 option'));
	});
	
	$('#b3').click(function(){
		$obj = $('#s2 option:selected').clone(true);
		if($obj.size() == 0){
			alert("请至少选择一条!");
		}
		$('#s1').append($obj);
		$('#s2 option:selected').remove();
	});
	
	$('#b4').click(function(){
		$('#s1').append($('#s2 option'));
	});
	
	$('#s1').dblclick(function(){
			$obj = $('#s1 option:selected').clone(true);
			$('#s2').append($obj);
			$('#s1 option:selected').remove();
	});
	$('#s2').dblclick(function(){
		$obj = $('#s2 option:selected').clone(true);
		$('#s1').append($obj);
		$('#s2 option:selected').remove();
   });
	
});