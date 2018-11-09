package com.noblemajesty.newsapplication.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class ActivityNewsBinding extends ViewDataBinding {
  @NonNull
  public final BottomNavigationView bottomNavigationBar;

  @NonNull
  public final FrameLayout fragmentContainer;

  @NonNull
  public final ConstraintLayout newsActivity;

  @NonNull
  public final Toolbar toolbar;

  protected ActivityNewsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, BottomNavigationView bottomNavigationBar, FrameLayout fragmentContainer,
      ConstraintLayout newsActivity, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bottomNavigationBar = bottomNavigationBar;
    this.fragmentContainer = fragmentContainer;
    this.newsActivity = newsActivity;
    this.toolbar = toolbar;
  }

  @NonNull
  public static ActivityNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNewsBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.activity_news, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityNewsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNewsBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.activity_news, null, false, component);
  }

  public static ActivityNewsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityNewsBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityNewsBinding)bind(component, view, com.noblemajesty.newsapplication.R.layout.activity_news);
  }
}
