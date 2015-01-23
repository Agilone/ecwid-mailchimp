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

import com.ecwid.mailchimp.MailChimpAPIVersion;
import com.ecwid.mailchimp.MailChimpMethod;
import com.ecwid.mailchimp.MailChimpObject;

/**
 * See : https://apidocs.mailchimp.com/api/2.0/reports/unsubscribes.php
 *
 * @author Ergin Demirel
 */
@MailChimpMethod.Method(name = "reports/unsubscribes", version = MailChimpAPIVersion.v2_0)
public class UnsubscribeReportMethod extends MailChimpMethod<ReportResult> {
    @Field
    public String cid = null;

    @MailChimpObject.Field
    public Opts opts;

}
