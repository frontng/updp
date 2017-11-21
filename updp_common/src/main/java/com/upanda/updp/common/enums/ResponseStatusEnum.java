package com.upanda.updp.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应状态码
 * @date 2017年9月5日
 * @author Front Ng
 *
 */
public enum ResponseStatusEnum {

	/**
	 * 客户端应当继续发送请求。 这个临时响应是用来通知客户端它的部分请求已经被服务器接收，且仍未被拒绝。
	 * 客户端应当继续发送请求的剩余部分，或者如果请求已经完成，忽略这个响应。 服务器必须在请求完成后向客户端发送一个最终响应。
	 */
	Continue("100", "部分请求已经被服务器接收,客户端应当继续发送请求"),

	/**
	 * 请求已成功，请求所希望的响应头或数据体将随此响应返回。
	 */
	OK("200", "请求成功"),

	/**
	 * 被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个 URI 之一。
	 * 如果可能，拥有链接编辑功能的客户端应当自动把请求的地址修改为从服务器反馈回来的地址。除非额外指定，否则这个响应也是可缓存的。 新的永久性的URI
	 * 应当在响应的 Location 域中返回。除非这是一个 HEAD 请求，否则响应的实体中应当包含指向新的 URI 的超链接及简短说明。 如果这不是一个
	 * GET 或者 HEAD 请求，因此浏览器禁止自动进行重定向，除非得到用户的确认，因为请求的条件可能因此发生变化。 注意：对于某些使用 HTTP/1.0
	 * 协议的浏览器，当它们发送的 POST 请求得到了一个301响应的话，接下来的重定向请求将会变成 GET 方式。
	 */
	MovedPermanently("301", "请求的资源已被永久移动到新位置"),

	/**
	 * 1、语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求。 2、请求参数有误。
	 */
	BadRequest("400", "错误请求，参数有误"),

	/**
	 * 当前请求需要用户验证。该响应必须包含一个适用于被请求资源的 WWW-Authenticate 信息头用以询问用户信息。 客户端可以重复提交一个包含恰当的
	 * Authorization 头信息的请求。 如果当前请求已经包含了 Authorization 证书，那么401响应代表着服务器验证已经拒绝了那些证书。
	 * 如果401响应包含了与前一个响应相同的身份验证询问，且浏览器已经至少尝试了一次验证，
	 * 那么浏览器应当向用户展示响应中包含的实体信息，因为这个实体信息中可能包含了相关诊断信息。 参见RFC 2617。
	 */
	Unauthorized("401", "未授权的请求"),

	/**
	 * 服务器已经理解请求，但是拒绝执行它。 与401响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。 如果这不是一个 HEAD
	 * 请求，而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。
	 * 当然服务器也可以返回一个404响应，假如它不希望让客户端获得任何信息。
	 */
	Forbidden("403", "请求被拒绝执行"),

	/**
	 * 请求失败，请求所希望得到的资源未被在服务器上发现。 没有信息能够告诉用户这个状况到底是暂时的还是永久的。
	 * 假如服务器知道情况的话，应当使用410状态码来告知旧资源因为某些内部的配置机制问题，已经永久的不可用，而且没有任何可以跳转的地址。
	 * 404这个状态码被广泛应用于当服务器不想揭示到底为何请求被拒绝或者没有其他适合的响应可用的情况下。 出现这个错误的最有可能的原因是服务器端没有这个页面。
	 */
	NotFound("404", "请求的资源不存在"),

	/**
	 * 请求行中指定的请求方法不能被用于请求相应的资源。 该响应必须返回一个Allow 头信息用以表示出当前资源能够接受的请求方法的列表。 鉴于
	 * PUT，DELETE
	 * 方法会对服务器上的资源进行写操作，因而绝大部分的网页服务器都不支持或者在默认配置下不允许上述请求方法，对于此类请求均会返回405错误。
	 */
	MethodNotAllowed("405", "请求方法不被允许"),

	/**
	 * 请求超时。客户端没有在服务器预备等待的时间内完成一个请求的发送。客户端可以随时再次提交这一请求而无需进行任何更改。
	 */
	RequestTimeout("408", "请求超时"),


	/**
	 * 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。 一般来说，这个问题都会在服务器端的源代码出现错误时出现。
	 */
	InternalServerError("500", "服务器遇到了一个未曾预料的状况");

	private String value;
	private String displayName;

	static Map<String, ResponseStatusEnum> enumMap = new HashMap<String, ResponseStatusEnum>();
	static {
		for (ResponseStatusEnum type : ResponseStatusEnum.values()) {
			enumMap.put(type.getValue(), type);
		}
	}

	private ResponseStatusEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public static ResponseStatusEnum getEnum(String value) {
		return enumMap.get(value);
	}
}