(function() {
	'use strict';

	angular.module('sampleApp').factory('GlobalVariable', Global);

	Global.$inject = [];

	function Global() {
		var GlobalVarObj = {
		};
		GlobalVarObj.isLoginPage = true;
		GlobalVarObj.editProduct = false;
		GlobalVarObj.returnProduct = false;
		
		return GlobalVarObj;
	}
})();
