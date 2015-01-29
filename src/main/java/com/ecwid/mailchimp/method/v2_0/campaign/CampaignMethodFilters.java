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
package com.ecwid.mailchimp.method.v2_0.campaign;

import com.ecwid.mailchimp.MailChimpObject;

/**
 * @author Erhin Demirel
 */
public class CampaignMethodFilters extends MailChimpObject {
    @Field
    public String campaign_id;

    @Field
    public String parent_id;

    @Field
    public String list_id;

    @Field
    public Integer folder_id;

    @Field
    public Integer template_id;

    @Field
    public String status;

    @Field
    public String type;

    @Field
    public String from_name;

    @Field
    public String from_email;

    @Field
    public String title;

    @Field
    public String subject;

    @Field
    public String sendtime_start;

    @Field
    public String sendtime_end;

    @Field
    public Boolean uses_segment;

    @Field
    public Boolean exact;


}
