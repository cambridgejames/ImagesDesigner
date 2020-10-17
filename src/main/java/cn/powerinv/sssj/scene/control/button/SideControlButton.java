package cn.powerinv.sssj.scene.control.button;

import javafx.scene.AccessibleRole;
import javafx.scene.control.ButtonBase;

public class SideControlButton extends ButtonBase {
    public static enum Alignment {LEFT, RIGHT, BOTTOM}

    private String icon;
    private Alignment align;

    public SideControlButton() {
        super();
        initialize();
    }

    public SideControlButton(String text, String icon, Alignment align) {
        super(text);
        this.setIcon(icon);
        this.setAlign(align);
        initialize();
    }

    @Override
    public void fire() {

    }

    private void initialize() {
        getStyleClass().setAll("button");
        setAccessibleRole(AccessibleRole.BUTTON);
        setMnemonicParsing(true);     // enable mnemonic auto-parsing by default
    }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public Alignment getAlign() { return align; }
    public void setAlign(Alignment align) { this.align = align; }
}
