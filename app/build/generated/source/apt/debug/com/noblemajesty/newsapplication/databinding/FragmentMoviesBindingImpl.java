package com.noblemajesty.newsapplication.databinding;
import com.noblemajesty.newsapplication.R;
import com.noblemajesty.newsapplication.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMoviesBindingImpl extends FragmentMoviesBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.moviesSwipeRefresh, 2);
        sViewsWithIds.put(R.id.moviesRecyclerView, 3);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMoviesBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private FragmentMoviesBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.support.constraint.ConstraintLayout) bindings[0]
            , (android.support.v7.widget.RecyclerView) bindings[3]
            , (android.support.v4.widget.SwipeRefreshLayout) bindings[2]
            , (android.widget.ProgressBar) bindings[1]
            );
        this.moviesFragmentContainer.setTag(null);
        this.progressBar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.display == variableId) {
            setDisplay((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setDisplay(@Nullable java.lang.Boolean Display) {
        this.mDisplay = Display;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.display);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        int displayViewVISIBLEViewGONE = 0;
        boolean androidDatabindingViewDataBindingSafeUnboxDisplay = false;
        java.lang.Boolean display = mDisplay;

        if ((dirtyFlags & 0x3L) != 0) {



                // read android.databinding.ViewDataBinding.safeUnbox(display)
                androidDatabindingViewDataBindingSafeUnboxDisplay = android.databinding.ViewDataBinding.safeUnbox(display);
            if((dirtyFlags & 0x3L) != 0) {
                if(androidDatabindingViewDataBindingSafeUnboxDisplay) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read android.databinding.ViewDataBinding.safeUnbox(display) ? View.VISIBLE : View.GONE
                displayViewVISIBLEViewGONE = ((androidDatabindingViewDataBindingSafeUnboxDisplay) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.progressBar.setVisibility(displayViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): display
        flag 1 (0x2L): null
        flag 2 (0x3L): android.databinding.ViewDataBinding.safeUnbox(display) ? View.VISIBLE : View.GONE
        flag 3 (0x4L): android.databinding.ViewDataBinding.safeUnbox(display) ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}