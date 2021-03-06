// Generated by view binder compiler. Do not edit!
package com.example.imoviesmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.imoviesmanager.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityForgotPasswordBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView bienvenidoLabel;

  @NonNull
  public final TextView continuarLabel;

  @NonNull
  public final TextInputEditText emailEditText;

  @NonNull
  public final ImageView loginImageView;

  @NonNull
  public final MaterialButton recuperarBoton;

  @NonNull
  public final TextInputLayout usuarioTextField;

  private ActivityForgotPasswordBinding(@NonNull LinearLayout rootView,
      @NonNull TextView bienvenidoLabel, @NonNull TextView continuarLabel,
      @NonNull TextInputEditText emailEditText, @NonNull ImageView loginImageView,
      @NonNull MaterialButton recuperarBoton, @NonNull TextInputLayout usuarioTextField) {
    this.rootView = rootView;
    this.bienvenidoLabel = bienvenidoLabel;
    this.continuarLabel = continuarLabel;
    this.emailEditText = emailEditText;
    this.loginImageView = loginImageView;
    this.recuperarBoton = recuperarBoton;
    this.usuarioTextField = usuarioTextField;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forgot_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgotPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bienvenidoLabel;
      TextView bienvenidoLabel = rootView.findViewById(id);
      if (bienvenidoLabel == null) {
        break missingId;
      }

      id = R.id.continuarLabel;
      TextView continuarLabel = rootView.findViewById(id);
      if (continuarLabel == null) {
        break missingId;
      }

      id = R.id.emailEditText;
      TextInputEditText emailEditText = rootView.findViewById(id);
      if (emailEditText == null) {
        break missingId;
      }

      id = R.id.loginImageView;
      ImageView loginImageView = rootView.findViewById(id);
      if (loginImageView == null) {
        break missingId;
      }

      id = R.id.recuperarBoton;
      MaterialButton recuperarBoton = rootView.findViewById(id);
      if (recuperarBoton == null) {
        break missingId;
      }

      id = R.id.usuarioTextField;
      TextInputLayout usuarioTextField = rootView.findViewById(id);
      if (usuarioTextField == null) {
        break missingId;
      }

      return new ActivityForgotPasswordBinding((LinearLayout) rootView, bienvenidoLabel,
          continuarLabel, emailEditText, loginImageView, recuperarBoton, usuarioTextField);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
