package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;

import java.util.List;

/**
 * Created by ergin on 1/20/15.
 */
public class ErrorData extends MailChimpObject {
    @Field
    public List<Email> email;

    @Field
    public String msg;

}