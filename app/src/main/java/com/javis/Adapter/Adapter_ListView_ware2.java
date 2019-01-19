package com.javis.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjs.gjsmall.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Adapter_ListView_ware2
		extends BaseAdapter {
	private Context context;
	@SuppressWarnings("unused")
	private int[]   data;
	private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

	public Adapter_ListView_ware2(Context context, ArrayList<HashMap<String, Object>> arrayList) {
		Log.d("TEST_SIZE", "Size :"+arrayList.size());
		this.context = context;
		this.arrayList = arrayList;
	}

	public Adapter_ListView_ware2(Context context) {
		this.context = context;

	}

	@Override
	public int getCount() {
		return (arrayList != null && arrayList.size() == 0) ? 30: arrayList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup arg2) {
		HolderView holderView = null;
		if (currentView == null) {
			holderView = new HolderView();
			currentView = LayoutInflater.from(context).inflate(R.layout.adapter_listview_ware2, null);
			holderView.iv_pic = (ImageView) currentView.findViewById(R.id.iv_adapter_list_pic);
			holderView.tv_name = (TextView) currentView.findViewById(R.id.name);
			holderView.tv_price = (TextView) currentView.findViewById(R.id.price);
			holderView.tv_sale_num = (TextView) currentView.findViewById(R.id.sale_num);
			holderView.store_adress=(TextView) currentView.findViewById(R.id.store_adress);
			holderView.store_leixin=(TextView) currentView.findViewById(R.id.store_leixin);

			currentView.setTag(holderView);
		} else {
			holderView = (HolderView) currentView.getTag();
		}
		if (arrayList.size() != 0) {



			String   store_company_name   = null;
			String  area_info = null;
			String   store_address   = null;
			String  goods_image_url = null;
			String store_id= null;


			store_company_name = arrayList.get(position).get("store_company_name").toString().trim();
			area_info = arrayList.get(position).get("area_info").toString().trim();
			store_address = arrayList.get(position).get("store_address").toString().trim();
			goods_image_url = arrayList.get(position).get("store_address").toString().trim();
			store_id = arrayList.get(position).get("store_id").toString().trim();

			holderView.store_adress.setText(area_info+store_address);
			holderView.tv_name.setText(store_company_name);
			holderView.tv_price.setText("502 条评论");
			holderView.tv_sale_num.setText("1.6 km");
			holderView.store_leixin.setText("超市");

//			DownloadImageTask localDownloadImageTask = new DownloadImageTask(holderView.iv_pic);
//			String[]          arrayOfString          = new String[1];
//			arrayOfString[0] = pic;
//			localDownloadImageTask.execute(arrayOfString);
			String[]  url={
					"http://img3.imgtn.bdimg.com/it/u=417249318,2599530597&fm=21&gp=0.jpg",
					"http://img4.imgtn.bdimg.com/it/u=1508006953,2855535457&fm=21&gp=0.jpg",
					"http://img2.imgtn.bdimg.com/it/u=2194533857,89892105&fm=21&gp=0.jpg"};
			//控件加载图片
			//显示图片的配置
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.ic_launcher)
					.showImageOnFail(R.drawable.ic_launcher)
					.cacheInMemory(true)
					.cacheOnDisk(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.build();
			ImageLoader.getInstance().displayImage("" + url[position], holderView.iv_pic, options); // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件


//			ImageLoader.ImageListener listener = ImageLoader.getImageListener(holderView.iv_pic, R.drawable.ic_launcher, R.drawable.ic_launcher);
//			CU_VolleyTool.getInstance(context).getmImageLoader().get("http://192.168.0.111:3000/taoBao/img/" + arrayList.get(position).get("pic"), listener);

//			holderView.tv_name.setText(arrayList.get(position).get("name").toString().trim());
//			holderView.tv_price.setText("￥" + arrayList.get(position).get("price").toString().trim() + "Ԫ");
//			holderView.tv_sale_num.setText("月销量:" + arrayList.get(position).get("sale_num").toString() + "件     " + arrayList.get(position).get("address").toString().trim());
		}
		return currentView;
	}

	public class HolderView {

		private ImageView iv_pic;
		private TextView  tv_sale_num;
		private TextView  tv_name;
		private TextView  tv_price;
		private TextView store_adress;
		private TextView store_leixin;
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Drawable>
	{
		ImageView imageView;
		private DownloadImageTask(ImageView imageView)
		{

			this.imageView = imageView;
		}

		protected Drawable doInBackground(String[] paramArrayOfString)
		{
			return loadImageFromNetwork(paramArrayOfString[0]);
		}

		protected void onPostExecute(Drawable paramDrawable)
		{
			imageView.setImageDrawable(paramDrawable);
		}
	}

	private Drawable loadImageFromNetwork(String paramString)
	{
		Drawable localDrawable1;
		try
		{
			Drawable localDrawable2 = Drawable.createFromStream(new URL(paramString).openStream(), "image.jpg");
			localDrawable1 = localDrawable2;
			if (localDrawable1 == null)
			{
				Log.d("test", "null drawable");
				return localDrawable1;
			}
		}
		catch (IOException localIOException)
		{
			while (true)
			{
				Log.d("test", localIOException.getMessage());
				localDrawable1 = null;
			}
		}
		return localDrawable1;
	}



}