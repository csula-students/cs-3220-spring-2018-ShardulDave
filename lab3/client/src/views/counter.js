export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: render counter inner HTML based on the store state
			this.innerHTML='<h1 id="counter">Grandma:</h1>'+this.store.state.counter;
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			//console.log('CounterComponent#stateChange', this, newState);
			// TODO: update inner HTML based on the new state
			document.getElementById('counter').innerHTML=this.store.state.counter;
		}

		connectedCallback () {
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

