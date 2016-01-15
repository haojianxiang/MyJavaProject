package util;



/**
 * 返回码常量
 * @author dengrenfu
 *
 */
public class HtranslateReturnCode {
	/**
	 * 返回码：处理成功
	 */
	public static final String SUCCESS = "00000";
	/**
	 * 返回码：系统异常
	 */
	public static final String SYSTEM_ERROR = "00001";

	/**
	 * 返回码：传入对象为空
	 */
	public static final String INPUTOBJ_NULL = "00002";
	/**
	 * 返回码：更新对象不存在
	 */
	public static final String UPDATEOBJ_NOTEXIST = "00003";
	/**
	 * 返回码：插入对象已存在
	 */
	public static final String INSERTOBJ_EXIST = "00004";
	/**
	 * 返回码：保存对象为空
	 */
	public static final String SAVEOBJ_NULL = "00005";
	/**
	 * 返回码：卖家信息为空
	 */
	public static final String INPUTOBJ_SUPPLIERID_NULL = "00006";
    
}
