package com.mosl.groupiex.helper.databind;

import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;

public class GroupieViewHolder<T extends ViewDataBinding> extends com.mosl.groupiex.helper.groupie.GroupieViewHolder {
    public final T binding;

    public GroupieViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
