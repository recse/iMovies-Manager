// Generated by view binder compiler. Do not edit!
package com.example.imoviesmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.imoviesmanager.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContenidoInicioBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout ctInicioActivity;

  @NonNull
  public final FrameLayout frameLayoutInicioActivity;

  private ContenidoInicioBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout ctInicioActivity, @NonNull FrameLayout frameLayoutInicioActivity) {
    this.rootView = rootView;
    this.ctInicioActivity = ctInicioActivity;
    this.frameLayoutInicioActivity = frameLayoutInicioActivity;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContenidoInicioBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContenidoInicioBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.contenido_inicio, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContenidoInicioBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout ctInicioActivity = (ConstraintLayout) rootView;

      id = R.id.frame_layout_inicio_activity;
      FrameLayout frameLayoutInicioActivity = rootView.findViewById(id);
      if (frameLayoutInicioActivity == null) {
        break missingId;
      }

      return new ContenidoInicioBinding((ConstraintLayout) rootView, ctInicioActivity,
          frameLayoutInicioActivity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
