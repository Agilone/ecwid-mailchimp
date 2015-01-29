package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v1_3.list.MemberInfo;

import java.util.List;

/**
 * Created by ergin on 1/20/15.
 */
public class ActivityData extends MailChimpObject {
    @Field
    public Email email;

    @Field
    public MemberInfo member;

    @MailChimpObject.Field
    public List<Activity> activity;

}