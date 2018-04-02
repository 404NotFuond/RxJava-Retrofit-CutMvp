package com.taopao.rxjavaretrofitcutmvp.ui.presenter;

import android.content.Context;

import com.taopao.rxjavaretrofitcutmvp.http.ApiRetrofit;
import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.rx.RxObserver;
import com.taopao.rxjavaretrofitcutmvp.rx.RxTransformer;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BasePresenter;
import com.taopao.rxjavaretrofitcutmvp.ui.view.CutMvpView;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 22:18
 * @Use:
 */

public class CutMvpPresenter extends BasePresenter<CutMvpView> {
    public CutMvpPresenter(Context context) {
        super(context);
    }

    public void getBanner(String loaction) {
        UIUtils.showWaitingDialog(mContext, "请骚等...");
        ApiRetrofit.getInstance()
                .getBanner(loaction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<BaseResult<ArrayList<BannerInfo>>>() {
                    @Override
                    public void onSuccess(BaseResult<ArrayList<BannerInfo>> arrayListBaseResult) {
                        //这里非空判断可不写,因为在view层销毁的时候rxjava 订阅者也取消回调了,不会再回调了(最好写上 万无一失)
                        if (getView() != null) {
                            getView().onGetBannerResult(arrayListBaseResult);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        //统一管理订阅者(此步不可省略)
                        addDisposable(d);
                    }
                });
    }

    public void getImgList(String url) {

        UIUtils.showWaitingDialog(mContext, "请骚等...");
        ApiRetrofit.getInstance()
                .getImgList(url)
                .compose(RxTransformer.<ImgListInfo>switchSchedulers())
                .subscribe(new RxObserver<ImgListInfo>() {
                    @Override
                    public void onSuccess(ImgListInfo imgListInfo) {
                        if(getView()!=null) {
                            getView().onGetImgListResult(imgListInfo);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
