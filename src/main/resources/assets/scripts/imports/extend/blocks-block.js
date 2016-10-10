/**
 * Created by wouter on 17/07/15.
 */
base.plugin("mobielegidsen.imports.Block", ["base.core.Class", "constants.blocks.core", "constants.mobielegidsen.core", "messages.mobielegidsen.core", "blocks.core.Sidebar", "blocks.imports.All", function (Class, BlocksConstants, MgConstants, MgMessages, Sidebar, All)
{
    var MgBlock = this;
    this.TAGS = All.IMPORTS;

    var BlocksBlock = base.getPlugin("blocks.imports.Block");
    if (BlocksBlock) {
        (this.Class = Class.create(BlocksBlock.Class, {

            //-----VARIABLES-----

            //-----CONSTRUCTORS-----
            constructor: function ()
            {
                MgBlock.Class.Super.call(this);
            },

            //-----IMPLEMENTED METHODS-----
            getConfigs: function (block, element)
            {
                var retVal = MgBlock.Class.Super.prototype.getConfigs.call(this, block, element);

                retVal.push(this.addOptionalClass(Sidebar, block.element, MgMessages.sidebarBlockSeamless, BlocksConstants.SEAMLESS_CLASS));

                return retVal;
            },

            //-----PRIVATE METHODS-----

        })).register(this.TAGS);
    }
}]);