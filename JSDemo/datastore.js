// put an anonymous function into a parentheses, browser knows it is an anoymous function but will not execute it rightaway.
(function (window){
    'use strict'
    var App = window.App || {}; // if an App property of the window existed, use it or create an empty object

    // In JS functions are objects, so the Camer-case DataStore is the constructor of an object
    function DataStore() {
        console.log('initialize a DataStore');
        this.data = {}; 
    }

    // in JS functions are also objects and can have properties. prototype is a property of the constructor
    DataStore.prototype.add = function(key, val) {
        this.data[key] = val;
    }

    DataStore.prototype.get = function(key) {
        return this.data[key];
    }

    DataStore.prototype.getAll = function() {
        return this.data;
    }

    DataStore.prototype.remove = function (key) {
        delete this.data[key]; //delete operator remove a key/value pair from the object
    }

    App.DataStore = DataStore;  // attach DataStore to the App object.
    window.App = App; // reassign the global App property to the newly modified App.
})(window); // 
