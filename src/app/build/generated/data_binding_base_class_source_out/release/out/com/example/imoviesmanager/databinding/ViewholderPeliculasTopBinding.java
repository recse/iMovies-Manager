// Generated by view binder compiler. Do not edit!
package com.example.imoviesmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.imoviesmanager.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewholderPeliculasTopBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView vistaPeliculasTop;

  private ViewholderPeliculasTopBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView vistaPeliculasTop) {
    this.rootView = rootView;
    this.vistaPeliculasTop = vistaPeliculasTop;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewholderPeliculasTopBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewholderPeliculasTopBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.viewholder_peliculas_top, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewholderPeliculasTopBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.vista_peliculas_top;
      ImageView vistaPeliculasTop = rootView.findViewById(id);
      if (vistaPeliculasTop == null) {
        break missingId;
      }

      return new ViewholderPeliculasTopBinding((ConstraintLayout) rootView, vistaPeliculasTop);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
