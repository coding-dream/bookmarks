package com.less.bookmarks;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.less.bookmarks.bean.Bookmark;
import com.less.bookmarks.dao.BookmarkDao;

import fi.iki.elonen.NanoHTTPD;

public class CoreServer extends NanoHTTPD {

	public static final String CMD_ADD = "add";
	public static final String CMD_LIST = "list";
	public static final String CMD_DELETE = "delete";
	private BookmarkDao bookmarkDao = new BookmarkDao();

	public CoreServer(int port) {
		super(port);
		bookmarkDao.createTable();
	}

	@Override
	public Response serve(IHTTPSession session) {
        Bookmark bookmark = parseRequest(session);
        String response = createResponse(bookmark);
        return newFixedLengthResponse(response);
	}

	private String createResponse(Bookmark bookmark) {
		String response = "{\"code\" : 400, \"msg\" : \"error\"}";
		switch (bookmark.getCmd()) {
		case CMD_LIST:
			List<Bookmark> list = bookmarkDao.list();
			Gson gson = new Gson();
			String json = gson.toJson(list);
			System.out.println(json);
			response = response.replace("400", "200").replace("\"error\"", json) ;
			break;
		case CMD_ADD:
			bookmarkDao.saveOrUpdate(bookmark);
			response = response.replace("400", "200").replace("error", "success");
			break;
		case CMD_DELETE:
			bookmarkDao.delete(bookmark);
			response = response.replace("400", "200").replace("error", "success");
			break;
		default:
			break;
		}
		return response;
	}

	private Bookmark parseRequest(IHTTPSession session) {
		 Method method = session.getMethod();
	     String uri = session.getUri();
	     Map<String, String> params = session.getParms();
	     String opt = params.get("opt");
	     Bookmark bookmark = new Bookmark();
	     bookmark.setCmd("ignore");
	     try {
		     Gson gson = new Gson();
		     bookmark = gson.fromJson(opt, Bookmark.class);
		     return bookmark;
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return bookmark;
	}
}
