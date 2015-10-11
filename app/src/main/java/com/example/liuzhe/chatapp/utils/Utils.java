package com.example.liuzhe.chatapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * The Class Utils is a common class that hold many kind of different useful
 * utility methods.
 */
public class Utils
{

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @param btn1
	 *            the btn1
	 * @param btn2
	 *            the btn2
	 * @param listener1
	 *            the listener1
	 * @param listener2
	 *            the listener2
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg, String btn1,
			String btn2, DialogInterface.OnClickListener listener1,
			DialogInterface.OnClickListener listener2)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		// builder.setTitle(R.string.app_name);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton(btn1, listener1);
		if (btn2 != null && listener2 != null)
			builder.setNegativeButton(btn2, listener2);

		AlertDialog alert = builder.create();
		alert.show();
		return alert;

	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @param btn1
	 *            the btn1
	 * @param btn2
	 *            the btn2
	 * @param listener1
	 *            the listener1
	 * @param listener2
	 *            the listener2
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg, int btn1,
			int btn2, DialogInterface.OnClickListener listener1,
			DialogInterface.OnClickListener listener2)
	{

		return showDialog(ctx, ctx.getString(msg), ctx.getString(btn1),
				ctx.getString(btn2), listener1, listener2);

	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @param btn1
	 *            the btn1
	 * @param btn2
	 *            the btn2
	 * @param listener
	 *            the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg, String btn1,
			String btn2, DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, msg, btn1, btn2, listener,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id)
					{

						dialog.dismiss();
					}
				});

	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @param btn1
	 *            the btn1
	 * @param btn2
	 *            the btn2
	 * @param listener
	 *            the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg, int btn1,
			int btn2, DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, ctx.getString(msg), ctx.getString(btn1),
				ctx.getString(btn2), listener);

	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @param listener
	 *            the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg,
			DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, msg, ctx.getString(android.R.string.ok), null,
				listener, null);
	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @param listener
	 *            the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg,
			DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, ctx.getString(msg),
				ctx.getString(android.R.string.ok), null, listener, null);
	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg)
	{

		return showDialog(ctx, msg, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id)
			{

				dialog.dismiss();
			}
		});

	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param msg
	 *            the msg
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg)
	{

		return showDialog(ctx, ctx.getString(msg));

	}

	/**
	 * Show dialog.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param title
	 *            the title
	 * @param msg
	 *            the msg
	 * @param listener
	 *            the listener
	 */
	public static void showDialog(Context ctx, int title, int msg,
			DialogInterface.OnClickListener listener)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton(android.R.string.ok, listener);
		builder.setTitle(title);
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * Hide keyboard.
	 * 
	 * @param ctx
	 *            the ctx
	 */
	public static final void hideKeyboard(Activity ctx)
	{

		if (ctx.getCurrentFocus() != null)
		{
			InputMethodManager imm = (InputMethodManager) ctx
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(ctx.getCurrentFocus().getWindowToken(),
					0);
		}
	}

	/**
	 * Hide keyboard.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param v
	 *            the v
	 */
	public static final void hideKeyboard(Activity ctx, View v)
	{

		try
		{
			InputMethodManager imm = (InputMethodManager) ctx
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


    /**
     * 公共处理，保存登录帐户信息
     * @author liuzhe
     * @param pu
     */
    private static ParseObject userinfo = new ParseObject("UserInfo");

    public static void SaveUserInfo(ParseUser pu){
//        userinfo.put("username",pu.getUsername());
//        userinfo.put("emal",pu.getEmail());
//        userinfo.put("usertoken",pu.getSessionToken());
//        userinfo.pinInBackground();

        pu.pinInBackground();
    }
    /**
     * 公共处理，取消保存登录信息
     * @author liuzhe
     * @param pu
     */
    public static void UnsaveUserInfo(ParseUser pu) throws ParseException {
        try {
            pu.unpin();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("Utils:error", e.getMessage());
        }
    }



}
