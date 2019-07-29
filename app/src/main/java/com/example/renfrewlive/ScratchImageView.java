package com.example.renfrewlive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.cooltechworks.utils.BitmapUtils;

public class ScratchImageView extends AppCompatImageView {

    public interface IRevealListener {
        public void onRevealed(ScratchImageView iv);
        public void onRevealPercentChangedListener(ScratchImageView siv, float percentage);
    }

    public static final float STROKE_WIDTH = 12f;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

//    Bitmap holding the scratch region.
    private Bitmap mScratchBitmap;

    //Drawable canvas area through which the scratchable area is drawn
    private Canvas mCanvas;

    //Path holding the ereasing path done by the user
    private Path mEreasePath;

    //Path to indicate where the user has touched
    private Path mTouchPath;

    //Paint properties for drawing the scratch area
    private Paint mBitmapPaint;

    //Paint properties for ereasing the scratch region
    private Paint mEreasePaint;

    //Gradiant paint properties that lies as a background fpr scratch region
    private Paint mGradientBgPaint;

    //Sample Drawable bitmap having the scratch pattern
    private BitmapDrawable mDrawable;

    //Listener object callback reference to send back the callback when the image has been revealed
    private IRevealListener mRevealListener;

    //Reveal percentage value
    private float mRevealPercentage;

    //Thread Count
    private int mThreadCount = 0;

    public ScratchImageView(Context context) {
        super(context);
        init();
    }

    public ScratchImageView(Context context, AttributeSet set) {
        super(context, set);
        init();
    }

    public ScratchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*Set the strokes width based on the parameter mulitplier.
    * @param mulitpler can be 1,2,3 and so on to set the stroke width of the paint.*/
    public void setStrokeWidth(float multiplier) {
        mEreasePaint.setStrokeWidth(multiplier * STROKE_WIDTH);
    }

    //Initialise the paint drawing elements
    private void init() {

        mTouchPath = new Path();

        mEreasePaint = new Paint();
        mEreasePaint.setAntiAlias(true);
        mEreasePaint.setDither(true);
        mEreasePaint.setColor(0xFFFF0000);
        mEreasePaint.setStyle(Paint.Style.STROKE);
        mEreasePaint.setStrokeJoin(Paint.Join.BEVEL);
        mEreasePaint.setStrokeCap(Paint.Cap.ROUND);
        setStrokeWidth(6);

        mGradientBgPaint = new Paint();

        mEreasePath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        Bitmap scratchBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_scratch_pattern);
        mDrawable = new BitmapDrawable(getResources(), scratchBitmap);

        setEreaserMode();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScratchBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mScratchBitmap);

        Rect rect = new Rect(0, 0, mScratchBitmap.getWidth(), mScratchBitmap.getHeight());
        mDrawable.setBounds(rect);

        int startGradientColor = ContextCompat.getColor(getContext(), R.color.scratch_start_gradient);
        int endGradientColor = ContextCompat.getColor(getContext(), R.color.scratch_end_gradient);

        mGradientBgPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), startGradientColor, endGradientColor, Shader.TileMode.MIRROR));

        mCanvas.drawRect(rect, mGradientBgPaint);
        mDrawable.draw(mCanvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mScratchBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mEreasePath, mEreasePaint);

    }

    private void touch_start(float x, float y) {
        mEreasePath.reset();
        mEreasePath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    //clears the scratch area to reveal the hidden image
    public void clear() {
        int[] bounds = getImageBounds();
        int left = bounds[0];
        int top = bounds[1];
        int right = bounds[2];
        int bottom = bounds[3];

        int width = right - left;
        int height = bottom - top;
        int centerX = left + width / 2;
        int centerY = top + height / 2;

        left = centerX - width / 2;
        top = centerY -  height / 2;
        right = left + width;
        bottom = top + height;

        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        mCanvas.drawRect(left, top, right, bottom, paint);
        checkReveal();
        invalidate();
    }

    private void touch_move(float x, float y) {

        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mEreasePath.quadTo(mX, mY, (x - mX) / 2, (y - mY) / 2);

            mX = x;
            mY = y;

            drawPath();
        }

        mTouchPath.reset();
        mTouchPath.addCircle(mX, mY, 30, Path.Direction.CW);
    }

    private void drawPath() {
        mEreasePath.lineTo(mX, mY);

        //Commit the path to out offscreen
        mCanvas.drawPath(mEreasePath, mEreasePaint);
        //Kill this so we do not double draw
        mTouchPath.reset();
        mEreasePath.reset();
        mEreasePath.moveTo(mX, mY);

        checkRevealed();
    }

    public void checkReveal() {
        clear();
    }

    private void touch_up() {
        drawPath();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public int getColor() {
        return mEreasePaint.getColor();
    }

    public Paint getEreasePaint() {
        return mEreasePaint;
    }

    public void setEreaserMode() {

        getEreasePaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void setRevealListener(IRevealListener listener) {
        this.mRevealListener = listener;
    }

    public boolean isRevealed() {
        return mRevealPercentage == 1;
    }

    @SuppressLint("StaticFieldLeak")
    private void checkRevealed() {

        if (!isRevealed() && mRevealListener != null) {

            int[] bounds = getImageBounds();
            int left = bounds[0];
            int top = bounds[1];
            int width = bounds[2] - left;
            int height = bounds[3] - top;

            //Do not create multiple calls to compare
            if (mThreadCount > 1) {
                Log.d("Captcha", "Count greater than 1");
                return;
            }

            mThreadCount++;

            new AsyncTask<Integer, Void, Float>() {

                @Override
                protected Float doInBackground(Integer... params) {
                    try {

                        int left = params[0];
                        int top = params[1];
                        int width = params[2];
                        int height = params[3];

                        Bitmap croppedBitmap = Bitmap.createBitmap(mScratchBitmap, left, top, width, height);

                        return BitmapUtils.getTransparentPixelPercent(croppedBitmap);
                    }finally {
                        mThreadCount--;
                    }
                }

                public void onPostExecute(Float percentRevealed) {

                    //check if not revealed before
                    if (!isRevealed()) {

                        float oldValue = mRevealPercentage;
                        mRevealPercentage = percentRevealed;

                        if (oldValue != percentRevealed) {
                            mRevealListener.onRevealPercentChangedListener(ScratchImageView.this, percentRevealed);
                        }
                        //if now revealed
                        if (isRevealed()) {
                            mRevealListener.onRevealed(ScratchImageView.this);
                        }
                    }
                }
            }.execute(left,top,width,height);
        }
    }

    public int[] getImageBounds() {

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int vwidth = getWidth() - paddingLeft - paddingRight;
        int vheight = getHeight() - paddingTop - paddingBottom;

        int centerX = vwidth / 2;
        int centerY = vheight / 2;

        Drawable drawable = getDrawable();
        Rect bounds = drawable.getBounds();

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        if (width <= 0) {
            width = bounds.right - bounds.left;
        }

        if (height <= 0) {
            height = bounds.bottom - bounds.top;
        }



        if (height > vheight) {
            height = vheight;
        }

        if (width > vwidth) {
            width = vwidth;
        }

        ScaleType scaleType = getScaleType();

        int left;
        int top;

        switch (scaleType) {
            case FIT_START:
                left = paddingLeft;
                top = centerY - height / 2;
                break;
            case FIT_END:
                left = vwidth - paddingRight - width;
                top = centerY - height / 2;
                break;
            case CENTER:
                left = centerX - width / 2;
                top = centerY - width / 2;
                break;
            default:
                left = paddingLeft;
                top = paddingTop;
                width = vwidth;
                height = vheight;
                break;
        }
        return new int[] {left, top, left + width, top + height};
    }

}
