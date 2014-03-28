package com.sinaapp.sanrenxing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sinaapp.sanrenxing.exception.DBException;
import com.sinaapp.sanrenxing.util.DBUtil;

/** 
 * 类说明 
 * @author  程辉 
 * @version V1.0  创建时间：2012-12-18 下午12:11:48 
 */
public class WeiXinDao{

	private static Logger log = Logger.getLogger(WeiXinDao.class
			.getSimpleName());
	
	
	public void saveWeiXinTable(Map<String, String> msg) throws DBException {
		log.log(Level.INFO, msg.toString());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			conn = DBUtil.getDBConn();
			
			String sql = "insert into weixin_msg(from_user_name,to_user_name,create_time,msg_type,content,location_x,location_y,scale,pic_url,event,event_key) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, msg.get("FromUserName"));
			ps.setString(2, msg.get("ToUserName"));
			ps.setString(3, msg.get("CreateTime"));
			ps.setString(4, msg.get("MsgType"));
			ps.setString(5, msg.get("Content"));
			
			ps.setString(6, msg.get("Location_X"));
			ps.setString(7, msg.get("Location_Y"));
			ps.setString(8, msg.get("Scale"));
			ps.setString(9, msg.get("PicUrl"));
			ps.setString(10, msg.get("Event"));
			ps.setString(11, msg.get("EventKey"));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DBUtil.closeDB(rs, ps, conn);
		}
	}

	
	
	public String queryAutoReply(String key) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String result=null;
		try {
			conn = DBUtil.getDBConn();
			String sql = "SELECT content FROM weixin_auto_reply WHERE reply_key=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, key);
			rs=ps.executeQuery();
			
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DBUtil.closeDB(rs, ps, conn);
		}
		return result;
	}

	
	
	

}
