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
package com.ecwid.mailchimp.method.v2_0.reports;

import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v2_0.lists.MemberInfoData;

/**
 * @author Ergin Demirel
 */
public class ReportData extends MailChimpObject {

    @Field
    public MemberInfoData member;

    @Field
    public String date;

    @Field
    public String type;

    @Field
    public String absplit_group;

    @Field
    public String tz_group;

    @Field
    public String message;

    @Field
    public String reason;

    @Field
    public String reason_text;

    @Field
    public Integer opens;

    @Field
    public Integer clicks;

    @Field
    public String status;

}
