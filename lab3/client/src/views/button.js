export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);

			this.addEventListener('onclick',()=>{
				this.store.dispatch({
					type:constants.actions.INCREMENT,
					payload:window.globalGeneratorRate+1	
				});
			});

			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)
		}
	};
}
