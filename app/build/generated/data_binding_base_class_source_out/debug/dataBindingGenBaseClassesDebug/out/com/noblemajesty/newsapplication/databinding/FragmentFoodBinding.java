package com.noblemajesty.newsapplication.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public abstract class FragmentFoodBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView foodRecyclerView;

  @NonNull
  public final SwipeRefreshLayout foodSwipeRefresh;

  @NonNull
  public final ConstraintLayout newsFragmentContainer;

  @NonNull
  public final ProgressBar progressBar;

  @Bindable
  protected Boolean mDisplay;

  protected FragmentFoodBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView foodRecyclerView, SwipeRefreshLayout foodSwipeRefresh,
      ConstraintLayout newsFragmentContainer, ProgressBar progressBar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.foodRecyclerView = foodRecyclerView;
    this.foodSwipeRefresh = foodSwipeRefresh;
    this.newsFragmentContainer = newsFragmentContainer;
    this.progressBar = progressBar;
  }

  public abstract void setDisplay(@Nullable Boolean display);

  @Nullable
  public Boolean getDisplay() {
    return mDisplay;
  }

  @NonNull
  public static FragmentFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentFoodBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.fragment_food, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentFoodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentFoodBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.fragment_food, null, false, component);
  }

  public static FragmentFoodBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentFoodBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentFoodBinding)bind(component, view, com.noblemajesty.newsapplication.R.layout.fragment_food);
  }
}
