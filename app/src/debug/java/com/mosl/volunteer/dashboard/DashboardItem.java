package com.mosl.volunteer.dashboard;

import androidx.annotation.NonNull;
import com.mosl.volunteer.R;
import com.mosl.volunteer.databinding.ItemDashboardBinding;
import com.xwray.groupie.databinding.BindableItem;

/**
 * Created by krutikapatil on 27-12-2017.
 */

public class DashboardItem extends BindableItem<ItemDashboardBinding> {
    private final DashboardData dashboardData;

    public DashboardItem(DashboardData dashboardData) {
        this.dashboardData = dashboardData;
    }

    @Override public int getLayout() {
        return R.layout.item_dashboard;
    }

    @Override public void bind(@NonNull ItemDashboardBinding viewBinding, int position) {
        viewBinding.txtPageName.setText(dashboardData.title);
    }

    public Integer getData() {
        return dashboardData.pageId;
    }

    @Override public long getId() {
        return dashboardData.pageId;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashboardItem that = (DashboardItem) o;

        return dashboardData.equals(that.dashboardData);
    }

    @Override public int hashCode() {
        return dashboardData.hashCode();
    }
}
