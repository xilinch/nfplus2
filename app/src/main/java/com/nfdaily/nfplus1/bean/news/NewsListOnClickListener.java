package com.nfdaily.nfplus1.bean.news;

import android.util.Log;
import android.view.View;

import com.nfdaily.nfplus1.util.UtilsDataCollect;

import java.util.HashMap;

/**
 * Created by xilinch on 18-1-2.
 */

public class NewsListOnClickListener implements View.OnClickListener {

    /**
     * 新闻对象
     */
    private ArticleBean articleBean;

    /**
     * 来源
     */
    private String parentColumnId;


    public NewsListOnClickListener(ArticleBean articleBean, String parentColumnId){
        this.articleBean = articleBean;
        this.parentColumnId = parentColumnId;
    }



    @Override
    public void onClick(View v) {
        cmsSubmit();
        dataCollect();
        if(articleBean != null){
            articleBean.onclick(v.getContext());
        }
    }

    /**
     * CMS原来的统计
     */
    private void cmsSubmit(){

    }

    /**
     * 大数据统计
     */
    private void dataCollect(){
        //大数据采集(从列表页面打开新闻操作)
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("dataType", String.valueOf(UtilsDataCollect.DATATYPE.ACTION_ARTICLE_LIST_CLICK.getValue()));
            params.put("articleID", String.valueOf(articleBean.getFileId()));
            params.put("origin", String.valueOf(parentColumnId));
            UtilsDataCollect.sendData(params);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("专题列表点击打开新闻大数据异常:", e.getLocalizedMessage());
        }
    }
}
