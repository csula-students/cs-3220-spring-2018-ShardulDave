import constants from '../constants';
import Generator from '../models/generator';


export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.rateChangeIncrement = null;
			this.calledOnce = false;
			

			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: render generator initial view
			document.write('<div id="nu"></div>');
			
			this.store.state.generators.forEach(i=>{
				const GenObj=new Generator(i);
				const ttt=document.createElement('h2');
				ttt.innerHTML="Hello";
				const box=document.createElement('div');
				box.className='BOX'+GenObj.type;

				const GenName=document.createElement('h2');
				GenName.innerHTML=GenObj.name;
				box.appendChild(GenName);
				parent.appendChild(box);

				const GenQuantity=document.getElementById('p');
				GenQuantity.innerHTML=GenObj.quantity;
				GenName.appendChild(GenQuantity);

				const GenRate=document.createElement('p');
				GenRate.innerHTML=GenObj.rate;

				const Genbutton=document.createElement('button');
				GenButton.addEventListener('click',(event)=>{
					this.store.dispatch({
						type:constants.actions.BUY_GENERATOR,
						payload:counter-GenObj.getCost
					});
				});

			});
			
			this.appendChild(parent);
			// TODO: subscribe to store on change event

			// TODO: add click event

		}

		handleStateChange (newState) {
			console.log('ExampleComponent#stateChange', this);
			this.textContent = newState.example;
		}

		connectedCallback () {
			console.log("GeneratorComponent#connectedCallback");
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			// console.log("GeneratorComponent#disconnectedCallback");
			this.store.unsubscribe(this.onStateChange);
		}
	};
}