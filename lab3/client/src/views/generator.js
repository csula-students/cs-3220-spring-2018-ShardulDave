export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view
			
			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);
			this.store.subscribe(state=>{
				console.log(state);
			})
			// TODO: add click event
			this.addEventListener('click', () => {
                this.store.dispatch({
					type: 'BUY GENERATOR',
					payload:{
						quantity:1,
						name: this.dataset.name
					}
                });
            });
		}
		
		handleStateChange (newState) {
			var g=newState.generators;
			g.forEach(element => {
				if(element.name===this.dataset.name){
					this.querySelector('.count_ofgen').textContent=element.quantity;
					this.querySelector('.btnres').textContent.element.unlockValue;
				}
			});
			
		}

		connectedCallback () {
			console.log('ExampleComponent#onConnectedCallback');
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('ExampleComponent#onDisconnectedCallback');
			this.store.unsubscribe(this.onStateChange);
		}

	};
}

window.customElements.define('game-generator', RGBSquareComponent(store));
