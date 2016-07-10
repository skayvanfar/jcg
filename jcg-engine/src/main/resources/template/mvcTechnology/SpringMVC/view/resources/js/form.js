$(document).ready(function () {
    Input.setup(".input");
    Suggest.setup(".suggest");
    Tags.setup(".tags.editable");
});

var UID = {
    chars: '_0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz'.split(''),
    get: function (length) {
        if (!length)
            length = 7;
        var id = '';
        for (var i = 0; i < length; i++) {
            id += UID.chars[Math.floor(Math.random() * UID.chars.length)];
        }
        // one last step is to check if this ID is already taken by an element before
        if ($("#" + id).length == 0)
            return id;
        else
            return UID.get(length + 1);
    },
    set: function (selector, length) {
        $(selector).each(function () {
            var id = $(this).attr("id");
            if (id && id != null && $("#" + id).length == 1)
                return;
            var newId = UID.get(length);
            $(this).attr("id", newId);
            if (id && id != null)
                $("* [for='" + id + "']").each(function () {
                    $(this).attr("for", newId);
                });
        });
    }
};

var Util = {
    isString: function (v) {
        if (!v || v == null)
            return null;
        return $.type(v) == "string";
    },
    json: function (v) {
        if (!v || v == null)
            return null;
        switch ($.type(v)) {
            case "string":
                return jQuery.parseJSON(v);
            case "array":
            case "object":
                return v;
        }
        return null;
    },
    array: function (v) {
        if (!v || v == null)
            return [];
        switch ($.type(v)) {
            case "array":
                return v;
            case "object":
                return [v];
        }
        return [];
    }
};

var Input = function (selector) {
    if (selector.nodeType || selector.indexOf("#") == 0 && $(selector).length <= 1)
        this.init(selector);
    else
        return Input.setup(selector);
};
Input.setup = function (selector) {
    if (selector.nodeType || selector.indexOf("#") == 0)
        return new Input(selector);
    var cp = [];
    UID.set(selector);
    $(selector).each(function () {
        cp.push(new Input("#" + this.id));
    });
    return cp;
};
Input.prototype = {
    init: function (selector) {
        var p = this;
        p.selector = selector;
        const s = $(selector);
        UID.set(selector + " .control");
        p.control = "#" + s.find(".control").attr("id");
        p.labeled();
        p.bind();
        p.file();
    },
    labeled: function () { //Add for label
        var p = this;
        const s = $(p.selector);
        s.find(".label").attr("for", $(p.control).attr("id"));
        s.data("input", p);
    },
    bind: function () { //Bind event on input
        var p = this;
        const s = $(p.selector);
        const i = $(p.control);
        i.on('focusin.form', function () {
            s.addClass('focus');
        });
        i.on('focusout.form', function () {
            s.removeClass('focus');
        });
        $(p.selector).on("click.form", function () {
            i.focus();
        });
    },
    file: function () {
        var p = this;
        if ($(p.selector + " .file").size() > 0)
            new File(p.selector + " .file", p.selector + " .progress", p.control, p.selector);
    }
};

var File = function (file, progress, button, container) {
    this.init(file, progress, button, container);
};
File.prototype = {
    init: function (file, progress, button, container) {
        this.file = file;
        this.name = $(file).attr("name");
        $(file).attr("name", "");
        this.progress = progress;
        this.button = button;
        this.container = container;
        this.reset();
        this.bind();
    },
    bind: function () {
        var p = this;
        const f = $(p.file);//File
        const b = $(p.button);//Button that fire file chooser
        f.hide();
        b.on("click.file", function () {
            f.click();
        });
        //f.on("change.file", function () {
        //    b.text(f.val());
        //});
    },
    reset: function () {
        var p = this;
        const f = $(p.file);//File
        const co = $(p.container);//Container
        const pr = $(p.progress);//Progress
        const b = $(p.button);//Button that fire file chooser
        f.fileupload({
            dataType: 'json',
            done: function (e, data) {
                pr.addClass("done");
                pr.removeClass("loading");
                co.find(".file-hidden-input").remove();
                const c = $("<div></div>").addClass("file-hidden-input");
                $.each(data.result, function (index, file) {
                    c.append($("<input type='hidden' />").val(file.fileName).attr("name", p.name));
                });
                co.prepend(c);
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                pr.removeClass("done");
                pr.addClass("loading");
                pr.css("width", progress + "%");
            },
            dropZone: co
        });
        f.bind('fileuploadchange', function (e, data) {
            $.each(data.files, function (index, file) {
                b.text(file.name);
            });
        });
        f.bind('fileuploaddrop', function (e, data) {
            $.each(data.files, function (index, file) {
                b.text(file.name);
            });
        });
    }
};

var Suggest = function (selector) {
    if (selector.nodeType || selector.indexOf("#") == 0 && $(selector).length <= 1)
        this.init(selector);
    else
        return Suggest.setup(selector);
};
Suggest.setup = function (selector) {
    if (selector.nodeType || selector.indexOf("#") == 0)
        return new Suggest(selector);
    var cp = [];
    UID.set(selector);
    $(selector).each(function () {
        cp.push(new Suggest("#" + this.id));
    });
    return cp;
};
Suggest.prototype = {
    init: function (selector) {
        const p = this;
        p.selector = selector; //selector of input that suggestion applied to it
        const e = $(selector);
        p.config = {
            popup: p.popup(), //create popup in parent off selector and return id of popup
            all: Util.array(Util.json(e.data("suggest"))), //static collection to searched
            filter: e.data("filter"), //url for filter by query staring
            create: e.data("create"), //url to create new item
            stringMode: false //switch beetwen {title:, param:}[] or String[] mode
        };
        p.state = {
            filtered: [], //list of showing item in popup
            hover: -1 //index of selected filter
        };
        e.on("focusout.suggest", function () {
            $(p.config.popup).hide();
        });
        e.keydown(function (event) {
            switch (event.which) {
                case 38://up
                    p.hover(p.state.hover - 1);
                    break;
                case 40://down
                    p.hover(p.state.hover + 1);
                    break;
                case 13://enter
                    p.select(p.state.hover);
                    event.preventDefault();
                    return false; //dont submit form
                case 27://Esc
                    $(p.config.popup).hide();
                    break;
            }
            return true;
        });
        e.on("input.suggest", function () {
            p.filter($(this).val());
            return true;
        });
    },
    popup: function () { //create popup in parent off selector and return id of popup
        const e = $(this.selector);
        const id = UID.get();
        e.parent().append($("<ul class='suggestion'></ul>").attr("id", id));
        return "#" + id;
    },
    filter: function (query) { //filter popup item by query
        const p = this;
        p.state.filtered = [];
        if (p.config.all.length > 0) { //if static item available
            $.each(p.config.all, function (index, data) {
                p.config.stringMode = p.config.stringMode || Util.isString(data);
                if (p.config.stringMode && data.indexOf(query) >= 0)
                    p.state.filtered.push({title: data, value: data});
                if (!p.config.stringMode && data.title.indexOf(query) >= 0)
                    p.state.filtered.push(data);
            });
            p.show(query);
        } else if (p.config.filter != null && p.config.filter) { //if filter url available pass parameter query={query}
            $.ajax({
                url: p.config.filter,
                data: {query: query}
            }).then(function (response) {
                p.state.filtered = [];
                $.each(response, function (index, data) {
                    p.config.stringMode = p.config.stringMode || Util.isString(data);
                    if (p.config.stringMode)
                        p.state.filtered.push({title: data, value: data});
                    if (!p.config.stringMode)
                        p.state.filtered.push(data);
                });
                p.show(query);
            });
        }
    },
    show: function (query) { //display popup and render filtered item in it
        const p = this;
        const c = $(p.config.popup);
        c.html("");
        if (this.state.filtered.length > 0 || p.config.create && p.config.create != null) {
            var match = false;
            $.each(this.state.filtered, function (index, data) {
                c.append(p.template(index, false));
                if (data.title == query)
                    match = true;
            });
            if (!match && p.config.create && p.config.create != null) {
                this.state.filtered.push({title: query});
                c.append(p.template(this.state.filtered.length - 1, true));
            }
            p.hover(0);
            c.show();
        } else
            c.hide();
    },
    template: function (index, create) { //create html of a item
        const p = this;
        const id = UID.get();
        const data = p.state.filtered[index];
        data.index = index;
        data.selector = "#" + id;
        data.create = create;
        const l = $("<li></li>").attr("id", id).data("index", index);
        if (!create)
            l.html(data.title);
        else
            l.html("ایجاد " + "<b>" + data.title + "</b");
        l.on("mouseover.suggest", function () {
            p.hover($(this).data("index"));
        });
        l.on("click.suggest", function () {
            const i = $(this).data("index");
            p.hover(i);
            p.select(i);
        });
        return l;
    },
    hover: function (index) { //change hover position to index
        const p = this;
        if (p.state.filtered.length == 0)
            return;
        if (index < 0)
            index = p.state.filtered.length - 1;
        if (index >= p.state.filtered.length)
            index = 0;
        p.state.hover = index;
        $(p.config.popup).find("li").removeClass("hover");
        $(this.state.filtered[index].selector).addClass("hover");
    },
    select: function (index) { //select index (if index be null use hover position) and insert title in input
        const p = this;
        if (p.state.filtered.length == 0 || index < 0 || index >= p.state.filtered.length)
            return;
        const v = p.state.filtered[index];
        if (!v.create) {
            p.trigger(v)
        } else {
            $.ajax({
                url: p.config.create,
                data: {query: v.title}
            }).then(function (response) {
                if (response && response != null)
                    p.trigger(response)
            });
        }
    },
    trigger: function (data) {
        const p = this;
        $(p.selector).val(data.title);
        if (!p.config.stringMode)
            $(p.selector).trigger('selected', data);
        else
            $(p.selector).trigger('selected', data.value);
        $(p.config.popup).hide();
    }

};

var Tags = function (selector) {
    if (selector.nodeType || selector.indexOf("#") == 0 && $(selector).length <= 1)
        this.init(selector);
    else
        return Tags.setup(selector);
};
Tags.setup = function (selector) {
    if (selector.nodeType || selector.indexOf("#") == 0)
        return new Tags(selector);
    var cp = [];
    UID.set(selector);
    $(selector).each(function () {
        cp.push(new Tags("#" + this.id));
    });
    return cp;
};
Tags.prototype = {
    init: function (selector) {
        const p = this;
        p.selector = selector;
        const e = $(selector);
        p.config = {
            text: e.data("text"), //text input must trigger selected event with param {title:,value:} then title and value added to text
            paramTitle: e.data("param-title"), //param title add hidden <input name=param-title value=input.data.title />
            paramValue: e.data("param-value"), //param value add hidden <input name=param-value value=input.data.value />
            repeat: e.data("repeat") != null, //if true repeated value allowed else not
            skipTitle: e.data("skip-title") != null, //if true repeated value allowed else not
            max: e.data("max-count"),
            stringMode: false //switch beetwen {title:, param:}[] or String[] mode
        };
        p.state = {
            ids: [], //id of tags
            datas: [] //value of tags
        };
        $.each(Util.array(Util.json(e.data("init"))), function (index, data) {
            p.config.stringMode = p.config.stringMode || Util.isString(data);
            if (!p.config.stringMode)
                p.tag(data);
            else
                p.tag({title: data, value: data});
        });
        e.find(p.config.text).on("selected", function (e, data) {
            $(this).val("");
            p.config.stringMode = p.config.stringMode || Util.isString(data);
            if (!p.config.stringMode)
                p.tag(data);
            else
                p.tag({title: data, value: data});
        });
    },
    tag: function (data) { //create tag and add add to tags list
        const p = this;
        if (p.config.max && p.config.max != null && p.state.datas.length >= p.config.max)
            return;
        const s = $(p.selector);
        if (!p.config.repeat) {
            var found = false;
            $.each(p.state.datas, function (index, d) {
                found = found || (d.value == data.value);
            });
            if (found)
                return;
        }
        const id = UID.get();
        if (p.state.ids.length == 0)
            s.prepend(p.template(data, id));
        else
            $("#" + p.state.ids[p.state.ids.length - 1]).after(p.template(data, id));
        p.state.datas.push(data);
        p.state.ids.push(id);
        p.hidden();
        if (p.config.max && p.config.max != null && p.state.datas.length >= p.config.max)
            $(p.selector).find(p.config.text).hide();
    },
    template: function (data, id) { //create html of a tag
        const p = this;
        const t = $("<li class='tag'></li>").attr("id", id)
            .append($("<span></span>").append(data.title))
            .append("<a class='close'><i class='icon icon-close'></i></a></li>");
        t.find(" .close").on("click", function (e) {
            p.remove(id);
            e.preventDefault();
            return true;
        });
        return t;
    },
    remove: function (id) { //remove tag form tags
        const p = this;
        const e = $(p.selector);
        const index = $.inArray(id, p.state.ids);
        if (index < 0)return;
        e.find("#" + id).remove();
        p.state.datas.splice(index, 1);
        p.state.ids.splice(index, 1);
        p.hidden();
        if (p.config.max && p.config.max != null && p.state.datas.length < p.config.max)
            e.find(p.config.text).show();
    },
    hidden: function () {//create hidden values
        const p = this;
        const e = $(p.selector);
        e.find(".tag-hidden-input").remove();
        const c = $("<div></div>").addClass("tag-hidden-input");
        $.each(p.state.datas, function (index, data) {
            if (!p.config.stringMode) {
                if (p.config.paramValue && p.config.paramValue != null)
                    c.append($("<input type='hidden' />").val(data.value).attr("name", p.config.paramValue + "[" + index + "]" + ".value"));
                if (p.config.paramTitle && p.config.paramTitle != null)
                    c.append($("<input type='hidden' />").val(data.title).attr("name", p.config.paramTitle + "[" + index + "]" + ".title"));
            } else {
                if (p.config.paramValue && p.config.paramValue != null)
                    c.append($("<input type='hidden' />").val(data.value).attr("name", p.config.paramValue));
            }
        });
        e.prepend(c);
    }
};
//
//var InputOject = function (selector) {
//    var cp = this;
//    cp.selector = selector;
//    if (selector.nodeType || selector.indexOf("#") == 0) {
//        var old = $(selector).data("FInput");
//        if (old && old != null)
//            return old;
//        $(selector).data("FInput", this);
//        $(cp.selector).find(".f-help").popover({
//            html: true,
//            trigger: 'hover',
//            content: function () {
//                return $(cp.selector).find(".f-help-text").html();
//            }
//        });
//        $(cp.selector).find(".f-error").each(function () {
//            $(this).popover({
//                html: true,
//                trigger: "hover",
//                content: function () {
//                    return $(cp.selector).find(".f-error-text").html();
//                }
//            });
//        });
//        $(cp.selector).find("input.s-number").each(function () {
//            var val = $(this).prop("value");
//            if (val && val != null) {
//                var chars = val.replace(/\s/g, '').split('').reverse();
//                val = "";
//                for (var i = 0; i < chars.length; i++) {
//                    val = chars[i] + val;
//                    if (i % 3 == 2 && i != chars.length - 1)
//                        val = " " + val;
//                }
//            }
//            $(this).prop("value", val);
//            $(this).on("blur.finput", function () {
//                var val = $(this).prop("value");
//                if (val && val != null) {
//                    var chars = val.replace(/\s/g, '').split('').reverse();
//                    val = "";
//                    for (var i = 0; i < chars.length; i++) {
//                        val = chars[i] + val;
//                        if (i % 3 == 2 && i != chars.length - 1)
//                            val = " " + val;
//                    }
//                    $(this).prop("value", val);
//                }
//            });
//            $(this).on("keyup.finput", function () {
//                var val = $(this).prop("value");
//                if (val && val != null) {
//                    var chars = val.replace(/\s/g, '').split('').reverse();
//                    val = "";
//                    for (var i = 0; i < chars.length; i++) {
//                        val = chars[i] + val;
//                        if (i % 3 == 2 && i != chars.length - 1)
//                            val = " " + val;
//                    }
//                    $(this).prop("value", val);
//                }
//            });
//        });
//    } else {
//        cp = [];
//        UID.set(selector);
//        $(selector).each(function () {
//            cp.push(new FInput("#" + this.id));
//        });
//    }
//    return cp;
//};