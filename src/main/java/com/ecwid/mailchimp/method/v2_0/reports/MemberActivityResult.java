package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;

import java.util.List;

/**
 * Created by ergin on 1/20/15.
 */
public class MemberActivityResult extends MailChimpObject {

    @Field
    public Integer success_count;

    @Field
    public Integer error_count;

    @Field
    public List<ErrorData> errors;

    @Field
    public List<ActivityData> data;

}
