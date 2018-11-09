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

public abstract class FragmentNewsBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout newsFragmentContainer;

  @NonNull
  public final RecyclerView newsRecyclerView;

  @NonNull
  public final SwipeRefreshLayout newsSwipeRefresh;

  @NonNull
  public final ProgressBar progressBar;

  @Bindable
  protected Boolean mDisplay;

  protected FragmentNewsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ConstraintLayout newsFragmentContainer, RecyclerView newsRecyclerView,
      SwipeRefreshLayout newsSwipeRefresh, ProgressBar progressBar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.newsFragmentContainer = newsFragmentContainer;
    this.newsRecyclerView = newsRecyclerView;
    this.newsSwipeRefresh = newsSwipeRefresh;
    this.progressBar = progressBar;
  }

  public abstract void setDisplay(@Nullable Boolean display);

  @Nullable
  public Boolean getDisplay() {
    return mDisplay;
  }

  @NonNull
  public static FragmentNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentNewsBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.fragment_news, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentNewsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentNewsBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.fragment_news, null, false, component);
  }

  public static FragmentNewsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentNewsBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentNewsBinding)bind(component, view, com.noblemajesty.newsapplication.R.layout.fragment_news);
  }
}
