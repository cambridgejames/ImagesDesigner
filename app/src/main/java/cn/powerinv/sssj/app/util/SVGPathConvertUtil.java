package cn.powerinv.sssj.app.util;

import javafx.scene.shape.SVGPath;

public class SVGPathConvertUtil {
    /**
     * 根据路径信息获取SVGPath
     *
     * @param path 路径信息
     * @return 创建的SVGPath对象
     */
    public static SVGPath toSVGPath(String path) {
        SVGPath solutionSVGPath = new SVGPath();
        solutionSVGPath.setContent(path);
        return solutionSVGPath;
    }
}
