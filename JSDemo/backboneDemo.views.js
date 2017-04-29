var BookView = Backbone.View.extend({
    // tagName: "h1",
    // el: '',
    model: Book,
    initialize: function() {
        console.log("BookView is initialized");
    },
    render: function() {
        this.$el.html('<h1>' + this.model.get("isbn") + ' (' + this.model.get("title") + ') - ' + this.model.get("publishYear") + '</h1>');
        return this;
    }
});

var LibraryView = Backbone.View.extend({
    // el: '',
    model: Library, // in this case, you use this.model.models in below - _(this.model.models).each(...)
    initialize: function() {
        console.log("LibraryView is initialized");
    },
    render: function() {
        this.$el.html();
        var self = this;

        _.each(this.model.models, function(elem,idx,list){ // ??? not sure why this.model.models is the array/collection
            // console.log("index: " + idx + elem);
            var bookView = new BookView({
                model: elem
            });
            this.$el.append(bookView.$el); // NOTE, 'this' here is the context 'self' passed in
            bookView.render();
        }, self);
//                for(var i = 0, len = this.model.length; i < len; ++i) {
//                    console.log(this.model.models[i].get("title"));
//                }
        return this;
    }
});
