package com.noblemajesty.newsapplication;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.noblemajesty.newsapplication.databinding.ActivityNewsBindingImpl;
import com.noblemajesty.newsapplication.databinding.FragmentFoodBindingImpl;
import com.noblemajesty.newsapplication.databinding.FragmentNewsBindingImpl;
import com.noblemajesty.newsapplication.databinding.FragmentSportsBindingImpl;
import com.noblemajesty.newsapplication.databinding.NewsRowItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYNEWS = 1;

  private static final int LAYOUT_FRAGMENTFOOD = 2;

  private static final int LAYOUT_FRAGMENTNEWS = 3;

  private static final int LAYOUT_FRAGMENTSPORTS = 4;

  private static final int LAYOUT_NEWSROWITEM = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.noblemajesty.newsapplication.R.layout.activity_news, LAYOUT_ACTIVITYNEWS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.noblemajesty.newsapplication.R.layout.fragment_food, LAYOUT_FRAGMENTFOOD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.noblemajesty.newsapplication.R.layout.fragment_news, LAYOUT_FRAGMENTNEWS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.noblemajesty.newsapplication.R.layout.fragment_sports, LAYOUT_FRAGMENTSPORTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.noblemajesty.newsapplication.R.layout.news_row_item, LAYOUT_NEWSROWITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYNEWS: {
          if ("layout/activity_news_0".equals(tag)) {
            return new ActivityNewsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_news is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFOOD: {
          if ("layout/fragment_food_0".equals(tag)) {
            return new FragmentFoodBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_food is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNEWS: {
          if ("layout/fragment_news_0".equals(tag)) {
            return new FragmentNewsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_news is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSPORTS: {
          if ("layout/fragment_sports_0".equals(tag)) {
            return new FragmentSportsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sports is invalid. Received: " + tag);
        }
        case  LAYOUT_NEWSROWITEM: {
          if ("layout/news_row_item_0".equals(tag)) {
            return new NewsRowItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for news_row_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "data");
      sKeys.put(2, "display");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_news_0", com.noblemajesty.newsapplication.R.layout.activity_news);
      sKeys.put("layout/fragment_food_0", com.noblemajesty.newsapplication.R.layout.fragment_food);
      sKeys.put("layout/fragment_news_0", com.noblemajesty.newsapplication.R.layout.fragment_news);
      sKeys.put("layout/fragment_sports_0", com.noblemajesty.newsapplication.R.layout.fragment_sports);
      sKeys.put("layout/news_row_item_0", com.noblemajesty.newsapplication.R.layout.news_row_item);
    }
  }
}
