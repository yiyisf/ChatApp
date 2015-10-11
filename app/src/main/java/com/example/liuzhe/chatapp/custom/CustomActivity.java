package com.example.liuzhe.chatapp.custom;

import android.app.ActionBar;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.liuzhe.chatapp.R;
import com.example.liuzhe.chatapp.Views.HeaderLayout;
import com.example.liuzhe.chatapp.utils.TouchEffect;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * This is a common activity that all other activities of the app can extend to
 * inherit the common behaviors like implementing a common interface that can be
 * used in all child activities.
 */
public class CustomActivity extends FragmentActivity implements OnClickListener
{

    protected HeaderLayout mHeaderLayout;
	/**
	 * Apply this Constant as touch listener for views to provide alpha touch
	 * effect. The view must have a Non-Transparent background.
	 */
	public static final TouchEffect TOUCH = new TouchEffect();



    /* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);
		setupActionBar();
	}

	/**
	 * This method will setup the top title bar (Action bar) content and display
	 * values. It will also setup the custom background theme for ActionBar. You
	 * can override this method to change the behavior of ActionBar for
	 * particular Activity
	 */
	protected void setupActionBar()
	{
		final ActionBar actionBar = getActionBar();
		if (actionBar == null)
			return;
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.icon);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.actionbar_bg));
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
	}

    /**
     * 只有title initTopBarLayoutByTitle
     * @Title: initTopBarLayoutByTitle
     * @throws
     */
    public void initTopBarForOnlyTitle(String titleName) {
        mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.DEFAULT_TITLE);
        mHeaderLayout.setDefaultTitle(titleName);
    }

    /**
     * 初始化标题栏-带左右按钮
     * @return void
     * @throws
     */
    public void initTopBarForBoth(String titleName, int rightDrawableId,String text,
                                  HeaderLayout.onRightImageButtonClickListener listener) {
        mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.drawable.base_action_bar_back_bg_selector,
                new OnLeftButtonClickListener());
        mHeaderLayout.setTitleAndRightButton(titleName, rightDrawableId, text,
                listener);
    }

    public void initTopBarForBoth(String titleName, int rightDrawableId,
                                  HeaderLayout.onRightImageButtonClickListener listener) {
        mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.drawable.base_action_bar_back_bg_selector,
                new OnLeftButtonClickListener());
        mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
                listener);
    }


    /**
	 * Sets the touch and click listener for a view with given id.
	 * 
	 * @param id
	 *            the id
	 * @return the view on which listeners applied
	 */
	public View setTouchNClick(int id)
	{

		View v = setClick(id);
		if (v != null)
			v.setOnTouchListener(TOUCH);
		return v;
	}

	/**
	 * Sets the click listener for a view with given id.
	 * 
	 * @param id
	 *            the id
	 * @return the view on which listener is applied
	 */
	public View setClick(int id)
	{

		View v = findViewById(id);
		if (v != null)
			v.setOnClickListener(this);
		return v;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{

	}

    Toast mToast;

    /**
     * 公共处理，用于显示提示信息
     * @param text
     */
    public void ShowToast(final String text){
        if (!TextUtils.isEmpty(text)){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null){
                        mToast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    }else {
                        mToast.setText(text);
                    }

                    mToast.show();
                }
            });
        }

    }


    private class OnLeftButtonClickListener implements HeaderLayout.onLeftImageButtonClickListener {

        @Override
        public void onClick() {
            finish();
        }
    }
}
