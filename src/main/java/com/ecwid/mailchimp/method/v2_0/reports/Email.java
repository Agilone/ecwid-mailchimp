package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;

/**
 * Created by ergin on 1/20/15.
 */
public class Email extends MailChimpObject {

    @MailChimpObject.Field
    public String email = null;

    @MailChimpObject.Field
    public String euid = null;

    @MailChimpObject.Field
    public String leid = null;

}