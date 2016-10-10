/**
 * Created by wouter on 17/07/15.
 */

base.plugin("mobielegidsen.site.styles", ["constants.mobielegidsen.core", "messages.mobielegidsen.core", function (MgConstants, MgMessages)
{
    //-----EDITOR STYLES-----
    //possible it's not loaded (eg. because the block wasn't used in the page)
    var MediumEditor = base.getPlugin("blocks.core.MediumEditor");
    if (MediumEditor) {
        // Styles is an array with objects
        // object is of type {value:"", text""}
        // value = "p:red" -> text before the colon is the tag, text after the colon are the classes that will be added
        // nothing after colon will remove allm classes, nothing before colon will not touch the tag
        // text is the text in the dropdown
        MediumEditor.setStylePickerStyles([

            {text: null, value: '<li class="dropdown-header">'+MgMessages.widgetTextStyles_sectionText+'</li>'},
            {text: MgMessages.widgetTextStyles_p, value: "p:"},
            {text: MgMessages.widgetTextStyles_pColor, value: "p:"+MgConstants.TEXT_STYLES_COLOR_CLASS},
            {text: null, value: '<li role="separator" class="divider"></li>'},

            {text: null, value: '<li class="dropdown-header">'+MgMessages.widgetTextStyles_sectionTitles+'</li>'},
            {text: MgMessages.widgetTextStyles_h1, value: "h1:"},
            {text: MgMessages.widgetTextStyles_h1Color, value: "h1:"+MgConstants.TEXT_STYLES_COLOR_CLASS},
            {text: MgMessages.widgetTextStyles_h2, value: "h2:"},
            {text: MgMessages.widgetTextStyles_h2Color, value: "h2:"+MgConstants.TEXT_STYLES_COLOR_CLASS},
            {text: MgMessages.widgetTextStyles_h3, value: "h3:"},
            {text: MgMessages.widgetTextStyles_h3Color, value: "h3:"+MgConstants.TEXT_STYLES_COLOR_CLASS},
            {text: null, value: '<li role="separator" class="divider"></li>'},

            {text: null, value: '<li class="dropdown-header">'+MgMessages.widgetTextStyles_sectionButtons+'</li>'},
            {text: MgMessages.widgetTextStyles_buttonBig, value: ":"+MgConstants.TEXT_STYLES_BUTTONS_BIG_CLASS},

        ]);
    }
}]);