package com.lcbo.common;

import android.support.v4.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>)getActivity()).getComponent());
    }
}
