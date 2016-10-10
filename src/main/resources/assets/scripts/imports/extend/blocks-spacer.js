/**
 * Created by wouter on 17/07/15.
 */
base.plugin("mobielegidsen.imports.BlocksSpacer", ["base.core.Class", "constants.blocks.core", "constants.mobielegidsen.core", "messages.mobielegidsen.core", "blocks.core.Sidebar", function (Class, BlocksConstants, MgConstants, MgMessages, Sidebar)
{
    var MgSpacer = this;

    var BlocksSpacer = base.getPlugin("blocks.imports.BlocksSpacer");
    if (BlocksSpacer) {
        (this.Class = Class.create(BlocksSpacer.Class, {

            //-----VARIABLES-----

            //-----CONSTRUCTORS-----
            constructor: function ()
            {
                MgSpacer.Class.Super.call(this);
            },

            //-----IMPLEMENTED METHODS-----
            getConfigs: function (block, element)
            {
                var retVal = MgSpacer.Class.Super.prototype.getConfigs.call(this, block, element);

                retVal.push(this.addOptionalClass(Sidebar, block.element, MgMessages.widgetSpacerColorsTitle, MgConstants.SPACER_STYLE_COLORED_CLASS));

                return retVal;
            },

            //-----PRIVATE METHODS-----

        })).register(BlocksSpacer.TAGS);
    }
}]);