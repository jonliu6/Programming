var Book = Backbone.Model.extend({
    defaults: {
        title: "",
        isbn: "", // unique key when using as element in a collection,
        publishYear: 2017
    },
    idAttribute: "isbn", // optional to specify the unique key
    initialize: function() {
        console.log("Book model is initialized");
        this.on("change:title",function(){ // only monitor the title change. If for all changes, just use "change"
            console.log("Title in the Book model is changed");
        });
    }
});

// NOTE: Book Model has to be above since it is referred in below
var Library = Backbone.Collection.extend({
    model: Book, // bind to Book model,
    comparator: function(bk){ // used for sorting the collection
        return bk.get("publishYear");
    },
    url: "/books" // URL for the data can be retrieved from the server
});
