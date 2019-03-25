package Common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;

public class MongolTextView extends android.support.v7.widget.AppCompatTextView {
    private TextPaint textPaint;

    public MongolTextView(Context context, AttributeSet attributeSet, int defstyle){
        super(context,attributeSet,defstyle);
        init();
    }
    public MongolTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MongolTextView(Context context) {
        super(context);
        init();
    }
    public void init()
    {
        //MongolFontMirrored
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/MAM8101.ttf");
        setTypeface(tf);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(),getMeasuredWidth());
    }
    @Override
    protected  void onDraw(Canvas canvas)
    {
        textPaint = getPaint();
        textPaint.setColor(getCurrentTextColor());
        textPaint.drawableState = getDrawableState();

        canvas.save();

        // flip and rotate the canvas
        canvas.translate(getWidth(), 0);
        canvas.rotate(90);
        //canvas.translate(0, getWidth());
        //canvas.scale(1, -1);
        //canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

        getLayout().draw(canvas);

        canvas.restore();
    }
}
