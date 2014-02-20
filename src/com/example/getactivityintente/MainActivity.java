package com.example.getactivityintente;

import java.util.List;

import com.example.getactivityintente.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getAllPackageName();
	}


	/**
	 * package名をPackageManagerで所得
	 */
	private void getAllPackageName() {
		PackageManager pManager = getPackageManager();
		List<ApplicationInfo> appInfoList = pManager.getInstalledApplications(PackageManager.GET_META_DATA);

		setListView(appInfoList);
	}

	/**
	 * 取得したpackage名をListViewに表示
	 * @param appInfoList
	 */
	private void setListView(List<ApplicationInfo> appInfoList) {
		ListView listView = (ListView)findViewById(id.package_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		for(ApplicationInfo info : appInfoList){
			adapter.add(info.packageName);
		}
		listView.setAdapter(adapter);

		//クリック処理
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView listView = (ListView) parent;

				String item = (String)listView.getItemAtPosition(position);
				PackageManager pManager = getPackageManager();
				Intent intent = pManager.getLaunchIntentForPackage(item);
				startActivity(intent);
			}
		});
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    */
}
