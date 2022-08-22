package com.mosl.volunteer.dashboard;

import androidx.annotation.NonNull;
import com.mosl.volunteer.R;
import com.mosl.volunteer.databinding.ItemDashboardHeaderBinding;
import com.xwray.groupie.databinding.BindableItem;

public class DashboardHeaderItem extends BindableItem<ItemDashboardHeaderBinding> {
    private final String title;

    public DashboardHeaderItem(String title) {
        this.title = title;
    }

    @Override public void bind(@NonNull ItemDashboardHeaderBinding viewBinding, int position) {
        viewBinding.txtSectionTitle.setText(title);
    }

    @Override public int getLayout() {
        return R.layout.item_dashboard_header;
    }

    @Override public long getId() {
        return title.hashCode();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashboardHeaderItem that = (DashboardHeaderItem) o;

        return title.equals(that.title);
    }

    @Override public int hashCode() {
        return title.hashCode();
    }
}
