package com.nfdaily.nfplus1.util;

import android.util.Log;

import com.android.network.NFHttpResponseListener;
import com.android.network.RequestUtil;
import com.android.nfRequest.LogError;
import com.nfdaily.nfplus1.application.ReaderApplication;
import com.nfdaily.nfplus1.bean.Account;
import com.nfdaily.nfplus1.constant.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：用户行为采集工具类。用于向服务器提交各类用户点击事件。
 * @author Dongyue
 */
public class UtilCmsEventSubmit {
	private static final String TAG = "EventSubmitUtil";

	private ReaderApplication app;

	public UtilCmsEventSubmit(ReaderApplication app) {
		this.app = app;

	}

	/**
	 * 文章点击事件
	 */
	public void submitArticleClickEvent(String fileId) {
		// articleclick
		String url = Config.getHostUrl(Config.URL_POST_EVENT);
		Map<String, String> params = new HashMap<>();
		params.put("id", fileId);
		params.put("type", "0");
		params.put("eventType", "0");
		params.put("userID", "0");
		params.put("userOtherID", AppUtil.getMyUUID());
		params.put("siteID", String.valueOf(1));

		RequestUtil.httpPost(ReaderApplication.getInstance(), url, params, new NFHttpResponseListener<String>() {
			@Override
			public void onErrorResponse(LogError logError) {

			}

			@Override
			public void onResponse(String result) {
				Log.e("my", "submitArticleClickEvent result:" + result);
			}
		});
	}

	/**
	 * 列表页视频统计接口：
	 * 1.统计视频播放时间。2.点击次数，接口自动加1。
	 */
	public void submitListVideoEvent(String fileId, String playTime) {
		String url = Config.getHostUrl(Config.URL_POST_EVENT);

		Map<String, String> params = new HashMap<>();
		params.put("id", fileId);
		params.put("type", "0");
		params.put("eventType", "0");
		params.put("userID", "0");
		params.put("userOtherID", AppUtil.getMyUUID());
		params.put("siteID", String.valueOf(1));
		params.put("time", playTime);
		RequestUtil.httpPost(ReaderApplication.getInstance(), url, params, new NFHttpResponseListener<String>() {
			@Override
			public void onErrorResponse(LogError logError) {

			}

			@Override
			public void onResponse(String result) {
				Log.e("my", "submitListVideoEvent result:" + result);
			}
		});
	}



	/**
	 * 文章统计接口
	 * @param fileId 稿件id，专题和外链新闻取与之相关联的新闻id
	 * @param type 对象类型type：0——稿件；1——评论；2——论坛；3——直播
	 * @param eventType 事件类型eventType：0——点击；1——点赞；2——分享；
	 *                     3——分享页点击；4——举报 5——南方号入口 6——南方号订阅号入口
	 */
	public void submitArticleShareEvent(String fileId, String type,String eventType) {

		String url = Config.getHostUrl(Config.URL_POST_EVENT);

		Map<String, String> params = new HashMap<>();
		params.put("id", fileId);
		params.put("type", type);
		params.put("eventType", eventType);
		params.put("userID", "0");
		params.put("userOtherID", AppUtil.getMyUUID());
		params.put("siteID", String.valueOf(1));
		RequestUtil.httpPost(ReaderApplication.getInstance(), url, params, new NFHttpResponseListener<String>() {
			@Override
			public void onErrorResponse(LogError logError) {

			}

			@Override
			public void onResponse(String result) {
				Log.e("my", "submitArticleShareEvent result:" + result);
			}
		});
	}


	/**
	 * 获取请求数据URL
	 *
	 * @param urlSB
	 *            服务器url
	 * @param columnID
	 *            推荐栏目id
	 * @param columnName
	 *            推荐栏目名
	 * @return 请求字符串
	 */
	public String getRecommendURL(StringBuffer urlSB, int columnID,
								  String columnName) {

		urlSB.append("?siteId=").append(1).append("&appid=")
				.append(1).append("&dev=")
				.append(app.getDeviceId())
				.append("&t=")
				.append(System.currentTimeMillis())
				.append("&uid=")
				.append(getUserId())
				// TODO bid shenmegui
				.append("&bid=").append("6accd1f0-3d1b-4527-8be5-0417aaedc3a1")
				.append("&cname=").append(columnName).append("&rule=")
				.append("").append("&rule_view=").append("true")
				.append("&param_view=").append("true").append("&attrs=")
				.append("aid,title").append("&debug=").append("false");
		return urlSB.toString();

	}


	private String getUserId() {
		String userID = "";

		Account account = new Account().getFromCache();
		if (account != null) {
			try {
				userID = account.getUserId();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userID;
	}
}
