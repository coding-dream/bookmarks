package com.less.bookmarks;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class CoreServer extends NanoHTTPD {

	public CoreServer(int port) {
		super(port);
	}

	@Override
	public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        Map<String, String> params = session.getParms();
        System.out.println(params);
        String json = "{'name':'xiaoming','age':20}";
        return newFixedLengthResponse(json);
	}

}
