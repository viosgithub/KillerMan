package com.foo.KillerMan;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.foo.KillerMan.R;

public class KillerMan extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningApp = am.getRunningAppProcesses();
		PackageManager packageManager = getPackageManager();
		if(runningApp!=null)
		{
			for(RunningAppProcessInfo app:runningApp)
			{
				try
				{
					ApplicationInfo appInfo = packageManager.getApplicationInfo(app.processName, 0);
					Log.d("debug","packagename?:"+app.processName);
					if(app.processName.equals("com.foo.KillerMan"))Log.d("debug","killer man was found!!");
					else
					{
						//kill package
						am.killBackgroundProcesses(app.processName);
					}
				}
				catch(NameNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			am.killBackgroundProcesses("com.foo.KillerMan");
		}
	}
}