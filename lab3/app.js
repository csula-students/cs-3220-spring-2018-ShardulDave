window.incrementalGame = {
    state: {
        counter: 0,
        
    }
};
class PubSub {
    constructor () {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe (fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish (data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}
const pubSub=new PubSub();
var count1= document.querySelector('#num1');
const button=   document.querySelector('#btn');
var count2=0;

pubSub.subscribe(action=>{
    if(action.type !== 'CTR'){
        return;
    }

    count2 ++;
    window.incrementalGame.state.counter=count2;
    count1.innerHTML=window.incrementalGame.state.counter;
    console.log(window.incrementalGame.state.counter);
});

button.addEventListener('click',function(){
    pubSub.publish({
        type:'CTR',
        payload:window.incrementalGame.state.counter
    });
});
