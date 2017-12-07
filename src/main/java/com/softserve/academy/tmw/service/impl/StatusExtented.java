package com.softserve.academy.tmw.service.impl;

import net.rcarz.jiraclient.RestClient;
import net.rcarz.jiraclient.Status;
import net.sf.json.JSONObject;

public class StatusExtented extends Status {
    protected StatusExtented(RestClient restclient, JSONObject json) {
        super(restclient, json);
    }
}
