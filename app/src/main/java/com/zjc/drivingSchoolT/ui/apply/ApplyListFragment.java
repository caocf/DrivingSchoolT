package com.zjc.drivingSchoolT.ui.apply;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.ui.widget.empty.EmptyLayout;
import com.zjc.drivingSchoolT.R;
import com.zjc.drivingSchoolT.api.ApiHttpClient;
import com.zjc.drivingSchoolT.api.ResultResponseHandler;
import com.zjc.drivingSchoolT.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingSchoolT.db.model.OrderItem;
import com.zjc.drivingSchoolT.db.parser.OrderListResponseParser;
import com.zjc.drivingSchoolT.db.response.OrderListResponse;
import com.zjc.drivingSchoolT.ui.apply.adapter.ApplyOrderAdapter;
import com.zjc.drivingSchoolT.utils.Constants;
import com.zjc.drivingSchoolT.utils.ConstantsParams;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ApplyListFragment extends ZBaseToolBarFragment implements SwipeRefreshLayout.OnRefreshListener, ZBaseRecyclerViewAdapter.OnLoadMoreListener, ZBaseRecyclerViewAdapter.OnItemClickListener {
    private EasyRecyclerView mRecyclerView;
    private ApplyOrderAdapter mAdapter;

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_apply_history);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.order_manager_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        initAdapter();
        findOrders();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
        mRecyclerView.setRefreshListener(this);
    }

    private void initAdapter() {
        mAdapter = new ApplyOrderAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //跳转到预约详情界面
        OrderItem orderItem = (OrderItem) mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, orderItem.getOrid());
//        startActivity(ApplyActivity.class, bundle);
    }

    @Override
    public void onRefresh() {
        ApiHttpClient.getInstance().startOrders(SharePreferencesUtil.getInstance().readUser().getUid(), ConstantsParams.PAGE_START, new ResultResponseHandler(getActivity(), mRecyclerView) {

            @Override
            public void onResultSuccess(String result) {
                OrderListResponse orderListResponse = new OrderListResponseParser().parseResultMessage(result);
                mAdapter.clear();
                mAdapter.addAll(orderListResponse.getOrderitems());
                isLoadFinish(orderListResponse.getOrderitems().size());
            }
        });
    }

    private void findOrders() {
        ApiHttpClient.getInstance().startOrders(SharePreferencesUtil.getInstance().readUser().getUid(), ConstantsParams.PAGE_START, new ResultResponseHandler(getActivity(), getEmptyLayout()) {

            @Override
            public void onResultSuccess(String result) {
                OrderListResponse orderListResponse = new OrderListResponseParser().parseResultMessage(result);
                mAdapter.addAll(orderListResponse.getOrderitems());
                isLoadFinish(orderListResponse.getOrderitems().size());
            }
        });
    }

    @Override
    public void onLoadMore() {
        int start = mAdapter.getCount();
        ApiHttpClient.getInstance().startOrders(SharePreferencesUtil.getInstance().readUser().getUid(), start, new ResultResponseHandler(getActivity()) {

            @Override
            public void onResultSuccess(String result) {
                OrderListResponse orderListResponse = new OrderListResponseParser().parseResultMessage(result);
                mAdapter.addAll(orderListResponse.getOrderitems());
                isLoadFinish(orderListResponse.getOrderitems().size());
            }
        });
    }

    /**
     * 加载完成
     */
    public boolean isLoadFinish(int size) {
        if (size == 0) {
            getEmptyLayout().setErrorType(EmptyLayout.NODATA_ENABLE_CLICK);
            return true;
        }

        if (size < ConstantsParams.PAGE_SIZE) {
            mAdapter.stopMore();
            mAdapter.setNoMore(R.layout.view_nomore);
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
