# RatioView

You can use this lib to set specified proportion to the size of the Layout or View.

**RatioView with xml code**

    <com.github.rayboot.widget.ratioview.RatioImageView
        android:id="@+id/view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        ratio:fixRatio="fixWidth"
        ratio:widthRatio="16.0"
        ratio:heightRatio="16.0"
        android:background="#ff0000"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true" />


**RatioView with java code**

    new RatioImageView(this, RatioFixMode.FIX_WIDTH, 4, 3);

or

    ratioImageView.setRatio(RatioFixMode.FIX_WIDTH, 4, 3);



**Attr params info**

    fixRatio--  the ratio is calculated based on Width Or Height
    widthRatio -- the proportion of the size Width
    heightRatio -- proportion of the size Height

**Support View**

    RatioButton
    RatioEditText
    RatioFrameLayout
    RatioImageView
    RatioLinearLayout
    RatioRelativeLayout
    RatioTextView
    RatioViewPager


**Add your custom view**

You could add any other view to implements `RatioMeasureDelegate`  like RatioImageView.

public class RatioImageView extends ImageView implements RatioMeasureDelegate {

    private RatioViewDelegate mRatioLayoutDelegate;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, RatioFixMode mode, float fixWidth, float fixHeight) {
        super(context);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, mode, fixWidth, fixHeight);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mRatioLayoutDelegate = RatioViewDelegate.obtain(this, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mRatioLayoutDelegate != null){
            mRatioLayoutDelegate.onMeasure(widthMeasureSpec,heightMeasureSpec);
            widthMeasureSpec = mRatioLayoutDelegate.getWidthMeasureSpec();
            heightMeasureSpec = mRatioLayoutDelegate.getHeightMeasureSpec();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setDelegateMeasuredDimension(int measuredWidth, int measuredHeight) {
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void setRatio(RatioFixMode mode, float datumWidth, float datumHeight) {
        if(mRatioLayoutDelegate != null){
            mRatioLayoutDelegate.setRatio(mode,datumWidth,datumHeight);
        }
    }
}


**How to use**

	Gradle
	

    compile 'com.github.rayboot.widget:ratioview:1.0.1'

