/*
 * Copyright 2012 Ecwid, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ecwid.mailchimp.method.campaign;

import com.ecwid.mailchimp.MailChimpObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ergin Demirel
 */
public class CampaignInformation extends MailChimpObject {

    @Field
    public String id;

    @Field
    public Integer web_id;

    @Field
    public String list_id;

    @Field
    public Integer folder_id;

    @Field
    public Integer template_id;

    @Field
    public String content_type;

    @Field
    public String title;

    @Field
    public String type;

    @Field
    public String create_time;

    @Field
    public String send_time;

    @Field
    public Integer emails_sent;

    @Field
    public String status;

    @Field
    public String from_name;

    @Field
    public String from_email;

    @Field
    public String subject;

    @Field
    public String to_name;

    @Field
    public String archive_url;

    @Field
    public Boolean inline_css;

    @Field
    public String analytics;

    @Field
    public String analytics_tag;

    @Field
    public Boolean authenticate;

    @Field
    public Boolean ecomm360;

    @Field
    public Boolean auto_tweet;

    @Field
    public String auto_fb_post;

    @Field
    public Boolean auto_footer;

    @Field
    public Boolean timewarp;

    @Field
    public String timewarp_schedule;

    @Field
    public String parent_id;


    @Field
    public Tracking tracking;


    @Field
    public String segment_text;

    //@Field
    //public SegmentOptions segment_opts;


    @Field
    public Map<String,Object> type_opts;


}
