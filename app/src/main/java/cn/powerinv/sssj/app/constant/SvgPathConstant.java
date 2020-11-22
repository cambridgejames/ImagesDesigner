package cn.powerinv.sssj.app.constant;

import cn.powerinv.sssj.app.util.SVGPathConvertUtil;

import javafx.scene.shape.SVGPath;

/**
 * 主场景按钮图标常量类
 *
 * @since 2020-11-22
 */
public final class SvgPathConstant {
    /**
     * 最小化按钮图标
     */
    public static final SVGPath MINIMIZE_SVG_PATH =
            SVGPathConvertUtil.toSVGPath("M0 0 L0 1 L10 1 L10 0 Z");

    /**
     * 最大化按钮图标
     */
    public static final SVGPath MAXIMIZE_SVG_PATH =
            SVGPathConvertUtil.toSVGPath("M0 0 L0 10 L10 10 L10 0 L1 0 L1 1 L9 1 L9 9 L1 9 L1 0 Z");

    /**
     * 取消最大化按钮
     */
    public static final SVGPath RESTORE_SVG_PATH =
            SVGPathConvertUtil.toSVGPath("M0 2 L0 10 L8 10 L8 8 L10 8 L10 0 L2 0 L2 2 L1 2 L1 3 L3 3 " +
            "L3 1 L9 1 L9 7 L8 7 L8 2 L3 2 L3 3 L7 3 L7 9 L1 9 L1 2 Z");

    /**
     * 关闭窗口按钮
     */
    public static final SVGPath CANCEL_SVG_PATH =
            SVGPathConvertUtil.toSVGPath("M0 0 L0 1 L4 5 L0 9 L0 10 L1 10 L5 6 L9 10 L10 10 L10 9 L6 5 " +
                    "L10 1 L10 0 L9 0 L5 4 L1 0 Z");
}
