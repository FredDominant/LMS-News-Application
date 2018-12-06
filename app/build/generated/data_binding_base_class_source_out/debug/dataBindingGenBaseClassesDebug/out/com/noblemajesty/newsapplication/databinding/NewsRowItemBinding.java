package com.noblemajesty.newsapplication.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class NewsRowItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView newsAbstractField;

  @NonNull
  public final ImageView newsImage;

  @NonNull
  public final TextView newsJournalist;

  @NonNull
  public final TextView newsPublishDate;

  @NonNull
  public final TextView newsTitle;

  @Bindable
  protected String mTitle;

  @Bindable
  protected String mNewsAbstract;

  @Bindable
  protected String mByline;

  @Bindable
  protected String mPublishedDate;

  @Bindable
  protected String mImage;

  protected NewsRowItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView newsAbstractField, ImageView newsImage,
      TextView newsJournalist, TextView newsPublishDate, TextView newsTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.newsAbstractField = newsAbstractField;
    this.newsImage = newsImage;
    this.newsJournalist = newsJournalist;
    this.newsPublishDate = newsPublishDate;
    this.newsTitle = newsTitle;
  }

  public abstract void setTitle(@Nullable String title);

  @Nullable
  public String getTitle() {
    return mTitle;
  }

  public abstract void setNewsAbstract(@Nullable String newsAbstract);

  @Nullable
  public String getNewsAbstract() {
    return mNewsAbstract;
  }

  public abstract void setByline(@Nullable String byline);

  @Nullable
  public String getByline() {
    return mByline;
  }

  public abstract void setPublishedDate(@Nullable String publishedDate);

  @Nullable
  public String getPublishedDate() {
    return mPublishedDate;
  }

  public abstract void setImage(@Nullable String image);

  @Nullable
  public String getImage() {
    return mImage;
  }

  @NonNull
  public static NewsRowItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static NewsRowItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<NewsRowItemBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.news_row_item, root, attachToRoot, component);
  }

  @NonNull
  public static NewsRowItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static NewsRowItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<NewsRowItemBinding>inflate(inflater, com.noblemajesty.newsapplication.R.layout.news_row_item, null, false, component);
  }

  public static NewsRowItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static NewsRowItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (NewsRowItemBinding)bind(component, view, com.noblemajesty.newsapplication.R.layout.news_row_item);
  }
}
