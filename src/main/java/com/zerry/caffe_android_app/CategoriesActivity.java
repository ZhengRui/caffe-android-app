package com.zerry.caffe_android_app;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zerry on 8/8/16.
 */
public class CategoriesActivity extends Activity {
    private static final String TAG = "CategoriesActivity";
    private TaskSpec.SingleTask task;
    private List<ObjectItem.ItemBrief> mItemListBrief = new ArrayList<>();
    private List<ObjectItem.ItemFeat> mItemListFeat = new ArrayList<>();
    private boolean isNew;
    private CategoryAdapter mAdapter;
    private SwipeMenuListView mListView;
    private SwipeMenuCreator creator;
    private String pthPrefix = Environment.getExternalStorageDirectory().toString() + File.separator + "caffe-android-app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        task = (TaskSpec.SingleTask) getIntent().getExtras().getSerializable("task");
        task.taskDBPre = pthPrefix + File.separator + task.taskDBPre;
        Log.i(TAG, "entering " + task.taskName + " task");

        isNew = false;
        displayCategories();

        FloatingActionButton newObjItemBtn = (FloatingActionButton) findViewById(R.id.newObjItemBtn);
        newObjItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ObjectItem newItem = new ObjectItem("Unknown");
                mItemListBrief.add(newItem.itemBrief);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }


    private List<ObjectItem.ItemBrief> loadItems() {
        List<ObjectItem.ItemBrief> res = new ArrayList<>();
        File ifile = new File(task.taskDBPre + ".brief");
        if (ifile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(ifile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                res = (ArrayList<ObjectItem.ItemBrief>) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    };

    private void writeItems() {
        if (isNew) {
            // need further check of consistency

            File ofileBrief = new File(task.taskDBPre + ".brief");
            try {
                FileOutputStream fosBrief = new FileOutputStream(ofileBrief);
                ObjectOutputStream oosBrief = new ObjectOutputStream(fosBrief);
                oosBrief.writeObject(mItemListBrief);
                Log.i(TAG, "finished saving database to " + ofileBrief.getAbsolutePath());
                oosBrief.close();
                fosBrief.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            File ofileFeat = new File(task.taskDBPre + ".feat");
            try {
                FileOutputStream fosFeat = new FileOutputStream(ofileFeat);
                ObjectOutputStream oosFeat = new ObjectOutputStream(fosFeat);
                oosFeat.writeObject(mItemListFeat);
                Log.i(TAG, "finished saving database to " + ofileFeat.getAbsolutePath());
                oosFeat.close();
                fosFeat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            isNew = false;
        }
    }

    void displayCategories() {
        mItemListBrief = loadItems();
        mListView = (SwipeMenuListView) findViewById(R.id.listView);
        mAdapter = new CategoryAdapter();
        mListView.setAdapter(mAdapter);

        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Reset");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        mListView.setMenuCreator(creator);



    }


    class CategoryAdapter extends BaseSwipListAdapter {

        @Override
        public int getCount() {
            return mItemListBrief.size();
        }

        @Override
        public ObjectItem.ItemBrief getItem(int position) {
            return mItemListBrief.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

//        private void showToast(String text, int milliseconds) {
//            final Toast toast = Toast.makeText(CategoriesActivity.this, text, Toast.LENGTH_SHORT);
//            toast.show();
//
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    toast.cancel();
//                }
//            }, milliseconds);
//        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            ObjectItem.ItemBrief itemBrief = getItem(position);
            if (itemBrief.thumbnailPth == null)
                holder.iv_icon.setImageResource(getResources().getIdentifier("@mipmap/ic_unknown", null, getPackageName()));
            else
                holder.iv_icon.setImageDrawable(null);

            holder.tv_name.setText(itemBrief.itemName);

//            holder.iv_icon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showToast("iv_icon_click", 500);
//                }
//            });
//            holder.tv_name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showToast("tv_name_click", 500);
//                }
//            });

            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }

        @Override
        public boolean getSwipEnableByPosition(int position) {
            if(position % 2 == 0){
                return false;
            }
            return true;
        }
    }


}
