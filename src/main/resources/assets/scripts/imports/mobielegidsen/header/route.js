base.plugin("mobielegidsen.imports.header.routeText", ["base.core.Class", "blocks.imports.Text", "blocks.core.MediumEditor", "blocks.core.Sidebar", function (Class, Text, Editor, Sidebar)
{
    var MgHeaderRouteText = this;
    //TODO why doesn't this work with [data-property=name] appended ??
    this.TAGS = ["mobielegidsen-header-route"];

    (this.Class = Class.create(Text.Class, {

        //-----VARIABLES-----

        //-----CONSTRUCTORS-----
        constructor: function ()
        {
            MgHeaderRouteText.Class.Super.call(this);
        },

        //-----IMPLEMENTED METHODS-----
        getConfigs: function (block, element)
        {
            return MgHeaderRouteText.Class.Super.prototype.getConfigs.call(this, block, element);
        },

        //-----PRIVATE METHODS-----

    })).register(this.TAGS);

}]);