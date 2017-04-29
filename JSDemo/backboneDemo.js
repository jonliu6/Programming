'use strict';
        
var book1 = new Book();
book1.set("title", "Backbone Demo Book");
book1.set("isbn", "123-1234567-111");

var view1 = new BookView({
    model: book1,
    el: $("#txtSection")
});

var book1 = new Book({title:'Underscore JS Demo', isbn:"123-111-1234", publishYear: 2010});
var book2 = new Book({title:'Drawing for Dummy', isbn:'111-111-0001', publishYear: 1990});
var book3 = new Book({title:'Backbone JS Demo', isbn:'234-222-9876', publishYear: 2015});
var book4 = new Book({title:'How to paint your house', isbn:'a12-000-xyzw', publishYear: 2017});

var bookShelf = new Library([book1,book2,book3,book4]);
var bookListView = null;
// var bookShelf = new Library();
// bookShelf.add(book1); // another way to add models

$(document).ready(function(){
    // console.log(view1.render().el);
    // NOTE: you may get wierd JS error if jQuery selector returns null or undefined
    // $("#txtSection").html(view1.render().el); // option 1 to render
    // view1.render(); // option 2 to render after sepcifying el in the view

    bookListView = new LibraryView({el: $('#txtSection'), model: bookShelf});
    bookListView.render();
});
