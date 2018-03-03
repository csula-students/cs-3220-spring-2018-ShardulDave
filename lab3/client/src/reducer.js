export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;
	case 'BUY_GENERATOR':
		for(let i=0;i<state.generators.length;i++){
			let generators=state.generators[i];
			if(generators.name===action.payload.name){
				let gen = new Generator(generator);
                state.counter -= gen.getCost();
                generator.quantity++;
				generator.unlockValue = gen.getCost();                        
				return state;
			}
		}
		let generatorName = action.payload.name;
            const generatorData = {
                type: 'autonomous',
                name: generatorName,
                description: 'desc',
                quantity: 0,
                baseCost: 0, 
                unlockValue: 0
            }

            if(generatorName === "Salary"){
                generatorData.rate = 5;
                generatorData.baseCost = 10;
                generatorData.unlockValue = 10;
            }
            else if(generatorName === "Rent"){
                generatorData.rate = 10;
                generatorData.baseCost = 100;
                generatorData.unlockValue = 100;
            }
            else if (generatorName === "Share"){
                generatorData.rate = 20;
                generatorData.baseCost = 500;
                generatorData.unlockValue = 500;
            }
            generatorData.quantity++;
            state.counter -= generatorData.baseCost;
            let instanceofgenerator = new Generator(generatorData);
            instanceofgenerator.unlockValue = instanceofgenerator.getCost();
            state.generators.push(instanceofgenerator);
            return state;
	default:
		return state;
	}
}

