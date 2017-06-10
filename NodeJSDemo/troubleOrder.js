module.exports = function(label, status) {
  var orderLabel = label;
  var orderStatus = status;
  
  var functions = {
    setLabel: function(label) {
	  this.orderLabel = label;
	},
	
	setStatus: function(status) {
	  this.orderStatus = status;
	},
	
	getOrder: function() {
	  return {
	    orderLabel: label,
		orderStatus: status
	  };
	}
  }; 
  
  return functions;
};