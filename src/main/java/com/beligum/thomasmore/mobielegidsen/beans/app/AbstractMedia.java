package com.beligum.thomasmore.mobielegidsen.beans.app;

import com.beligum.base.utils.Logger;
import com.beligum.base.utils.toolkit.FileFunctions;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Media;

import java.io.IOException;
import java.net.URI;

/**
 * Created by bram on 4/14/16.
 */
public abstract class AbstractMedia implements Media
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private URI url;
    private String type;
    private String mimeType;

    //-----CONSTRUCTORS-----
    protected AbstractMedia(URI url)
    {
        this.url = url;

        if (url!=null) {
            try {
                this.mimeType = FileFunctions.getMimeTypeByFileName(url.getPath());
                this.type = this.mimeType == null ? null : this.mimeType.substring(0, this.mimeType.indexOf("/"));
            }
            catch (IOException e) {
                Logger.error("Error while trying to get the mime-type of an url; "+url);
            }
        }
    }

    //-----PUBLIC METHODS-----
    @Override
    public URI getUrl()
    {
        return url;
    }
    @Override
    public String getType()
    {
        return type;
    }
    @Override
    public String getMimeType()
    {
        return mimeType;
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----

}
