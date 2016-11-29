package com.example.jsoup;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private String url="http://fashion.chinadaily.com.cn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView list_item = (ListView) findViewById(R.id.list_item);
        
    }
    
    public void change(View v){
    	
    	new Thread(){
    		public void run() {
    			try {
					Document document = Jsoup.connect(url).get();
					ArrayList<String> list = new ArrayList<String>();
					
					//获取标题
					String title = document.title();
					//获得class
					Elements byclass = document.getElementsByClass("pingdao-lef");
					//
					Element element = byclass.get(0);
					//获得外部标签
					Elements li = element.getElementsByTag("li");
					for (int i = 0; i < li.size(); i++) {
						Element element2 = li.get(i);
						Elements byTag = element2.getElementsByTag("a");
						//获取网址
						String attr = byTag.attr("herf");
						String text = element2.text();
						
						Log.e("---------text", ""+text);
						Log.e("-------->attr", ""+attr);
						
						list.add(text+"----------"+attr);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		};
    	}.start();
    	
    }

}
