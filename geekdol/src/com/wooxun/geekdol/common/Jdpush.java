package com.wooxun.geekdol.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wooxun.geekdol.hbridge.model.VillageNotice;
import com.wooxun.geekdol.system.model.AppUserVillage;
import com.wooxun.geekdol.system.model.SendMessage;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 863soft-王肖东
 * @CreateDate 2016年8月20日
 * @param
 * @see
 * @modified 修改履历 ========================================================== No
 *           修改人员 修改日期 描述 1. 王肖东 2016年8月20日 上午11:27:17 创建
 *           ==========================================================
 * 
 */
public class Jdpush {

	// demo App defined in resources/jpush-api.conf

	public static String TITLE = "申通快递";
	public static String ALERT = "祝大家新春快乐";
	public static String MSG_CONTENT = "申通快递祝新老客户新春快乐";
	public static String REGISTRATION_ID = "0900e8d85ef";
	public static String TAG = "tag_api";

	public static JPushClient jpushClient = null;
	
	/**
	 * 推送消息
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午4:23:24
	 * @param mastersecretUser
	 * @param appkeyUser
	 * @param message
	 */
	public static boolean sendPush(String mastersecretUser, String appkeyUser, SendMessage message) {
		// 创建极光推送客户端对象
		JPushClient jpushClient = new JPushClient(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER), ComDefine.getMsg(ConstantStr.APPKEY_USER));
		
		// 设置推送标题
		TITLE = message.getTitle();
		// 设置Android推送内容
		ALERT = message.getContent();
		// 设置IOS推送内容
		MSG_CONTENT = message.getContent();
		
		// 设置推送模式
		PushPayload payload = buildPushObject_all_alias_alert();
		// 极光推送设置离线时间
		payload.resetOptionsTimeToLive(ConstantStr.TIMETOLIVE);

		try {
			// 进行推送
			PushResult result = jpushClient.sendPush(payload);
			
			// 向控制台打印控制结果
			System.out.println(result);

			// 返回结果
			return true;
		} catch (APIConnectionException e) {
			e.printStackTrace();

		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 推送小区通知公告
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年9月18日 上午9:57:18
	 * @param mastersecretUser
	 * @param appkeyUser
	 * @param villageNotice
	 * @param list
	 * @return
	 */
	public static boolean sendPush(String mastersecretUser, String appkeyUser, VillageNotice villageNotice, List<AppUserVillage> list) {
		// 创建极光推送客户端对象
		JPushClient jpushClient = new JPushClient(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER), ComDefine.getMsg(ConstantStr.APPKEY_USER));
		
		// 设置推送标题
		TITLE = villageNotice.getTitle();
		// 设置Android推送内容
		ALERT = villageNotice.getContent();
		// 设置IOS推送内容
		MSG_CONTENT = villageNotice.getContent();
		
		try {
			
			// 创建推送模式对象
			PushPayload payload = null;
			// 遍历需要推送的用户
			for (AppUserVillage auv:list) {
				if (StringUtils.isNotBlank(auv.getPushId())) {
					// 设置推送模式
					payload = buildPushObject_all_alias_alert_indi(auv.getPushId(), villageNotice.getId());
				}
			}
			
			// 极光推送设置离线时间
			payload.resetOptionsTimeToLive(ConstantStr.TIMETOLIVE);
			
			// 进行推送
			PushResult result = jpushClient.sendPush(payload);
			
			// 向控制台打印控制结果
			System.out.println(result);

			// 返回结果
			return true;
		} catch (APIConnectionException e) {
			e.printStackTrace();

		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午4:25:33
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}

	/**
	 * 构建推送对象：所有平台，推送目标是全部设备，通知内容为 ALERT。
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午4:25:20
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all())// 设置接受的平台
				.setAudience(Audience.all())// Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
				.setNotification(Notification.alert(ALERT))
				.build();
	}
	
	/**
	 * 构建推送对象：平台是 Android，目标是全部设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午4:24:03
	 * @return
	 */
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
	
	/**
	 * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，
	 * 推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，
	 * 通知声音为 "happy"，并且附加字段 from = "JPush"；
	 * 消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。
	 * APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午3:30:38
	 * @return
	 */
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
		return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy.caf")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }

	/**
	 * 构建推送对象：平台是 Andorid 与 iOS，
	 * 推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），
	 * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午3:27:09
	 * @return
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
	}
	
	/**
	 * 根据push_id个推
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午3:26:16
	 * @param pushId
	 * @param id
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert_indi(String pushId, Long id) {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("id", String.valueOf(id));
		extras.put("areaLevel", ConstantStr.AREA_LEVEL_05);
		return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(pushId))
                .setNotification(Notification.android(ALERT, TITLE, extras))
                .setMessage(Message.newBuilder()
                		.setTitle(TITLE)
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("id", id)
                        .build())
                .build();
    }
	
	/**
	 * 测试推送主方法
	 * @Title
	 * @Description
	 * @author:kangtianyu
	 * @CreateDate:2016年8月20日 下午3:26:00
	 * @param args
	 */
	public static void main(String args[]) {
		JPushClient jpushClient = new JPushClient(ComDefine.getMsg(ConstantStr.MASTERSECRET_USER), ComDefine.getMsg(ConstantStr.APPKEY_USER));

		PushPayload payload = buildPushObject_all_alias_alert_indi("1a0018970aae397413a", 257l);
//		PushPayload payload = buildPushObject_all_alias_alert();
		// 极光推送设置离线时间
		payload.resetOptionsTimeToLive(86400l);

		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);

		} catch (APIConnectionException e) {
			e.printStackTrace();

		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}

}
