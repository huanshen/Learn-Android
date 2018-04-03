package com.example.zy.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shenjiaqi on 2018/3/21.
 */

public class MyDialog extends Dialog {
    /** TAG */
    private static final String TAG = "MyDialog";
    private Context mContext;
    /** 根布局 */
    private LinearLayout mRoot;
    /** 关闭按钮 */
    private TextView mCloseBtn;
    /** 分割线 */
    private View mDividerLine;
    /** dialog标题 */
    private TextView mTitle;
    /** dialog描述 */
    private TextView mDesc;
    /** dialog图片 */
    private ImageView mImage;


    public MyDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setCanceledOnTouchOutside(false);
    }


    /**
     * 初始化关注 Feed 弹屏 Dialog
     */
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, null);
        setContentView(view);
        mRoot = (LinearLayout) view.findViewById(R.id.root);
        mTitle = (TextView) view.findViewById(R.id.dialog_title);
        mTitle.setTextColor(mContext.getResources().getColor(R.color.dialog_title_color));
        mImage = (ImageView) view.findViewById(R.id.dialog_image);
        mImage.setBackground(mContext.getResources().getDrawable(R.drawable.head));
        mDesc = (TextView) view.findViewById(R.id.dialog_desc);
        mDesc.setTextColor(mContext.getResources().getColor(R.color.dialog_desc_color));
        mCloseBtn = (TextView) view.findViewById(R.id.dialog_close);
        mCloseBtn.setTextColor(mContext.getResources().getColor(R.color.dialog_close_color));
        mCloseBtn.setBackground(mContext.getResources().getDrawable(R.drawable.bg_cu));
        mDividerLine = view.findViewById(R.id.dialog_divider);
        mDividerLine.setBackgroundColor(mContext.getResources().getColor(R.color.dialog_divider_color));
        mRoot.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_bg));

        // 设置关闭按钮点击监听
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo 打点
                closeDialog();
            }
        });

    }

    /**
     * 关闭弹窗
     */
    public void closeDialog() {
        dismiss();
    }

    /**
     * 弹出看听模式引导提示框
     *
     * @param context 上下文
     */
    public static void showRalModelGuideDialog(Context context) {
        if (!(context instanceof Activity)) {
            return;
        }
        MyDialog dialog = new MyDialog(context, R.style.dialog);
        dialog.show();
    }
}

