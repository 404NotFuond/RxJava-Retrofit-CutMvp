package com.taopao.rxjavaretrofitcutmvp.ui.view;

import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import java.util.ArrayList;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 22:19
 * @Use:
 */
public interface CutMvpView extends BaseView {
    void onGetBannerResult(BaseResult<ArrayList<BannerInfo>> banner);
    void onGetImgListResult(ImgListInfo imgListInfo);
}

