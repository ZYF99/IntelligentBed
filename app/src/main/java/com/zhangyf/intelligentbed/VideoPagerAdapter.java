package com.zhangyf.intelligentbed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;
import java.util.function.Consumer;

public class VideoPagerAdapter extends PagerAdapter {

    Context context;
    List<View> viewList;

    public VideoPagerAdapter(Context context, List<View> list) {
        this.context = context;
        viewList = list;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        //         super.destroyItem(container, position, object);
    }

    //目的是展示title上的文字，
    @Override
    public CharSequence getPageTitle(int position) {
        return "";

    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View videoItem = viewList.get(position);
        StandardGSYVideoPlayer videoPlayer = videoItem.findViewById(R.id.video_player);
        videoPlayer.setBottomProgressBarDrawable(null);

        if (position == 0){
            videoPlayer.setUp(Constants.rtmpAddress_1, true, "");
        }

        else if (position == 1){
            videoPlayer.setUp(Constants.rtmpAddress_2, true, "");
        }


        //增加封面
/*        val imageView = ImageView(this)
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        imageView.setImageResource(R.mipmap.xxx1)
        videoPlayer.setThumbImageView(imageView)*/
/*        //增加title
        binding.detailPlayer.titleTextView.visibility = View.VISIBLE*/


        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);

        container.addView(videoItem);
        return videoItem;
    }

    void releaseVideo() {
        viewList.forEach(view -> {
            StandardGSYVideoPlayer player = view.findViewById(R.id.video_player);
            player.release();
        });
    }

}
