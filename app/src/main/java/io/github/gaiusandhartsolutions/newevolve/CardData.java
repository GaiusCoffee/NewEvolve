package io.github.gaiusandhartsolutions.newevolve;

import android.view.View;

/**
 * Created by ErikGaius on 6/12/2016.
 */
public class CardData {
    protected int image;
    protected String title;
    protected String content;
    protected View.OnClickListener clickListener;

    public CardData(int image, String title, String content) {
        this(image, title, content, null);
    }

    public CardData(int image, String title, String content, View.OnClickListener clickListener) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.clickListener = clickListener;
    }
}