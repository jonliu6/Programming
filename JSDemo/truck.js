(function(window){
    'use strict';
    var App = window.App || {};

    // constructor of Truck object
    function Truck(truckId, ds) { // params: truckId and ds - datastore
        this.truckId = truckId;
        this.ds = ds;
    }

    Truck.prototype.createOrder = function(order) {
        console.log('Adding order for ' + order.userName);
        this.ds.add(order.userName, order);
    };
    
    Truck.prototype.deliverOrder = function(userName) {
        console.log('Delivering order for ' + userName);
        this.ds.remove(userName);
    };
    
    Truck.prototype.printOrders = function() {
        var userNameArray = Object.keys(this.ds.getAll());
        
        console.log('Truck #:' + this.truckId + ' has pending orders: ');
        userNameArray.forEach(function(name) {
            console.log(this.ds.get(name));
        }.bind(this)); // bind method accepts an object argument and returns a new version. 
        // the forEach callback has no owner, use bind() to pass a reference to the Truck instance.
        // in ES6, you can use arrow notation instead of bind(). e.g. () => { ... }
    };

    App.Truck = Truck;
    window.App = App;
})(window);
