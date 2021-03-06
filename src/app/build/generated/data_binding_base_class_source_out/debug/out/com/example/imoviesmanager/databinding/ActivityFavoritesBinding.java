// Generated by view binder compiler. Do not edit!
package com.example.imoviesmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import com.example.imoviesmanager.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFavoritesBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView recyclerViewMoviesFavoritesFragment;

  @NonNull
  public final TabLayout tabsFavoritesActivity;

  @NonNull
  public final ViewPager viewPagerFavoritesActivity;

  private ActivityFavoritesBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView recyclerViewMoviesFavoritesFragment,
      @NonNull TabLayout tabsFavoritesActivity, @NonNull ViewPager viewPagerFavoritesActivity) {
    this.rootView = rootView;
    this.recyclerViewMoviesFavoritesFragment = recyclerViewMoviesFavoritesFragment;
    this.tabsFavoritesActivity = tabsFavoritesActivity;
    this.viewPagerFavoritesActivity = viewPagerFavoritesActivity;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFavoritesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFavoritesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_favorites, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFavoritesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recycler_view_movies_favorites_fragment;
      RecyclerView recyclerViewMoviesFavoritesFragment = rootView.findViewById(id);
      if (recyclerViewMoviesFavoritesFragment == null) {
        break missingId;
      }

      id = R.id.tabs_favorites_activity;
      TabLayout tabsFavoritesActivity = rootView.findViewById(id);
      if (tabsFavoritesActivity == null) {
        break missingId;
      }

      id = R.id.view_pager_favorites_activity;
      ViewPager viewPagerFavoritesActivity = rootView.findViewById(id);
      if (viewPagerFavoritesActivity == null) {
        break missingId;
      }

      return new ActivityFavoritesBinding((ConstraintLayout) rootView,
          recyclerViewMoviesFavoritesFragment, tabsFavoritesActivity, viewPagerFavoritesActivity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
