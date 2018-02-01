package com.less.bookmarks.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.less.bookmarks.bean.Bookmark;
import com.less.bookmarks.util.JdbcUtils;

public class BookmarkDao {

	private String tableName = "bookmarks";

	private QueryRunner queryRunner = JdbcUtils.getQueryRunner("bookmarkdb");


	public void createTable(){
		try {
			String sql = String.format("create table if not exists %s(id integer primary key autoincrement, name varchar(255), url varchar(255) not null unique, cate varchar(50))", tableName);
			queryRunner.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveOrUpdate(Bookmark bookmark) {
		try {
			String[] params = new String[]{bookmark.getName(), bookmark.getUrl(), bookmark.getCate()};
			String sql = String.format("insert into %s(name, url, cate) values(?,?,?)", tableName);
			queryRunner.update(sql, params);
		} catch (Exception e) {
			String[] params = new String[]{bookmark.getName(), bookmark.getCate(),bookmark.getUrl()};
			String sql = String.format("update %s set name = ?, cate = ? where url=?", tableName);
			try {
				queryRunner.update(sql,params);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Bookmark> list(){
		try {
			String sql = String.format("select * from %s", tableName);
			List<Bookmark> list = queryRunner.query(sql, new BeanListHandler<Bookmark>(Bookmark.class));
			return list;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public void delete(Bookmark bookmark) {
		try {
			String[] params = new String[]{bookmark.getUrl()};
			String sql = String.format("delete from %s where url = ?", tableName);
			queryRunner.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
