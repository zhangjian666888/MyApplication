package com.example.myapplication.activaties;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.service.LoginService;
import com.example.myapplication.utils.SharedPreferencesUtils;

public class LoginActivaty extends AppCompatActivity implements
        View.OnFocusChangeListener,
        View.OnTouchListener,
        View.OnClickListener {

    private ImageButton loginButton;
    private ImageView clearAccount;
    private ImageView clearPassword;
    private EditText accountText;
    private EditText passwordText;
    private TextView phoneLoginButton;
    private TextView registerButton;
    private TextView retrievePasswordButton;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    JSONObject obj = (JSONObject) msg.obj;
                    if(obj.getBoolean("success")){
                        SharedPreferencesUtils.putString(LoginActivaty.this, "token", obj.getString("data"));
                        Intent intent = new Intent(LoginActivaty.this, RecoveryActivaty.class);
                        startActivity(intent);
                    }else {
                        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(LoginActivaty.this).inflate(R.layout.public_box, null);
                        TextView boxContent = (TextView) constraintLayout.findViewById(R.id.box_content);
                        boxContent.setText(obj.getString("msg"));
                        AlertDialog dialog = new AlertDialog.Builder(LoginActivaty.this).create();
                        dialog.setView(constraintLayout);
                        dialog.show();
                        dialog.setCanceledOnTouchOutside(true);
                        setAlertDialogBackground(dialog);
                        dialog.setCancelable(false);
                        TextView boxButton = (TextView) constraintLayout.findViewById(R.id.box_button);
                        boxButton.setOnClickListener(a->{
                            dialog.dismiss();
                        });
                    }
                    break;
            }
        }
    };
    private void setAlertDialogBackground(AlertDialog dialog){
        Window window = dialog.getWindow();
        if (null != window) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setGravity(Gravity.CENTER);
            WindowManager windowManager = LoginActivaty.this.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (display.getWidth() * 0.90f);
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
            window.getDecorView().setPadding(0, 0, 0, 0);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginButton = (ImageButton) findViewById(R.id.loginButton);
        clearAccount = (ImageView) findViewById(R.id.clearAccount);
        clearPassword = (ImageView) findViewById(R.id.clearPassword);
        accountText = (EditText) findViewById(R.id.account);
        passwordText = (EditText) findViewById(R.id.password);
        phoneLoginButton = (TextView) findViewById(R.id.phone_login_button);
        registerButton = (TextView) findViewById(R.id.register_button);
        retrievePasswordButton = (TextView) findViewById(R.id.retrieve_password_button);
        //初始化事件
        initEvent();
        accountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null && !"".equals(charSequence.toString())){
                    clearAccount.setVisibility(View.VISIBLE);
                }else {
                    clearAccount.setVisibility(View.INVISIBLE);
                    loginButton.setClickable(false);
                    loginButton.setBackgroundResource(R.drawable.longin_forbid_image);
                }
                String string = passwordText.getText().toString();
                if(string != null && !"".equals(string) && charSequence != null && !"".equals(charSequence.toString())){
                    loginButton.setClickable(true);
                    loginButton.setBackgroundResource(R.drawable.longin_image);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null && !"".equals(charSequence.toString())){
                    clearPassword.setVisibility(View.VISIBLE);
                }else {
                    clearPassword.setVisibility(View.INVISIBLE);
                    loginButton.setClickable(false);
                    loginButton.setBackgroundResource(R.drawable.longin_forbid_image);
                }
                String string = accountText.getText().toString();
                if(string != null && !"".equals(string) && charSequence != null && !"".equals(charSequence.toString())){
                    loginButton.setClickable(true);
                    loginButton.setBackgroundResource(R.drawable.longin_image);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initEvent() {
        loginButton.setOnClickListener(this);
        clearAccount.setOnClickListener(this);
        clearPassword.setOnClickListener(this);
        phoneLoginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        retrievePasswordButton.setOnClickListener(this);

        accountText.setOnTouchListener(this);
        passwordText.setOnTouchListener(this);

        accountText.setOnFocusChangeListener(this);
        passwordText.setOnFocusChangeListener(this);

        loginButton.setClickable(false);
        loginButton.setBackgroundResource(R.drawable.longin_forbid_image);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.loginButton){
            String accountString = accountText.getText().toString();
            String passwordString = passwordText.getText().toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", accountString);
            jsonObject.put("password", passwordString);
            LoginService.loginApp(jsonObject, mHandler);

        }else if(id == R.id.clearAccount){
            accountText.setText("");
            clearAccount.setVisibility(View.INVISIBLE);
            loginButton.setClickable(false);
            loginButton.setBackgroundResource(R.drawable.longin_forbid_image);
        }else if(id == R.id.clearPassword){
            passwordText.setText("");
            clearPassword.setVisibility(View.INVISIBLE);
            loginButton.setClickable(false);
            loginButton.setBackgroundResource(R.drawable.longin_forbid_image);
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if(id == R.id.account){
            String str = accountText.getText().toString();
            if(str != null && !"".equals(str)){
                clearAccount.setVisibility(View.VISIBLE);
            }else {
                clearAccount.setVisibility(View.INVISIBLE);
            }
        }else if(id == R.id.password){
            String str = passwordText.getText().toString();
            if(str != null && !"".equals(str)){
                clearPassword.setVisibility(View.VISIBLE);
            }else {
                clearPassword.setVisibility(View.INVISIBLE);
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        if(id == R.id.account && b){
            clearPassword.setVisibility(View.INVISIBLE);

        }else if(id == R.id.password && b){
            clearAccount.setVisibility(View.INVISIBLE);
        }
    }

}
