package com.mosl.volunteer.core;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mosl.app.core.events.DataAction;
import com.mosl.app.coreui.adapters.ViewPagerAdapter;
import com.mosl.app.coreui.base.LeanBaseFragment;
import com.mosl.app.coreui.base.NavigationElement;
import com.mosl.app.coreui.views.CustomViewPager;

import com.mosl.volunteer.R;
import com.mosl.volunteer.util.PagerLoaderManager;


import timber.log.Timber;

import static com.mosl.volunteer.menu.MenuGetter.ARG_SUB_SECTION_ID;


/**
 * Created by lalkishore on 1/20/2017.
 */

public abstract class MOSLCommonPager extends MOSLCommonFragment
        implements ViewPagerAdapter.Helper, PagerLoaderManager {
    public CustomViewPager mPager;
    public final ViewPager.OnPageChangeListener pageChangeListener =
            new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset,
                                           int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    MOSLCommonPager.this.onPageSelection(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };
    public TabLayout mIndicator;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPager = view.findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(3);
        mIndicator = view.findViewById(R.id.tab_layout);
    }

    @Override
    public final void onShowProgress(MOSLCommonFragment currFragment) {
        if (currFragment == null || mPager == null) return;
        if (currFragment.equals(getAdapter().getFragmentAt(mPager.getCurrentItem()))) {
            showProgress();
        }
    }

    @Override
    public final void onHideProgress(MOSLCommonFragment currFragment) {
        if (currFragment == null || mPager == null) return;
        if (currFragment.equals(getAdapter().getFragmentAt(mPager.getCurrentItem()))) {
            hideProgress();
        }
    }

    public void onPageSelection(int position) {
        dispatchSendServiceRequest(position);
    }

    @Override
    public void onFragmentReopened(Bundle args) {
        int newFragSectionId = -1, openFragSectionId = -1;
        if (args != null && getArguments() != null) {
            newFragSectionId = args.getInt(ARG_SUB_SECTION_ID);
            openFragSectionId = getArguments().getInt(ARG_SUB_SECTION_ID);
        }
        if (newFragSectionId == openFragSectionId) {
            Timber.d("Already on the same page");
        } else {
            openPage(newFragSectionId);
        }
    }

    public void openPage(int position) {
        if (mPager != null) {
            mPager.setCurrentItem(position);
        }
    }

    @Override
    public abstract @Nullable
    Fragment getItem(int pos);

    public abstract ViewPagerAdapter getAdapter();

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        Timber.e(
                "onFragmentResume Page : %s, Added : %s, Visible : %s, isHidden : %s, Detached : %s",
                this, isAdded(), isVisible(), isHidden(), isDetached());
        dispatchOnFragmentResume(mPager);
        //TODO Find Out, Why does a pager need to dispatch sendServiceRequest in onFragmentResume
        //dispatchSendServiceRequest(mPager);
    }

    @Override
    @CallSuper
    public void onFragmentResult(@NonNull Bundle data) {
        ViewPagerAdapter adapter = getAdapter();
        if (mPager != null && adapter != null) {
            LeanBaseFragment fragmentAt = adapter.getFragmentAt(mPager.getCurrentItem());
            if (fragmentAt != null) {
                fragmentAt.onFragmentResult(data);
            }
        }
    }

    private void dispatchOnFragmentResume(ViewPager mPager) {
        if (mPager != null && getAdapter() != null) {
            LeanBaseFragment fragmentAt = getAdapter().getFragmentAt(mPager.getCurrentItem());
            if (fragmentAt instanceof NavigationElement) {
                ((NavigationElement) fragmentAt).onFragmentResume();
            }
        }
    }

    @Override
    public void sendServiceRequest() {
        dataActionSubject.onNext(DataAction.Retry.INSTANCE);
        dispatchSendServiceRequest(mPager);
    }

    private void dispatchSendServiceRequest(ViewPager mPager) {
        if (mPager != null) {
            dispatchSendServiceRequest(mPager.getCurrentItem());
        }
    }

    public final void dispatchSendServiceRequest(int pos) {
        ViewPagerAdapter adapter = getAdapter();
        if (mPager != null && adapter != null) {
            LeanBaseFragment fragmentAt = adapter.getFragmentAt(pos);
            if (fragmentAt instanceof MOSLPagerItemFragment) {
                MOSLPagerItemFragment pagerItemFragment = (MOSLPagerItemFragment) fragmentAt;
                pagerItemFragment.setLoadData(true);
                pagerItemFragment.sendServiceRequest();
            }
        }
    }
}
