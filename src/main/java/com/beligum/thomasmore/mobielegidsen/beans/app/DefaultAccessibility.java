package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Accessibility;

/**
 * Created by bram on 5/20/16.
 */
public class DefaultAccessibility implements Accessibility
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private String title;
    private String instructions;

    //-----CONSTRUCTORS-----
    public DefaultAccessibility(String title, String instructions)
    {
        this.title = title;
        this.instructions = instructions;
    }

    //-----PUBLIC METHODS-----
    @Override
    public String getTitle()
    {
        return title;
    }
    @Override
    public String getInstructions()
    {
        return instructions;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
