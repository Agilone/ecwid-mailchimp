package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;

import java.util.Date;


/**
 * @author Ergin Demirel
 */
public class Opts extends MailChimpObject {

    @MailChimpObject.Field
    public Integer start = null;

    @MailChimpObject.Field
    public Integer limit = null;

    @MailChimpObject.Field
    public Date since = null;

}
