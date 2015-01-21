package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;

import java.util.List;

/**
 * Created by ergin on 1/20/15.
 */
public class MemberActivityData extends MailChimpObject {

    @Field
    public List<Activity> activity;


    public static class Activity extends MailChimpObject {
        @Field
        public String action;

        @Field
        public String timestamp;

        @Field
        public String url;

        @Field
        public String ip;
    }
}