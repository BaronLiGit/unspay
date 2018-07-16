function mymd5(){
	var accountId = $("#accountId").val();
	var customerId = $("#customerId").val();
	var orderNo = $("#orderNo").val();
	var purpose = $("#purpose").val();
	var amount = $("#amount").val();
	var commodityName = $("#commodityName").val();
	var businessType = $("#businessType").val();
	var responseUrl = $("#responseUrl").val();
	var pageResponseUrl = $("#pageResponseUrl").val();
	var key = $("#key").val();
	var macStr="";
	 macStr = 'accountId='+accountId;
	 macStr += '&customerId='+customerId;
	 macStr += '&orderNo='+orderNo;
	 macStr += '&purpose='+purpose;
	 macStr += '&amount='+amount;
	 macStr += '&commodityName='+commodityName;
	 macStr += '&businessType='+businessType;
	 macStr += '&responseUrl='+responseUrl;
	 macStr += '&pageResponseUrl='+pageResponseUrl;
	 macStr += '&key='+key;
	 var mac = md5(macStr).toUpperCase();
	 alert(macStr);
	 $("#mac").val(mac);
}