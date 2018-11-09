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

public abstract class FragmentSportsBinding extends ViewDataBinding {
  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final ConstraintLayout sportsFragmentContainer;

  @NonNull
  public final RecyclerView sportsRecyclerView;

  @NonNull
  public final SwipeRefreshLayout sportsSwipeRefresh;

  @Bindable
  protected Boolean mDisplay;

  protected FragmentSportsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ProgressBar progressBar, ConstraintLayout sportsFragmentContainer,
      RecyclerView sportsRecyclerView, SwipeRefreshLayout sportsSwipeRefresh) {
    super(_bindingComponent, _root, _localFieldCount);
    this.progressBar = progressBar;
    this.sportsFragmentContainer = sportsFragmentContainer;
    this.sportsRecyclerView = sportsRecyclerView;
    this.sportsSwipeRefresh = sportsSwipeRefresh;
  }

  public abstract void setDisplay(@Nullable Boolean display);

  @Nullable
  public Boolean getDisplay() {
    return mDisplay;
  }

  @NonNull
  public static FragmentSportsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentSportsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentSportsBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.fragment_sports, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSportsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentSportsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentSportsBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.fragment_sports, null, false, component);
  }

  public static FragmentSportsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentSportsBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentSportsBinding)bind(component, view, com.noblemajesty.newsapplication.R.layout.fragment_sports);
  }
}
