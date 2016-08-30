// npm vue, vue-resource
var vm = new Vue({
	el: '#app',
	data: {
		message: 'Second',
		message2: 'Third'
	},
	methods: {
		setMessage: function (message) {
			this.message = message;	
		},
		getApiData: function (url, data) {
            this.message = 'loading...';
			this.$http.get(url,data)
				.success(function(result) {
					console.log('API Success');	
					this.setMessage('API is success');
				})
				.error(function() {
					console.log('API Fail.');
					this.setMessage('API opposite of working');	
				});
		}
	}
});

vm.getApiData('/photography');

new Vue({
	el: '#secc',
	data: {
		seccMsg: 'First Section',
		seccMsg2: 'Second Section'
	}
});
